package io.usnack.ncm.whoedit.service.dto;

public record NotionCreateTokenRequest(
        String grant_type,
        String code,
        String redirect_uri
) {
}
