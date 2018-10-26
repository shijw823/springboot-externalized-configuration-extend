package springboot.propertysource.extend.event.listener;

import org.springframework.boot.context.config.ConfigFileApplicationListener;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.AnnotationAwareOrderComparator;
import springboot.propertysource.extend.util.PropertySourceLoaderUtils;
import org.springframework.boot.context.event.ApplicationEnvironmentPreparedEvent;
import org.springframework.boot.env.PropertiesPropertySourceLoader;
import org.springframework.context.ApplicationListener;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.env.PropertySource;

/**
 * 自定义的 {@link ApplicationListener} 监听 {@link ApplicationEnvironmentPreparedEvent}
 *
 * 发布事件后，通知 Listener 也是有顺序的
 * @see AnnotationAwareOrderComparator
 *
 * @author shijw823
 * @since 2018/10/15
 */
public class ApplicationEnvironmentPreparedEventListener implements ApplicationListener<ApplicationEnvironmentPreparedEvent>, Ordered {
    private final PropertiesPropertySourceLoader loader = new PropertiesPropertySourceLoader();

    @Override
    public void onApplicationEvent(ApplicationEnvironmentPreparedEvent applicationEnvironmentPreparedEvent) {
        PropertySource ps = PropertySourceLoaderUtils.loadPropertySource("ApplicationEnvironmentPreparedEventListener",
                "extend/config/applicationListener.properties", loader);
        ConfigurableEnvironment env = applicationEnvironmentPreparedEvent.getEnvironment();
        MutablePropertySources propertySources = env.getPropertySources();
        propertySources.addFirst(ps);
    }

    @Override
    public int getOrder() {
        return new ConfigFileApplicationListener().getOrder() - 1;
    }
}
