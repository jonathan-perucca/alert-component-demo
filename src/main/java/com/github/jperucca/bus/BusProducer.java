package com.github.jperucca.bus;

public interface BusProducer<M> {

    boolean publish(M message);
}
