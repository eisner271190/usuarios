package com.usuarios.usuarios.infraestructure.exceptionhandler;

public class ErrorResponse {
    private int status;
    private String message;
    private String stack;

    public ErrorResponse() {
        this.status = 0;
        this.message = "";
        this.stack = "";
    }

    public ErrorResponse(int status, String message, String stack) {
        this.status = status;
        this.message = message;
        this.stack = stack;
    }

    // Getter para status
    public int getStatus() {
        return status;
    }

    // Setter para status
    public void setStatus(int status) {
        this.status = status;
    }

    // Getter para message
    public String getMessage() {
        return message;
    }

    // Setter para message
    public void setMessage(String message) {
        this.message = message;
    }

    public String getStack() {
        return stack;
    }

    // Setter para status
    public void setStack(String stack) {
        this.stack = stack;
    }
}