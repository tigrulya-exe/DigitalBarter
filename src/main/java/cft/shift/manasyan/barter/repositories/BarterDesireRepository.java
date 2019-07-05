package cft.shift.manasyan.barter.repositories;

import cft.shift.manasyan.barter.exceptions.NotFoundException;
import cft.shift.manasyan.barter.models.Desire;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//@Repository
public class BarterDesireRepository {

    private Map<String, Desire> Desires;

    public BarterDesireRepository(){
        this.Desires = new HashMap<>();
    }

    private BarterDesireRepository(Map<String, Desire> Desires){
        this.Desires = Desires;
    }

    public void addDesire(Desire desire) {
        Desires.put(desire.getId(), desire);
    }

    public void closeDesire(String desireId) {

        if (Desires.remove(desireId) == null){
            throw new NotFoundException();
        }
    }

    public List<Desire> getDesires() {
        return new ArrayList<>(Desires.values());
    }

    public Desire getDesire(String desireId) {
        return Desires.get(desireId);
    }


}
