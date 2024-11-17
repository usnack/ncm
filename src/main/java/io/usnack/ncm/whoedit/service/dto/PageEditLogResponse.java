package io.usnack.ncm.whoedit.service.dto;

import io.usnack.ncm.whoedit.domain.BlockEditLog;

import java.util.List;

public record PageEditLogResponse(
        String pageId,
        List<BlockEditLog> logs
) {
}
