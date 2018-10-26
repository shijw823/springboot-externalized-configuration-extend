package springboot.propertysource.extend.event.listener;

import springboot.propertysource.extend.util.PropertySourceLoaderUtils;
import org.springframework.boot.context.event.ApplicationPreparedEvent;
import org.springframework.boot.env.PropertiesPropertySourceLoader;
import org.springframework.context.ApplicationListener;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.env.PropertySource;

/**
 * 自定义的 {@link ApplicationListener} 监听 {@link ApplicationPreparedEvent}
 *
 * @author shijw823
 * @since 2018/10/15
 */
public class ApplicationPreparedEventListener implements ApplicationListener<ApplicationPreparedEvent> {
    private final PropertiesPropertySourceLoader loader = new PropertiesPropertySourceLoader();

    @Override
    public void onApplicationEvent(ApplicationPreparedEvent applicationPreparedEvent) {
        PropertySource ps = PropertySourceLoaderUtils.loadPropertySource("ApplicationPreparedEventListener",
                "extend/config/applicationListener.properties", loader);
        ConfigurableEnvironment env = applicationPreparedEvent.getApplicationContext().getEnvironment();
        MutablePropertySources propertySources = env.getPropertySources();
        propertySources.addFirst(ps);
    }
}
