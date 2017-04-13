package com.github.jperucca.alert.model;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Builder
@Data
public class Alert {

    private AlertType type;
    private LocalDateTime creation;

    public static Alert build(AlertType type) {
        return Alert.builder()
                .type(type)
                .creation(LocalDateTime.now())
                .build();
    }
}
