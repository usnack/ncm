package io.usnack.ncm.whoedit.service.dto;

import java.time.ZonedDateTime;

public record ChildBlock(
        String id,
        String parentId,
        ZonedDateTime lastEditedTime,
        String lastEditedBy,
        boolean hasChildren
) {
}
