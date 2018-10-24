package springboot.property.source.extend.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.PropertySources;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * 单元测试 {@link PropertySources} 顺序 @TestPropertySource、@SpringBootTest
 *
 * @author shijw823
 * @TestPropertySource#properties
 * @SpringBootTest#properties
 * @TestPropertySource#locations
 * @since 2018/10/15
 */
@RunWith(SpringRunner.class)
@TestPropertySource(
        locations = {"classpath:propertySourceConfig.properties"}, // 3
        properties = {"user.age = 11"} // 1
)
@SpringBootTest(
        classes = PropertySourceOrderTest.class,
        properties = {"user.age = 12"} // 2
)
public class PropertySourceOrderTest {
    @Autowired
    private ConfigurableEnvironment environment;

    @Test
    public void testPropertySourceOrder() {
        Long age = environment.getProperty("user.age", Long.class);
        System.out.println("age=" + age);

        environment.getPropertySources().forEach(propertySource -> {
            System.err.printf("propertySource.getName: [%s], propertySource: [%s]\n", propertySource.getName(), propertySource);
            System.err.println();
        });
    }

}
