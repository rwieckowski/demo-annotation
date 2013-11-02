package pl.rawie.demo.annotation;

public class ServiceImpl implements Service {
    @Override
    public int div(int a, @Validate(NonZero.class) int b) {
        System.out.println(String.format("service: %d / %d = %d", a, b, a / b));
        return a + b;
    }
}
