package cft.shift.manasyan.barter.models.dtos;

public class ErrorTO {
    private String errorMessage;

    public ErrorTO(){}

    public ErrorTO(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
