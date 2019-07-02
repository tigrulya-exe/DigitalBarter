package cft.shift.manasyan.barter.services;

import cft.shift.manasyan.barter.repositories.BarterOfferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DigitalBarterService {
    private BarterOfferRepository desiresRepository;
    private BarterOfferRepository suggestsReposirtory;

    @Autowired
    public DigitalBarterService(BarterOfferRepository desiresRepository, BarterOfferRepository suggestsReposirtory) {
        this.desiresRepository = desiresRepository;
        this.suggestsReposirtory = suggestsReposirtory;
    }

    public void addDesire(){

    }
}
