package io.usnack.ncm.whoedit.service.basic;

import io.usnack.ncm.whoedit.service.AuthService;
import io.usnack.ncm.whoedit.service.dto.NotionCreateTokenRequest;
import io.usnack.ncm.whoedit.service.dto.NotionCreateTokenResponse;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Base64;

@Slf4j
@Service
public class BasicAuthService implements AuthService {
    private final String username;
    private final String password;
    private final WebClient notionClient;

    public BasicAuthService(
            @Value("${notion.integration.username}") String username,
            @Value("${notion.integration.password}") String password,
            @Qualifier("notionClient") WebClient notionClient
    ) {
        this.username = username;
        this.password = password;
        this.notionClient = notionClient;
    }

    @Override
    public NotionCreateTokenResponse createTokenByCode(String code) {
        String basicToken = "Basic " + Base64.getEncoder().encodeToString(String.join(":", username, password).getBytes());
        log.debug("basic token: {}", basicToken);

        NotionCreateTokenRequest request = new NotionCreateTokenRequest("authorization_code", code, "http://localhost:8080/whoedit/auth/install-integration");
        NotionCreateTokenResponse response = notionClient.post()
                .uri("/oauth/token")
                .bodyValue(request)
                .header("Authorization", basicToken)
                .retrieve()
                .bodyToMono(NotionCreateTokenResponse.class)
                .block();
        return response;
    }

    public static void main(String[] args) {
        System.out.println(Base64.getEncoder().encodeToString("128d872b-594c-80e7-9537-00371c7c6243:secret_ugnujUJcTOfmkwt7D6e0BmXmHw5OI7qegc9selx94K3".getBytes()));
    }
}
