package com.depromeet.health;

import com.depromeet.health.util.VimeoUtil;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class VimeoUtilTest {

    @Test
    public void getVimeoThumbnailTest() {
        var id = 448222113L;
        String thumbnail = VimeoUtil.extractThumbnailFromVimeoVideo(id);
        System.out.println(thumbnail);
        assertNotNull(thumbnail);
    }
}
