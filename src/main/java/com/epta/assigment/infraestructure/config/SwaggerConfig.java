package com.epta.assigment.infraestructure.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.stereotype.Component;

@Component
@OpenAPIDefinition(
        info =
        @Info(
                title = "ETPA REST APIs",
                version = "1.0",
                description = "ETPA Assignment API Documentation",
                contact =
                @Contact(
                        name = "ETPA Assignment",
                        url = "https://etpa.nl/")),
        servers = {@Server(url = "${server.servlet.context-path}", description = "Default Server URL")})
public class SwaggerConfig {
}
