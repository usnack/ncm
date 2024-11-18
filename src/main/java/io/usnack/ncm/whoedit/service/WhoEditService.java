package io.usnack.ncm.whoedit.service;

import io.usnack.ncm.whoedit.service.dto.data.BlockEditLogDto;

import java.util.List;

public interface WhoEditService {
    List<BlockEditLogDto> findBlockEditLogsByPageId(String notionApiKey, String pageId);
}
