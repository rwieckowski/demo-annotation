package pl.rawie.demo.annotation;

public class Demo {
    public static void main(String[] args) {
        Service service = ServiceProxy.proxy(new ServiceImpl());
        service.div(10, 2);
        service.div(10, 0);
    }
}
