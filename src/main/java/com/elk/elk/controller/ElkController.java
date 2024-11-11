package com.elk.elk.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author Md. Aslam Hossain
 * @Date Nov 10, 2024
 */
@Slf4j
@RestController
@RequestMapping("/elk")
@AllArgsConstructor(onConstructor_ = {@Autowired})
public class ElkController {
    private final KafkaTemplate<String, String> kafkaTemplate;

    @PostMapping("")
    private void elk(@RequestBody String message) {
        log.info("ELK api is call with message: " + message);
        kafkaTemplate.send("logs", message);
    }
}
