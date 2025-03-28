package com.agua.ai.mcp.server.config;

import com.agua.ai.mcp.server.service.JenkinsApiService;
import org.springframework.ai.tool.ToolCallbackProvider;
import org.springframework.ai.tool.method.MethodToolCallbackProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JenkinsMcpServerConfig {

    @Bean
    public ToolCallbackProvider jenkinsTools(JenkinsApiService jenkinsApiService) {
        return MethodToolCallbackProvider.builder().toolObjects(jenkinsApiService).build();
    }
}
