package com.example.demo

import io.swagger.v3.core.converter.ModelConverters
import org.springdoc.core.models.GroupedOpenApi
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class SpringdocConfiguration {

	@Bean
	fun openApi(): GroupedOpenApi {
		return GroupedOpenApi.builder()
			.group("api")
			.packagesToScan("com.example.demo")
			.addOpenApiCustomizer {
				it.components.addSchemas(
					"DemoRequest",
					ModelConverters
						.getInstance()
						.readAllAsResolvedSchema(DemoRequest::class.java)
						.schema
						.apply {
							val prop = properties.getValue("time")
							prop.addExtension("x-key", "ignored")
						}
				)

				it.components.addSchemas(
					"DemoResponse",
					ModelConverters
						.getInstance()
						.readAllAsResolvedSchema(DemoResponse::class.java)
						.schema
						.apply {
							val prop = properties.getValue("time")
							prop.`$ref` = null
							prop.addExtension("x-key", "value")
						}
				)
			}
			.build()
	}
}