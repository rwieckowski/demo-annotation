package pl.rawie.demo.annotation;

public class ValidationException extends RuntimeException {
    public ValidationException(String message) {
        super(message);
    }
}
