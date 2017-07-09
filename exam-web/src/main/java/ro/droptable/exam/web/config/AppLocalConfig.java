package ro.droptable.exam.web.config;

import org.springframework.context.annotation.*;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import ro.droptable.exam.core.JPAConfig;

/**
 * Created by vlad on 19/06/2017.
 */
@Configuration
@ComponentScan({"ro.droptable.exam.core"})
@Import({JPAConfig.class})
@PropertySources({
        @PropertySource(value = "classpath:local/db.properties"),
})
public class AppLocalConfig {
    /**
     * Enables placeholders usage with SpEL expressions.
     *
     * @return
     */
    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }
}
