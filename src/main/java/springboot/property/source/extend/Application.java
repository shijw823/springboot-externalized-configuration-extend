package springboot.property.source.extend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.core.env.ConfigurableEnvironment;

import java.util.HashMap;
import java.util.Map;

/**
 * 启动器
 *
 * @author shijw823
 * @since 2018/10/15
 */
@SpringBootApplication
@PropertySources({@PropertySource(name = "propertySourceConfig", value = {"classpath:propertySourceConfig.properties"})})
public class Application {

    public static void main(String[] args) {
        Map<String, Object> defaultProperties = new HashMap<>();
        defaultProperties.put("user.age", 88);

        SpringApplication springApplication = new SpringApplicationBuilder(Application.class)
                .properties(defaultProperties)
                .build();

        ConfigurableApplicationContext context = springApplication.run(
                asResolvedArray("--user.age=111",
                        "--spring.config.additional-location=classpath:/extend/config/",
                        "--spring.profiles.active=dev",
                        "--spring.config.name=application,environmentPostProcessor,applicationContextInitializer,applicationListener,springApplicationRunListener"));

        ConfigurableEnvironment env = context.getEnvironment();

        String age = env.getProperty("user.age");
        System.out.println("age=" + age);

        printPropertySource(env);

//        context.close();
    }

    private static <T> T[] asResolvedArray(T... args) {
        return args;
    }

    private static void printPropertySource(ConfigurableEnvironment env) {
        env.getPropertySources().forEach(ps -> {
            System.err.printf("propertySourceName: [%s], propertySourceClassName: [%s]\n", ps.getName(), ps.getClass().getSimpleName());
            System.err.println();
        });
    }
}
