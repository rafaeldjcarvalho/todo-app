package com.minicurso.todo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * A classe WebConfig configura o comportamento do CORS (Cross-Origin Resource Sharing) 
 * para a aplicação, permitindo que o frontend se comunique com o backend.
 */
@Configuration // Indica que essa classe é uma classe de configuração Spring.
public class WebConfig implements WebMvcConfigurer {

    /**
     * Configura os mapeamentos CORS para permitir que requisições de determinados
     * domínios sejam aceitas pelo backend.
     * 
     * @param registry O objeto usado para registrar mapeamentos CORS.
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/**") // Define o padrão de URL para aplicar as configurações de CORS.
                .allowedOrigins("http://127.0.0.1:5500", "http://localhost:5500") // Especifica os domínios permitidos para acessar os recursos.
                .allowedMethods("GET", "POST", "PUT", "DELETE") // Lista os métodos HTTP permitidos.
                .allowCredentials(true); // Permite o envio de cookies ou credenciais nas requisições.
    }
}