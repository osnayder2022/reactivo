package com.oconde.reactivo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.ReactiveAuditorAware;
import org.springframework.data.r2dbc.config.EnableR2dbcAuditing;
import reactor.core.publisher.Mono;

@Configuration
@EnableR2dbcAuditing
public class R2dbcConfig {
    // Si necesitas configurar auditoría de usuario (@CreatedBy, @LastModifiedBy),
    // aquí registrarías un bean de tipo ReactiveAuditorAware<String>

    @Bean
    public ReactiveAuditorAware<String> reactiveAuditorProvider() {
        // Lee el mapa del Contexto donde el filtro guardó al usuario
        return () -> Mono.deferContextual(ctx -> {
            if (ctx.hasKey("CURRENT_USER")) {
                return Mono.just(ctx.get("CURRENT_USER"));
            }
            return Mono.just("SISTEMA");
        });
    }
}