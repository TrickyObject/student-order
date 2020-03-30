package mts.exception;

public class CityRegException extends Exception {

    private String code;


    public CityRegException(String code, String message) {
        super(message);
        this.code = code;
    }

    public CityRegException(String code, String message, Throwable cause) {
        super(message, cause);
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
