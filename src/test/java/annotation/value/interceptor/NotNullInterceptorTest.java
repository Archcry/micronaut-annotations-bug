package annotation.value.interceptor;

import io.micronaut.context.ApplicationContext;
import org.junit.jupiter.api.Test;

import javax.inject.Singleton;

public class NotNullInterceptorTest {

    @Test
    void myTest() {
        try(final ApplicationContext ctx = ApplicationContext.run()) {
            ctx.getBean(MyClass.class).myMethod(null);
        }
    }

    @Test
    void mySecondTest() {
        try(final ApplicationContext ctx = ApplicationContext.run()) {
            ctx.getBean(MyClass.class).mySecondMethod(null);
        }
    }

    @Singleton
    @NotNull
    public static class MyClass {
        void myMethod(final String a) {

        }

        @NotNull(test = "lol")
        void mySecondMethod(final String a) {

        }
    }
}
