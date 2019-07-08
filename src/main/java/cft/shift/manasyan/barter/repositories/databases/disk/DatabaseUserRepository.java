package cft.shift.manasyan.barter.repositories.databases.disk;

import cft.shift.manasyan.barter.models.user.User;
import cft.shift.manasyan.barter.repositories.databases.interfaces.UserRepository;
import cft.shift.manasyan.barter.repositories.extractors.UserExtractor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
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

        createUser("Tigran", new User("Tigran"));
        createUser("Nikita", new User("Nikita"));
        createUser("Katerina", new User("Katerina"));
        createUser("Daniil", new User("Daniil"));
        createUser("Alina", new User("Alina"));
        createUser("Natasha", new User("Natasha"));
        createUser("Sveta", new User("Sveta"));

    }*/

    @Override
    public Collection<User> getAllUsers()
    {
        /*TODO test is it correct method?*/
        String sql = "select USER_ID, NAME "+
                "from BARTER_USERS";

        return jdbcTemplate.query(sql, userExtractor);
    }

    @Override
    public User fetchUser(String userId) {
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
    public User updateUser(String userId, String name, User user) {
        //update information about user
        String updateUserSql = "update BARTER_USERS "+
                "set NAME=:name, "+
                "where USER_ID=:userId";

        MapSqlParameterSource userParams = new MapSqlParameterSource()
                .addValue("userId", userId)
                .addValue("name", name);

        jdbcTemplate.update(updateUserSql, userParams);

        user.setName(name);

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
    public User createUser(String name, User user) {
        //add user by name
        String insertUserSql = "insert into BARTER_USERS (USER_ID, NAME) values (:userId, :name)";
        MapSqlParameterSource userParams = new MapSqlParameterSource()
                .addValue("name", name)
                .addValue("userId", user.getUid());

        jdbcTemplate.update(insertUserSql, userParams);
        return user;
    }

    @Override
    public User fetchUserByName(String name) {
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
        /*Collection<User> users = this.getAllUsers();
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