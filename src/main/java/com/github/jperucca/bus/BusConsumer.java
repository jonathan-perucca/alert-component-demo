package com.github.jperucca.bus;

public interface BusConsumer<M> {

    M consume();
}
