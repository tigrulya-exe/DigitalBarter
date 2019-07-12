package cft.shift.manasyan.barter.services;

import cft.shift.manasyan.barter.models.deals.Deal;
import cft.shift.manasyan.barter.models.dtos.DealTO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TOConstructorService {

    static public  <T extends Deal> List<DealTO> getDealTOs(List<T> deals){
        List<DealTO> dealTOS = new ArrayList<>();

        for(Deal deal : deals){
            dealTOS.add(new DealTO(deal));
        }
        return dealTOS;
    }
}
