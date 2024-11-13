package net.axel.wrm.config;

import net.axel.wrm.config.applicationProprtiesConfig.WaitingRoomConfigProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties({WaitingRoomConfigProperties.class})
public class AppConfig {
}
