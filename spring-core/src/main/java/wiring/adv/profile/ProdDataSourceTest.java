package wiring.adv.profile;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.sql.DataSource;

import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=ProdDataSourceConfig.class)
@ActiveProfiles("prod")
public class ProdDataSourceTest {

    @Autowired
    private DataSource dataSource;

    @Test
    public void testDatasource() {
        assertNotNull(dataSource);
        System.out.println(dataSource);
    }
}