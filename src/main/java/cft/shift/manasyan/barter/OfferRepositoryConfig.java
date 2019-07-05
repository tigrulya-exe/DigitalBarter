package cft.shift.manasyan.barter;

import cft.shift.manasyan.barter.repositories.BarterDesireRepository;
import cft.shift.manasyan.barter.repositories.BarterOfferRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OfferRepositoryConfig {
    @Bean(name = "desires")
    public BarterDesireRepository desires(){
        return new BarterDesireRepository();
    }

    @Bean(name = "offers")
    public BarterOfferRepository offers(){
        return new BarterOfferRepository();
    }
}
