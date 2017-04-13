package com.github.jperucca.alert;

import com.github.jperucca.alert.model.Alert;
import com.github.jperucca.alert.model.AlertType;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

import static com.github.jperucca.alert.model.AlertType.GENERIC_SERVICE;
import static java.util.Arrays.asList;
import static java.util.Optional.ofNullable;
import static java.util.stream.Collectors.toList;

class InMemoryAlertComponent implements AlertComponent {

    private final Set<Alert> alerts;
    private Set<AlertType> handledTypes = new HashSet<>(asList(GENERIC_SERVICE));

    InMemoryAlertComponent() {
        this.alerts = new CopyOnWriteArraySet<>();
    }

    @Override
    public void handle(AlertContext context) {
        ofNullable(context.getType())
                .filter(handledTypes::contains)
                .map(Alert::build)
                .ifPresent(this.alerts::add);
    }

    @Override
    public List<Alert> getAlerts() {
        return alerts.stream().collect(toList());
    }
}
