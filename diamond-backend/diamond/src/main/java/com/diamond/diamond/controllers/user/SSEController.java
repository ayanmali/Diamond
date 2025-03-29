/*
 * Controller for server-sent events (e.g. sending notifications to client)
 */
package com.diamond.diamond.controllers.user;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;


@RestController
public class SSEController {
    @GetMapping(path="/sse", produces=MediaType.TEXT_EVENT_STREAM_VALUE)
    public SseEmitter subscribe() {
        SseEmitter emitter = new SseEmitter(Long.MAX_VALUE);
        return emitter;
    }
}
