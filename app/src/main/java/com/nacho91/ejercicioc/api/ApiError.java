package com.nacho91.ejercicioc.api;

import java.util.List;

/**
 * Created by Ignacio on 1/5/2017.
 */

public class ApiError {

    private String message;

    private String error;

    private int status;

    private List<ApiCause> cause;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<ApiCause> getCause() {
        return cause;
    }

    public void setCause(List<ApiCause> cause) {
        this.cause = cause;
    }

    public String getCauseMessage(){
        return cause.get(0).getDescription();
    }
}
