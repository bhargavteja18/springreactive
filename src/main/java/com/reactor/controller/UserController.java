package com.reactor.controller;

import com.reactor.handler.UserHandler;
import com.reactor.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import java.util.concurrent.ExecutionException;
import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class UserController {

    @Autowired
    UserHandler userHandler;


    @Bean
    RouterFunction<ServerResponse> routes() {
        return route(GET("/users"), userHandler::getUsers)
                .andRoute(POST("/users"), userHandler::postUsers).filter((request, next) -> {
                    User user;
                    try {
                        user = request.bodyToMono(User.class).toFuture().get();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    } catch (ExecutionException e) {
                        throw new RuntimeException(e);
                    }
                    if (user != null && user.getId() == 1)
                        return ServerResponse.badRequest().bodyValue("Bad request");

                    return next.handle(request);
                });
    }




}
