package bozhko_project.electronic_board.configuration;


import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
   /* private ApiKey apiKey() {
        return new ApiKey("JWT", "Authorization", "header");
    }
*/
    @Bean
    public OpenAPI springShopOpenAPI() {

        return new OpenAPI()
                .addSecurityItem(new SecurityRequirement().addList("bearer"))
                .components(new Components()
                        .addSecuritySchemes("bearer",
                                new SecurityScheme()
                                        .type(SecurityScheme.Type.HTTP)
                                        .scheme("basic")
                                        .bearerFormat("JWT")
                                        .flows(new OAuthFlows()
                                                .password(new OAuthFlow()
                                                        .refreshUrl("/login")
                                                        .tokenUrl("/login")
                                                        .scopes(new Scopes().addString("users", "scope объявлений"))
                                                ))))
                .info(new Info().title("Board Service")
                        .description("Board Service")
                        .version("v0.0.1"));
             //   .servers(List.of(new Server().url("http://localhost:8081")));


    }
}


