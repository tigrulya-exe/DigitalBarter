package cft.shift.manasyan.barter.models;

import java.util.LinkedList;
import java.util.List;

public class UserDeals {
    private List<Deal> desires = new LinkedList<>();
    private List<Deal> suggestions = new LinkedList<>();

    public List<Deal> getDesires() {
        return desires;
    }

    public List<Deal> getSuggestions() {
        return suggestions;
    }

    public void addDesire(Deal desire){
        desires.add(desire);
    }

    public void addSuggestion(Deal suggestion){
        suggestions.add(suggestion);
    }

    public void deleteDesire(Deal desire){
        desires.remove(desire);
    }

    public void deleteSuggestion(Deal suggestion){
        suggestions.remove(suggestion);
    }
}
