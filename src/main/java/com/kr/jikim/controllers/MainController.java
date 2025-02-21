package com.kr.jikim.controllers;

import java.util.concurrent.Callable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller

public class MainController {
    
    private static final Logger log = LoggerFactory.getLogger(MainController.class);

    @GetMapping("/testfilterbefore")
    public String testfilterbefore() {
        log.info("MainController testfilterbefore");
        // return "forward:/testfilterafter";
        return "redirect:/testfilterafter";
    }

// Callable을 사용한 비동기 처리 방식
    @GetMapping("/async")
    @ResponseBody
    public Callable<String> asyncMethod() {
        log.info("비동기 처리 시작");
        
        return () -> {
            log.info("별도 스레드에서 작업 시작");
            // 시간이 걸리는 작업
            Thread.sleep(5000);
            log.info("작업 완료");
            return "비동기 작업 완료";
        };
    }

    @GetMapping("/testfilterafter")
    @ResponseBody
    public String testfilterafter() {
        log.info("MainController testfilterafter");
        return "hihihi";
    }

    @GetMapping("/user")
    @ResponseBody
    public String user() {
        log.info("MainController user");
        return "user";
    }

    @GetMapping("/admin")
    @ResponseBody
    public String admin() {
        log.info("MainController admin");
        return "admin";
    }
}
