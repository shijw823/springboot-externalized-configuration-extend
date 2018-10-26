package springboot.propertysource.extend.processor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.env.EnvironmentPostProcessor;
import org.springframework.boot.env.PropertiesPropertySourceLoader;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.PropertySource;
import org.springframework.util.ObjectUtils;
import springboot.propertysource.extend.util.PropertySourceLoaderUtils;

import java.io.File;
import java.util.stream.Stream;

/**
 * 自定义的 {@link EnvironmentPostProcessor}
 *
 * @author shijw823
 * @since 2018/10/15
 */
public class CustomEnvironmentPostProcessor implements EnvironmentPostProcessor {
    private final PropertiesPropertySourceLoader loader = new PropertiesPropertySourceLoader();

    @Override
    public void postProcessEnvironment(ConfigurableEnvironment environment, SpringApplication application) {
//        PropertySource ps = PropertySourceLoaderUtils.loadPropertySource("CustomEnvironmentPostProcessor",
//                "extend/config/environmentPostProcessor.properties", loader);
//        MutablePropertySources propertySources = environment.getPropertySources();
//        propertySources.addFirst(ps);


        /*
         * 加载 activeProfiles 目录下的所有属性文件
         */
        String[] activeProfiles = environment.getActiveProfiles();

        for (String activeProfile : activeProfiles) {
            File[] files = null;
            try {
                String path = this.getClass().getResource("/" + activeProfile).getPath();
                File file = new File(path);
                files = file.listFiles();
            } catch (Exception e) {
                e.printStackTrace();
                continue;
            }

            if (ObjectUtils.isEmpty(files)) {
                continue;
            }

            Stream.of(files).forEach(f -> {
                String fileName = f.getName();
                String fileNamePrefix = fileName.substring(0, fileName.indexOf("."));

//                try (InputStream is = new FileInputStream(f)) {
//                    Properties properties = new Properties();
//                    properties.load(is);
//                    PropertiesPropertySource propertySource = new PropertiesPropertySource("CustomEnvironmentPostProcessor-"
//                            + activeProfile + "-" + fileNamePrefix, properties);
//                    environment.getPropertySources().addFirst(propertySource);
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }

                PropertySource propertySource = PropertySourceLoaderUtils.loadPropertySource("CustomEnvironmentPostProcessor-"
                                + activeProfile + "-" + fileNamePrefix,
                        activeProfile + "/" + fileName, loader);
                environment.getPropertySources().addFirst(propertySource);
            });
        }
    }
}
