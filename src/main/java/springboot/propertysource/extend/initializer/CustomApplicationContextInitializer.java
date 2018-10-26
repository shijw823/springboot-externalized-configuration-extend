package springboot.propertysource.extend.initializer;

import springboot.propertysource.extend.util.PropertySourceLoaderUtils;
import org.springframework.boot.env.PropertiesPropertySourceLoader;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.env.PropertySource;

/**
 * 自定义的 {@link ApplicationContextInitializer}
 *
 * @author shijw823
 * @since 2018/10/15
 */
public class CustomApplicationContextInitializer implements ApplicationContextInitializer {
    private final PropertiesPropertySourceLoader loader = new PropertiesPropertySourceLoader();

    @Override
    public void initialize(ConfigurableApplicationContext applicationContext) {
        PropertySource ps = PropertySourceLoaderUtils.loadPropertySource("CustomApplicationContextInitializer",
                "extend/config/applicationContextInitializer.properties", loader);
        ConfigurableEnvironment env = applicationContext.getEnvironment();
        MutablePropertySources propertySources = env.getPropertySources();
        propertySources.addFirst(ps);
    }
}
