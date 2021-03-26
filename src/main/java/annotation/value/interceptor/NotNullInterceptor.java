package annotation.value.interceptor;

import io.micronaut.aop.MethodInterceptor;
import io.micronaut.aop.MethodInvocationContext;
import io.micronaut.core.annotation.AnnotationValue;
import io.micronaut.core.type.MutableArgumentValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.event.Level;

import javax.inject.Singleton;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

@Singleton
public class NotNullInterceptor implements MethodInterceptor<Object, Object> {
    private final static Logger LOG = LoggerFactory.getLogger(NotNullInterceptor.class);

    @Override
    public Object intercept(MethodInvocationContext<Object, Object> context) {
        final Level logLevel = getLogLevel(context);

        Optional<Map.Entry<String, MutableArgumentValue<?>>> nullParam = context.getParameters()
            .entrySet()
            .stream()
            .filter(entry -> {
                MutableArgumentValue<?> argumentValue = entry.getValue();
                return Objects.isNull(argumentValue.getValue());
            })
            .findFirst();

        if (nullParam.isPresent()) {
            final String message = "Null parameter [" + nullParam.get().getKey() + "] not allowed";

            this.log(logLevel, message);
        }

        return context.proceed();
    }

    private Level getLogLevel(MethodInvocationContext<Object, Object> context) {
        final AnnotationValue<NotNull> annotation =
            Objects.requireNonNull(context.getAnnotationMetadata().getAnnotation(NotNull.class));

        final Optional<String> logLevelOptional = annotation.get("logLevel", String.class);

        final String logLevel = logLevelOptional.orElseThrow(IllegalStateException::new);

        return Level.valueOf(logLevel);
    }

    private void log(final Level level, final String message) {
        switch(level) {
            case TRACE:
                LOG.trace(message);
                break;
            case DEBUG:
                LOG.debug(message);
                break;
            case INFO:
                LOG.info(message);
                break;
            case WARN:
                LOG.warn(message);
                break;
            case ERROR:
                LOG.error(message);
                break;
        }
    }
}
