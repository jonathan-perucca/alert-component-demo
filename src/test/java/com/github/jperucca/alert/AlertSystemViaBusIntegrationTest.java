package com.github.jperucca.alert;

import com.github.jperucca.alert.model.Alert;
import com.github.jperucca.bus.MessageBus;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static com.github.jperucca.alert.model.AlertType.GENERIC_SERVICE;
import static com.github.jperucca.alert.model.AlertType.UNHANDLED_ALERT;
import static org.assertj.core.api.Assertions.assertThat;

public class AlertSystemViaBusIntegrationTest {

    MessageBus<AlertContext> messageBus;
    AlertConsumer alertConsumer;
    AlertComponent alertComponent;

    @Before
    public void initialize() {
        messageBus = new MessageBus<>();

        alertComponent = new InMemoryAlertComponent();
        alertConsumer = new AlertConsumer(messageBus, alertComponent);
    }

    @Test
    public void should_store_alert_on_event() {
        AlertContext context = AlertContext.builder().type(GENERIC_SERVICE).build();

        messageBus.publish(context);
        alertConsumer.consume();

        List<Alert> alerts = alertComponent.getAlerts();
        System.out.println(alerts);
        assertThat(alerts).hasSize(1);
    }

    @Test
    public void should_not_store_alert_on_event() {
        AlertContext context = AlertContext.builder().type(UNHANDLED_ALERT).build();

        messageBus.publish(context);
        alertConsumer.consume();

        List<Alert> alerts = alertComponent.getAlerts();
        assertThat(alerts).hasSize(0);
    }
}
