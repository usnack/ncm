package io.usnack.ncm.whoedit.service.dto.data;

import java.time.ZonedDateTime;

public record BlockEditLogDto(
        String blockId,
        String userId,
        ZonedDateTime time,
        String pageId
) {}
