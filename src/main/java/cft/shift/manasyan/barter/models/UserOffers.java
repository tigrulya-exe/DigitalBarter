package cft.shift.manasyan.barter.models;

import java.util.LinkedList;
import java.util.List;

public class UserOffers {
    private List<Offer> desires = new LinkedList<>();
    private List<Offer> suggestions = new LinkedList<>();

    public List<Offer> getDesires() {
        return desires;
    }

    public List<Offer> getSuggestions() {
        return suggestions;
    }

    public void addDesire(Offer desire){
        desires.add(desire);
    }

    public void addSuggestion(Offer suggestion){
        suggestions.add(suggestion);
    }

    public void deleteDesire(Offer desire){
        desires.remove(desire);
    }

    public void deleteSuggestion(Offer suggestion){
        suggestions.remove(suggestion);
    }
}
