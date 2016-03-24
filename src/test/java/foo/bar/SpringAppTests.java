package foo.bar;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import test.PoiFilterChainProcess;

public class SpringAppTests {

    @Test
    public void testSayHello() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring-config.xml");

        applicationContext.getBean(PoiFilterChainProcess.class).process("点评筛选业务链");
    }
}
