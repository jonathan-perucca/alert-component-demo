package com.github.jperucca.alert;

import com.github.jperucca.alert.model.Alert;
import com.github.jperucca.alert.model.AlertType;
import org.junit.Test;

import java.util.List;

import static com.github.jperucca.alert.model.AlertType.GENERIC_SERVICE;
import static com.github.jperucca.alert.model.AlertType.UNHANDLED_ALERT;
import static org.assertj.core.api.Assertions.assertThat;

public class InMemoryAlertComponentTest {

    AlertComponent alertComponent = new InMemoryAlertComponent();

    @Test
    public void should_store_alert_on_event() {
        AlertContext alertContext = AlertContext.builder().type(GENERIC_SERVICE).build();

        alertComponent.handle(alertContext);

        List<Alert> alerts = alertComponent.getAlerts();
        assertThat(alerts).hasSize(1);
        assertThat(alerts.get(0))
                .hasFieldOrPropertyWithValue("type", AlertType.GENERIC_SERVICE)
                .hasFieldOrProperty("creation");
    }

    @Test
    public void should_not_store_alert_on_event() {
        AlertContext alertContext = AlertContext.builder().type(UNHANDLED_ALERT).build();

        alertComponent.handle(alertContext);

        assertThat(alertComponent.getAlerts()).hasSize(0);
    }

}