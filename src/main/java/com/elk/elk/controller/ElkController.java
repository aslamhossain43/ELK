package com.elk.elk.controller;

import com.elk.elk.dto.Message;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

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
    private void elk(@RequestBody String log_text) {
        try {
            log.info("ELK api is call with log_text: " + log_text);
            String stringLogMap = prepareStringLogMap(log_text);
            kafkaTemplate.send("logs", stringLogMap);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    private String prepareStringLogMap(String log_text) throws JsonProcessingException {
        String correlationId = UUID.randomUUID().toString().replace("-", "");
        Map<String, Message> logMap = new HashMap<>();
        logMap.put("message", Message.builder().correlation_id(correlationId).log_text(log_text).build());
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(logMap);
    }
}
