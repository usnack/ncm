package io.usnack.ncm.whoedit.service.dto;

public record NotionCreateTokenResponse(
        String access_token,
        String bot_id,
        String duplicated_template_id,
        String workspace_icon,
        String workspace_id,
        String workspace_name,
        Object owner
) {

}
