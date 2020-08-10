package com.rab3tech;

import java.nio.charset.StandardCharsets;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.templatemode.TemplateMode;

@Configuration
public class ThymeleafTemplateConfig {
	
	//these two beans act as view layer and get input String and send to html page 
	
	@Bean
    public SpringTemplateEngine springTemplateEngine() {
        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
        templateEngine.addTemplateResolver(htmlTemplateResolver());
        return templateEngine;
    }
	
	 @Bean
	    public SpringResourceTemplateResolver htmlTemplateResolver(){
	        SpringResourceTemplateResolver emailTemplateResolver = new SpringResourceTemplateResolver();
	        emailTemplateResolver.setPrefix("classpath:/templates/");
	        emailTemplateResolver.setSuffix(".html");
	        emailTemplateResolver.setTemplateMode(TemplateMode.HTML);
	        emailTemplateResolver.setCharacterEncoding(StandardCharsets.UTF_8.name());
	        return emailTemplateResolver;
	    }
	

}
