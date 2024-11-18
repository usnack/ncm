package io.usnack.ncm.whoedit.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.web.reactive.function.client.WebClient;
import org.yaml.snakeyaml.Yaml;

import java.io.InputStream;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Optional;

class WhoEditServiceTest {
    private static String NOTION_API_KEY;
    private static WebClient webClient;
    @BeforeAll
    static void setUp() {
        InputStream resourceAsStream = WhoEditServiceTest.class.getClassLoader().getResourceAsStream("application-secret.yaml");
        LinkedHashMap loaded = new Yaml().loadAs(resourceAsStream, LinkedHashMap.class);
        NOTION_API_KEY = ((LinkedHashMap)loaded.get("notion")).get("api-key").toString();
        System.out.println(NOTION_API_KEY);

        webClient = WebClient.builder()
                .baseUrl("https://api.notion.com/v1")
                .defaultHeader("Notion-Version", "2022-06-28")
                .defaultHeader("Authorization", "Bearer " + NOTION_API_KEY)
                .build();

    }
//
//    @Test
//    void findBlockChildrenRecursive() throws JsonProcessingException {
//        String pageId = "110a4919eefa8056aa60d53001f72e4c";
//
//        List<ChildBlock> logs = findEditLogsByPageId(pageId);
//        System.out.println(logs);
//    }
//
//    private List<ChildBlock> findEditLogsByPageId(String blockId) throws JsonProcessingException {
//        List<ChildBlock> childBlocks = new ArrayList<>();
//
//        boolean hasMore = false;
//        String nextCursor = null;
//        do {
//            JsonNode response = webClient.get().
//                    uri(
//                            String.format("/blocks/%s/children%s", blockId, Optional.ofNullable(nextCursor)
//                                    .map(cursor -> String.format("?next_cursor=%s", cursor))
//                                    .orElse(""))
//                    )
//                    .retrieve()
//                    .bodyToMono(JsonNode.class)
//                    .block();
//
//            ArrayNode results = (ArrayNode) response.get("results");
//            results.forEach(result -> {
//                String id = result.get("id").asText();
//                String parentId = result.at("/parent/page_id").asText();
//                String lastEditedBy = result.at("/last_edited_by/id").asText();
//                ZonedDateTime lastEditedTime = ZonedDateTime.parse(result.get("last_edited_time").asText());
//                boolean hasChildren = result.get("has_children").asBoolean();
//                ChildBlock log = new ChildBlock(id, parentId, lastEditedTime, lastEditedBy, hasChildren);
//                childBlocks.add(log);
//
//                if (log.hasChildren()) {
//                    try {
//                        List<ChildBlock> grandChildren = findEditLogsByPageId(log.id());
//                        childBlocks.addAll(grandChildren);
//                    } catch (JsonProcessingException e) {
//                        throw new RuntimeException(e);
//                    }
//                }
//            });
//
//            hasMore = Boolean.parseBoolean(response.get("has_more").toString());
//            nextCursor = response.get("next_cursor").toString();
//        } while(hasMore);
//
//        return childBlocks;
//    }


}