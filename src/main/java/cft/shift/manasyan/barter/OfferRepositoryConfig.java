package cft.shift.manasyan.barter;

import cft.shift.manasyan.barter.repositories.BarterDealRepository;
import cft.shift.manasyan.barter.services.DigitalBarterService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OfferRepositoryConfig {
    @Bean(name = "desires")
    public BarterDealRepository desires(){
        return new BarterDealRepository();
    }

    @Bean(name = "offers")
    public BarterDealRepository offers(){
        return new BarterDealRepository();
    }
}
