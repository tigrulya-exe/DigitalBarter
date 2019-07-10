package cft.shift.manasyan.barter;

import cft.shift.manasyan.barter.models.deals.Desire;
import cft.shift.manasyan.barter.models.deals.Offer;
import cft.shift.manasyan.barter.repositories.databases.disk.DatabaseDesireRepository;
import cft.shift.manasyan.barter.repositories.databases.disk.DatabaseOfferResponseRepository;
import cft.shift.manasyan.barter.repositories.databases.memory.BarterDealRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RepositoriesConfig {
    @Bean(name = "desires")
    public BarterDealRepository<Desire> desires(){
        return new BarterDealRepository<>();
    }

    @Bean(name = "offers")
    public BarterDealRepository<Offer> offers(){
        return new BarterDealRepository<>();
    }

}
