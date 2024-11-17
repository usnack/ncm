package io.usnack.ncm.whoedit.service;

import io.usnack.ncm.whoedit.service.dto.PageEditLogResponse;

public interface WhoEditService {
    PageEditLogResponse findPageEditLog(String notionApiKey, String pageId);
}
