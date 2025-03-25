package com.diamond.diamond.services;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.TimeUnit;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@Service
public class SseService {
    private final List<SseEmitter> emitters = new CopyOnWriteArrayList<>();

    /*
     * Adding an emitter to a list
     */
    public void addEmitter(SseEmitter emitter) {
        emitters.add(emitter);
        emitter.onCompletion(() -> emitters.remove(emitter));
        emitter.onTimeout(() -> emitters.remove(emitter));
    }

    /*
     * Sends events to the client on a recurring basis
     */
    @Scheduled(fixedRate=1, timeUnit=TimeUnit.SECONDS) // emits once every second
    public void emitEvents() {
        for (SseEmitter emitter : emitters) {
            try {
                emitter.send("Testing SSE");
            } catch (IOException e) {
                emitter.complete();
                emitters.remove(emitter);
            }
        }
    }
}
