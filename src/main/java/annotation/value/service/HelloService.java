package annotation.value.service;


import annotation.value.interceptor.NotNull;

import javax.inject.Singleton;

@Singleton
@NotNull
public class HelloService {
    public String hello1(final String name) {
        return String.format("Hello %s", name);
    }

    @NotNull(test = "lol")
    public String hello2(final String name) {
        return String.format("Hello %s", name);
    }
}
