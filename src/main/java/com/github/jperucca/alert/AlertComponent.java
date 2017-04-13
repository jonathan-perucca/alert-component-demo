package com.github.jperucca.alert;

import com.github.jperucca.alert.model.Alert;

import java.util.List;

public interface AlertComponent {

    void handle(AlertContext context);

    List<Alert> getAlerts();
}
