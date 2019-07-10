package cft.shift.manasyan.barter.repositories.databases.disk;

import cft.shift.manasyan.barter.models.user.User;
import cft.shift.manasyan.barter.repositories.databases.interfaces.UserRepository;
import cft.shift.manasyan.barter.repositories.extractors.UserExtractor;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Qualifier("sql")
@ConditionalOnProperty(name = "use.database", havingValue = "true")
public class DatabaseUserRepository implements UserRepository {
    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    private UserExtractor userExtractor;

    /*
    @PostConstruct
    public void initialize()
    {
        String createGenerateUserIdSequenceSql = "create sequence USER_ID_GENERATOR";

        String createUserTableSql = "create table BARTER_USERS (" +
                "USER_ID  VARCHAR(128),"+//" default USER_ID_GENERATOR.nextval," +
                "NAME     VARCHAR(64)" +
                ");";
        jdbcTemplate.update(createUserTableSql, new MapSqlParameterSource());

        addUser("Tigran", new User("Tigran"));
        addUser("Nikita", new User("Nikita"));
        addUser("Katerina", new User("Katerina"));
        addUser("Daniil", new User("Daniil"));
        addUser("Alina", new User("Alina"));
        addUser("Natasha", new User("Natasha"));
        addUser("Sveta", new User("Sveta"));

    }*/

    @Override
    public List<User> getUsers()
    {
        /*TODO test is it correct method?*/
        String sql = "select USER_ID, NAME "+
                "from BARTER_USERS";

        return jdbcTemplate.query(sql, userExtractor);
    }

    @Override
    public User getUser(String userId) {
       String sql = "select BARTER_USERS.USER_ID, NAME "+
               "from BARTER_USERS "+
               "where BARTER_USERS.USER_ID=:userId";

       MapSqlParameterSource params = new MapSqlParameterSource()
               .addValue("userId", userId);

       List<User> users = jdbcTemplate.query(sql, params, userExtractor);

       if(users.isEmpty())
       {
           return null;
       }
       return users.get(0);
    }


    @Override
    public User updateUser(@NonNull User user) {
        //update information about user
        String updateUserSql = "update BARTER_USERS "+
                "set NAME=:name, "+
                "where USER_ID=:userId";

        MapSqlParameterSource userParams = new MapSqlParameterSource()
                .addValue("userId", user.getId())
                .addValue("name", user.getName());

        jdbcTemplate.update(updateUserSql, userParams);

        return user;
    }

    @Override
    public void deleteUser(String userId) {
        String deleteUserSql = "delete from BARTER_USERS where USER_ID=:userId";

        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("userId", userId);

        jdbcTemplate.update(deleteUserSql, params);
    }

    @Override
    public void addUser(@NonNull User user) {
        //add user by name
        String insertUserSql = "insert into BARTER_USERS (USER_ID, NAME) values (:userId, :name)";
        MapSqlParameterSource userParams = new MapSqlParameterSource()
                .addValue("name", user.getName())
                .addValue("userId", user.getId());

        jdbcTemplate.update(insertUserSql, userParams);
    }

    @Override
    public User getUserByName(String name) {
        String sql = "select USER_ID, NAME "+
                "from BARTER_USERS "+
                "where NAME=:name";

        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("NAME", name);

        List<User> users = jdbcTemplate.query(sql, params, userExtractor);

        if(users.isEmpty())
        {
            return null;
        }
        return users.get(0);
        /*Collection<User> users = this.getUsers();
        for(Iterator<User> iter = users.iterator(); iter.hasNext(); )
        {
            User user = iter.next();
            if(user.getName().equals(name))
            {
                return user;
            }
        }
        return null;*/
    }
}
