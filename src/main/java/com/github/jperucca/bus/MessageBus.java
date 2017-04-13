package com.github.jperucca.bus;

import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

public class MessageBus<M> implements BusProducer<M>, BusConsumer<M> {

    private Queue<M> queue;

    public MessageBus() {
        this.queue = new ArrayBlockingQueue<>(150);
    }

    @Override
    public boolean publish(M message) {
        return queue.offer(message);
    }

    @Override
    public M consume() {
        return queue.poll();
    }
}
