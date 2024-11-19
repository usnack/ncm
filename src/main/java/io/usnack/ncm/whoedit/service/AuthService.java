package io.usnack.ncm.whoedit.service;

import io.usnack.ncm.whoedit.service.dto.NotionCreateTokenResponse;

public interface AuthService {
    NotionCreateTokenResponse createTokenByCode(String code);
}
