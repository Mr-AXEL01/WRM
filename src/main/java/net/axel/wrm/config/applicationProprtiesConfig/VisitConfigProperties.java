package net.axel.wrm.config.applicationProprtiesConfig;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.bind.DefaultValue;

import java.time.Duration;

@ConfigurationProperties(prefix = "app.visit.default")
public record VisitConfigProperties(
        @DefaultValue("255")
        Integer priority,

        @DefaultValue("PT3M")
        Duration ept
) {
}
