package hu.me.utils;

public class ValidatorResponse {
    private boolean valid;
    private ErrorMessage errorMessage;

    public ValidatorResponse(boolean valid, ErrorMessage errorMessage){
        this.valid = valid;
        this.errorMessage = errorMessage;
    }

    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }

    public ErrorMessage getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(ErrorMessage errorMessage) {
        this.errorMessage = errorMessage;
    }
}
