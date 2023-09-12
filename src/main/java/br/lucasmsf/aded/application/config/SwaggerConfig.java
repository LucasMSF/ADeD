package br.lucasmsf.aded.application.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI customOpenAPI() {
        Contact contact = new Contact();
        contact.setName("LucasMSF");

        Info info = new Info()
                .title("AD&D - Advanced Dangeous & Dragons REST API")
                .version("1.0")
                .contact(contact)
                .description("API REST para um sistema de RPG em turnos no formato de duelo");

        return new OpenAPI().info(info);
    }
}