package com.example.PostawIWygraj.configuration;

import java.nio.file.Paths;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@SuppressWarnings("deprecation")
@Configuration
public class MvcConfig extends WebMvcConfigurerAdapter{

    public void addResourcesHandlers(ResourceHandlerRegistry registry) {
	addResourceHandlers(registry);
	String resourcePath = Paths.get("src/main/resources/static/avatar").toAbsolutePath().toUri().toString();
	
	registry.addResourceHandler("src/main/resources/static/avatar/**")
	.addResourceLocations(resourcePath);
	
	
    }
}
