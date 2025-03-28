package com.agua.ai.mcp.server.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties("jenkins")
public class JenkinsProperties {
    /**
     * 服务URI
     */
    private String serverUri;
    /**
     * 用户名
     */
    private String username;
    /**
     * 密码/token
     */
    private String password;
}