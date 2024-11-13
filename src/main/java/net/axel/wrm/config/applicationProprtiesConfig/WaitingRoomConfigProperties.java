package net.axel.wrm.config.applicationProprtiesConfig;

import net.axel.wrm.domain.enums.Mode;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.bind.DefaultValue;

@ConfigurationProperties(prefix = "app.waiting-room.default")
public record WaitingRoomConfigProperties(
        @DefaultValue("15")
        Integer capacity,

        @DefaultValue("FULL_TIME")
        Mode mode,

        @DefaultValue("FIFO")
        String algorithm
) {
}
