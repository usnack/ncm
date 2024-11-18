package io.usnack.ncm.whoedit.config;

import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Setter
@Configuration
@ConfigurationProperties("notion")
public class NotionConfig {
    private String baseUrl;
    private String version;

    @Bean
    public WebClient notionClient() {
        return WebClient.builder()
                .baseUrl(baseUrl)
                .defaultHeader("Notion-Version", version)
                .build();
    }
}
