package wiring.adv.profile;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.sql.DataSource;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class DataSourceConfigTest {

  @RunWith(SpringJUnit4ClassRunner.class)
  @ContextConfiguration(classes = DevDataSourceConfig.class)
  // 使用 @ActiveProfiles 注解可以指定运行测试时要激活哪个 profile
  @ActiveProfiles("dev")
  public static class DevDataSourceTest {

    @Autowired
    private DataSource dataSource;

    @Test
    public void testDatasource() {
      assertNotNull(dataSource);
      System.out.println(dataSource);
    }
  }

  @RunWith(SpringJUnit4ClassRunner.class)
  @ContextConfiguration(classes=ProdDataSourceConfig.class)
  @ActiveProfiles("prod")
  public static class ProductionDataSourceTest {

    @Autowired
    private DataSource dataSource;
    
    @Test
    public void testDatasource() {
      assertNull(dataSource);
      System.out.println(dataSource);
    }
  }

  @RunWith(SpringJUnit4ClassRunner.class)
  @ContextConfiguration("classpath:wiring/adv/profile/dataSource.xml")
  @ActiveProfiles("dev")
  public static class DevDataSourceTest_XMLConfig {

    @Autowired
    private DataSource dataSource;

    @Test
    public void testDatasource() {
      assertNotNull(dataSource);
      System.out.println(dataSource);
    }
  }
}
