package pl.gajowski.mateusz.hotelbooking.config.logging;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoggingConfiguration {
    @Bean
    public StubLoggingFilter stubLoggingFilter() {
        return new StubLoggingFilter();
    }
}
