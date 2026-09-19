package com.oconde.reactivo.middleware;

import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilterChain;
import org.springframework.web.server.WebFilter;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;
import reactor.util.context.Context;

@Component
public class UserHeaderFilter implements WebFilter {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        String userId = exchange.getRequest().getHeaders().getFirst("X-User-Id");

        if (userId == null) {
            userId = "SISTEMA_ANONIMO";
        }

        // Se guarda la identidad en el contexto reactivo del Pipeline
        return chain.filter(exchange)
                .contextWrite(Context.of("CURRENT_USER", userId));
    }
}
