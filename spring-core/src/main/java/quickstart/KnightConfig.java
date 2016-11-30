package quickstart;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 基于 Java 的配置，可作为 XML 的替代方案
 * <p>
 * <p>
 * Created by liuchenwei on 2016/11/30.
 */
@Configuration
public class KnightConfig {

    @Bean
    public Knight knight(){
        return new BraveKnight(quest());
    }

    @Bean
    public Quest quest(){
        return new SlayDragonQuest(System.out);
    }
}
