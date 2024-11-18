package io.usnack.ncm.whoedit.controller;

import io.usnack.ncm.whoedit.domain.BlockEditLog;
import io.usnack.ncm.whoedit.service.WhoEditService;
import io.usnack.ncm.whoedit.service.dto.data.BlockEditLogDto;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("whoedit")
@RestController
@Slf4j
public class WhoEditController {
    private final WhoEditService whoEditService;

    @GetMapping("blockEditLogs/{pageId}")
    public List<BlockEditLogDto> findBlockEditLogs(@PathVariable("pageId") String pageId, @RequestHeader("x-notion-api-key") String notionApiKey) {
        return whoEditService.findBlockEditLogsByPageId(notionApiKey, pageId);
    }

    @PostConstruct
    public void init() {
        log.info("Hello World!!!!!!!!!!!!");
    }
}
