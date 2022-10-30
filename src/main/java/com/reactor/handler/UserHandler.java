package com.reactor.handler;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
public class UserHandler {

    public Mono<ServerResponse> getUsers(ServerRequest req){
        Mono<Object[]> mono = WebClient.create("http://localhost:8082").get().uri("/users").retrieve().bodyToMono(Object[].class);
       return ServerResponse.ok().body(mono,Object[].class);
    }

    public Mono<ServerResponse> postUsers(ServerRequest req){
        Mono<Object[]> mono = WebClient.create("http://localhost:8082").get().uri("/users").retrieve().bodyToMono(Object[].class);
        return ServerResponse.ok().body(mono,Object[].class);
    }
}
