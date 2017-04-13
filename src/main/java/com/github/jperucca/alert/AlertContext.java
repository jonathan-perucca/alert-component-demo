package com.github.jperucca.alert;

import com.github.jperucca.alert.model.AlertType;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class AlertContext {

    private final AlertType type;
}
