package com.atendimentohospitalar.configuracao;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenAPIConfiguracao {

	@Bean
	public OpenAPI customOpenAPI() {
		return new OpenAPI()
				.info(new Info()
				.title("API Sistema Hospitalar")
				.description("Documentação da API de Prontuário, Triagem e Consultas Médicas")
				.version("1.0")
				.contact(new Contact().name("Equipe de Desenvolvimento")
				.email("contato@hosp.com"))
				.license(new License().name("Apache 2.0")
				.url("http://springdoc.org")))
				.externalDocs(new ExternalDocumentation()
				.description("Código Fonte")
				.url("http://github.com"));
	}
}
