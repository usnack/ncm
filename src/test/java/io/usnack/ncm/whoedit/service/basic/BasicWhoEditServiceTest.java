package io.usnack.ncm.whoedit.service.basic;

import io.usnack.ncm.whoedit.service.dto.data.BlockEditLogDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BasicWhoEditServiceTest {
    @Autowired BasicWhoEditService whoEditService;

    @Test
    void test() {
        List<BlockEditLogDto> result = whoEditService.findBlockEditLogsByPageId(
                "secret_W0wmlXdoMKtCJodCCQYxadDlCBqVpY4SNEjQRRWHphd",
                "110a4919eefa8056aa60d53001f72e4c"
        );
        System.out.println(result);
    }

}