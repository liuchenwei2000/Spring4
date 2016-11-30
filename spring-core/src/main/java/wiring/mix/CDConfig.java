package wiring.mix;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import wiring.CD;

/**
 * CD 配置类
 */
@Configuration
public class CDConfig {

    @Bean
    public CD myCD(){
        return new WhiteAlbum();
    }
}
