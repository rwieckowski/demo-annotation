package pl.rawie.demo.annotation;

public class NonZero implements Validator {
    @Override
    public boolean validate(Object arg) {
        int value = (Integer)arg;
        return value != 0;
    }
}
