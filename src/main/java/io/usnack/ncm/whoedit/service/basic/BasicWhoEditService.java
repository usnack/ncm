package io.usnack.ncm.whoedit.service.basic;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import io.usnack.ncm.whoedit.service.WhoEditService;
import io.usnack.ncm.whoedit.service.dto.data.BlockEditLogDto;
import io.usnack.ncm.whoedit.util.mapper.BlockEditLogMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BasicWhoEditService implements WhoEditService {
    private final WebClient notionClient;
    private final BlockEditLogMapper blockEditLogMapper;

    public BasicWhoEditService(
            @Qualifier("notionClient") WebClient notionClient,
            BlockEditLogMapper blockEditLogMapper
            
    ) {
        this.notionClient = notionClient;
        this.blockEditLogMapper = blockEditLogMapper;
    }

    @Override
    public List<BlockEditLogDto> findBlockEditLogsByPageId(String notionApiKey, String pageId) {

        List<BlockEditLogDto> logs = findAllDescendantsBlock(notionApiKey, pageId);
        return logs;
    }

    private List<BlockEditLogDto> findAllDescendantsBlock(String apiKey, String blockId) {
        List<BlockEditLogDto> editLogs = new ArrayList<>();

        boolean hasMore = true;
        String nextCursor = null;
        while (hasMore) {
            JsonNode response = notionClient.get()
                    .uri(
                            String.format("/blocks/%s/children%s", blockId, Optional.ofNullable(nextCursor)
                                    .map(cursor -> String.format("?next_cursor=%s", cursor))
                                    .orElse(""))
                    )
                    .header(HttpHeaders.AUTHORIZATION, "Bearer " + apiKey)
                    .retrieve()
                    .bodyToMono(JsonNode.class)
                    .retry(3)
                    .block();
            ArrayNode results = (ArrayNode) response.get("results");
            results.forEach(result -> {
                String id = result.get("id").asText();
                String parentId = result.at("/parent/page_id").asText();
                String lastEditedBy = result.at("/last_edited_by/id").asText();
                ZonedDateTime lastEditedTime = ZonedDateTime.parse(result.get("last_edited_time").asText());

                BlockEditLogDto editLog = new BlockEditLogDto(id, lastEditedBy, lastEditedTime, parentId);
                editLogs.add(editLog);

                boolean hasChildren = result.get("has_children").asBoolean();
                if (hasChildren) {
                    List<BlockEditLogDto> grandChildren = findAllDescendantsBlock(apiKey, editLog.blockId());
                    editLogs.addAll(grandChildren);
                }
            });

            hasMore = Boolean.parseBoolean(response.get("has_more").toString());
            nextCursor = response.get("next_cursor").toString();
        }

        return editLogs;
    }
}
