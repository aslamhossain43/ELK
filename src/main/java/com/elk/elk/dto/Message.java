package com.elk.elk.dto;

import lombok.Builder;
import lombok.Data;

/**
 * @Author Md. Aslam Hossain
 * @Date Nov 16, 2024
 */
@Data
@Builder
public class Message {
    private String log_text;
    private String correlation_id;
}
