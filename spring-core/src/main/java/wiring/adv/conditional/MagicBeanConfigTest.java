package wiring.adv.conditional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertNull;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = MagicBeanConfig.class)
public class MagicBeanConfigTest {

    @Autowired(required = false)
    private MagicBean magicBean;

    @Test
    public void testDatasource() {
        assertNull(magicBean);
    }
}