package springboot.property.source.extend.util;

import org.springframework.boot.env.PropertySourceLoader;
import org.springframework.core.env.PropertySource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.IOException;

/**
 * {@link PropertySource} 加载工具类
 *
 * @author shijw823
 * @since 2018/10/21
 */
public class PropertySourceLoaderUtils {

    public static PropertySource loadPropertySource(String propertySourceName, String path,
                                                    PropertySourceLoader propertySourceLoader) {
        Resource resource = new ClassPathResource(path);
        PropertySource<?> propertySource = loadProperties(propertySourceName, resource, propertySourceLoader);
        return propertySource;
    }

    private static PropertySource<?> loadProperties(String propertySourceName, Resource resource,
                                                    PropertySourceLoader loader) {
        if (!resource.exists()) {
            throw new IllegalArgumentException("Resource " + resource + " does not exist");
        }
        try {
            return loader.load(propertySourceName, resource).get(0);
        } catch (IOException ex) {
            throw new IllegalStateException(
                    "Failed to load custom configuration from " + resource, ex);
        }
    }
}
