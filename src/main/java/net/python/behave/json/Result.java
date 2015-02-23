package net.python.behave.json;

public class Result {

    private String status;
    private String[] error_message;
    private Double duration;

    public Result() {

    }

    public String getStatus() {
        return status;
    }

    public double getDuration() {
        return duration == null ? 0L : duration;
    }

    public String[] getErrorMessage() {
        return error_message;
    }
}
