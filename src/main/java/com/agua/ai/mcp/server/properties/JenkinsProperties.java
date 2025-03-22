package com.agua.ai.mcp.server.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties("jenkins")
public class JenkinsProperties {
    private String serverUri;
    private String username;
    private String password;
    private String token;
}