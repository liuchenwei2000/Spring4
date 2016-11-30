package wiring.mix;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import wiring.CD;

/**
 * CDPlayer 配置类
 */
@Configuration
public class CDPlayerConfig {

    @Bean
    public CDPlayer cdPlayer(CD myCD){
        return new CDPlayer(myCD);
    }
}
