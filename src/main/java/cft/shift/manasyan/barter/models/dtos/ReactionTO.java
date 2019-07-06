package cft.shift.manasyan.barter.models.dtos;

public class ReactionTO {
    private String responseId;

    public ReactionTO(){}

    public ReactionTO(String responseId) {
        this.responseId = responseId;
    }

    public String getResponseId() {
        return responseId;
    }

    public void setResponseId(String responseId) {
        this.responseId = responseId;
    }


}
