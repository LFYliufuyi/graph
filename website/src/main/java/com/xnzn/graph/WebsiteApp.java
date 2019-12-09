package com.xnzn.graph;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

/**
 * @Auther: xn
 * @Date: 2019/9/26 16:02
 */
@SpringBootApplication
@Import({BaseConfig.class})
public class WebsiteApp {
    public static void main(String[] args) {
        SpringApplication.run(WebsiteApp.class, args);
    }
}
