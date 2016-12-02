package wiring.adv.spel;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertNotNull;

/**
 * SpEL 基本用法示例
 * <p>
 * <p>
 * Created by liuchenwei on 2016/12/2.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:wiring/adv/spel/walkman.xml")
public class WalkmanConfigTest {

    @Autowired
    private Walkman walkman;

    @Autowired
    private Information information;

    @Test
    public void testWalkman(){
        assertNotNull(walkman);
        walkman.play();
        System.out.println(walkman);
    }

    @Test
    public void testInformation(){
        assertNotNull(information);
        System.out.println(information);
    }
}
