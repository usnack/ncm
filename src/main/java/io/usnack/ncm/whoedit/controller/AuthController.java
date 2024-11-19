package io.usnack.ncm.whoedit.controller;

import io.usnack.ncm.whoedit.service.AuthService;
import io.usnack.ncm.whoedit.service.dto.NotionCreateTokenResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("whoedit/auth")
@RestController
@Slf4j
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @GetMapping("install-integration")
    public NotionCreateTokenResponse installIntegration(
            @RequestParam(value = "code", required = false) String code,
            @RequestParam(value = "error", required = false) String error,
            @RequestParam(value = "state", required = false) String state
    ) {
        log.debug("redirected with:\n\tcode: {}\n\terror: {}\n\tstate: {}", code, error, state);

        return authService.createTokenByCode(code);
    }
}
