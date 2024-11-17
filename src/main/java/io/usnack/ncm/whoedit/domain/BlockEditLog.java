package io.usnack.ncm.whoedit.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.xml.bind.DatatypeConverter;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.ZonedDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BlockEditLog {
    private String id;
    private String blockId;
    private String userId;
    private ZonedDateTime time;
    //
    private String pageId;

    protected BlockEditLog(String id) {
        this.id = id;
    }

    public BlockEditLog(String blockId, String userId, ZonedDateTime time, String pageId) {
        this(hash(blockId, userId, time));

        this.blockId = blockId;
        this.userId = userId;
        this.time = time;
        this.pageId = pageId;
    }

    public static String hash(String blockId, String userId, ZonedDateTime time) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] content = String.join("#", blockId, userId, time.toString()).getBytes(StandardCharsets.UTF_8);
            byte[] messageDigest = md.digest(content);
            return DatatypeConverter.printHexBinary(messageDigest).toLowerCase();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("MD5 해시 생성 중 오류 발생", e);
        }
    }
}
