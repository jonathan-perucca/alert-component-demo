package com.github.jperucca.alert;

import com.github.jperucca.bus.MessageBus;

import static java.util.Optional.ofNullable;

public class AlertConsumer {

    MessageBus<AlertContext> messageBus;
    AlertComponent alertComponent;

    public AlertConsumer(MessageBus<AlertContext> messageBus,
                         AlertComponent alertComponent) {
        this.messageBus = messageBus;
        this.alertComponent = alertComponent;
    }

    public void consume() {
        ofNullable( messageBus.consume() )
        .ifPresent( alertComponent::handle );
    }
}
