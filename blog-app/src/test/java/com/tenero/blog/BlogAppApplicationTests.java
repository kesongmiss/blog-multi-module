package com.tenero.blog;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

@SpringBootTest(classes = {BlogAppApplication.class})
class BlogAppApplicationTests {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Test
    void contextLoads() {
        log.info("测试上传GitHub");
        log.info("UUID {} " +UUID.randomUUID().toString().replace("-",""));
        return;
    }

}
