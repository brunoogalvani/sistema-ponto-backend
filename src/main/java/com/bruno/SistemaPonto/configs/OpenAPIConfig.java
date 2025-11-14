package com.bruno.SistemaPonto.configs;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import io.swagger.v3.oas.models.tags.Tag;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OpenAPIConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
            .info(new Info()
                .title("Sistema de Ponto")
                .description("API do sistema de registro de ponto eletrônico")
                .version("1.0.0"))
            .servers(List.of(
                new Server().url("http://localhost:8080")
            ))
            .tags(List.of(
                new Tag().name("Usuários").description("Gerenciamento de usuários"),
                new Tag().name("Autenticação").description("Operação de Autenticação"),
                new Tag().name("Folha Ponto").description("Registro e consulta de pontos"),
                new Tag().name("Solicitação").description("Processamento, inserção e consulta de solicitações")
            ));
    }
}
