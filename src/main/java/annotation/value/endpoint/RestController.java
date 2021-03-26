package annotation.value.endpoint;

import annotation.value.service.HelloService;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.QueryValue;

import java.util.Optional;

@Controller
public class RestController {

    private final HelloService helloService;

    public RestController(final HelloService helloService) {
        this.helloService = helloService;
    }

    @Get("/hello/1")
    public HttpResponse<String> hello(@QueryValue("name") final Optional<String> name) {
        return HttpResponse.ok(helloService.hello1(name.orElse(null)));
    }

    @Get("/hello/2")
    public HttpResponse<String> hello2(@QueryValue("name") final Optional<String> name) {
        return HttpResponse.ok(helloService.hello2(name.orElse(null)));
    }
}
