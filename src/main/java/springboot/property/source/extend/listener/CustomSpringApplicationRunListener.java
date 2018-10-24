package springboot.property.source.extend.listener;

import springboot.property.source.extend.util.PropertySourceLoaderUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringApplicationRunListener;
import org.springframework.boot.context.event.EventPublishingRunListener;
import org.springframework.boot.env.PropertiesPropertySourceLoader;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.Ordered;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.env.PropertySource;

/**
 * 自定义的 {@link SpringApplicationRunListener}
 *
 * @author shijw823
 * @since 2018/10/15
 */
public class CustomSpringApplicationRunListener implements SpringApplicationRunListener, Ordered {
    private final PropertiesPropertySourceLoader loader = new PropertiesPropertySourceLoader();

    private final SpringApplication application;

    private final String[] args;

    public CustomSpringApplicationRunListener(SpringApplication application, String[] args) {
        this.application = application;
        this.args = args;
    }

    @Override
    public void starting() {
        System.err.println("CustomSpringApplicationRunListener-starting");
    }

    @Override
    public void environmentPrepared(ConfigurableEnvironment environment) {
        PropertySource ps = PropertySourceLoaderUtils.loadPropertySource("CustomSpringApplicationRunListener-environmentPrepared",
                "extend/config/springApplicationRunListener.properties", loader);
        MutablePropertySources propertySources = environment.getPropertySources();
        propertySources.addFirst(ps);
    }

    @Override
    public void contextPrepared(ConfigurableApplicationContext context) {
        PropertySource ps = PropertySourceLoaderUtils.loadPropertySource("CustomSpringApplicationRunListener-contextPrepared",
                "extend/config/springApplicationRunListener.properties", loader);
        ConfigurableEnvironment env = context.getEnvironment();
        MutablePropertySources propertySources = env.getPropertySources();
        propertySources.addFirst(ps);
    }

    @Override
    public void contextLoaded(ConfigurableApplicationContext context) {
        PropertySource ps = PropertySourceLoaderUtils.loadPropertySource("CustomSpringApplicationRunListener-contextLoaded",
                "extend/config/springApplicationRunListener.properties", loader);
        ConfigurableEnvironment env = context.getEnvironment();
        MutablePropertySources propertySources = env.getPropertySources();
        propertySources.addFirst(ps);
    }

    @Override
    public void started(ConfigurableApplicationContext context) {
//        ConfigurableEnvironment environment = context.getEnvironment();
//        MutablePropertySources propertySources = environment.getPropertySources();
//        Map<String, Object> map = new HashMap<>();
//        map.put("user.age", 103);
//        PropertySource ps = new MapPropertySource("CustomSpringApplicationRunListener-started", map);
//        propertySources.addFirst(ps);
    }

    @Override
    public void running(ConfigurableApplicationContext context) {

    }

    @Override
    public void failed(ConfigurableApplicationContext context, Throwable exception) {

    }

    @Override
    public int getOrder() {
        // 代表在 EventPublishingRunListener 之后
        return new EventPublishingRunListener(application, args).getOrder() + 1;
    }
}
