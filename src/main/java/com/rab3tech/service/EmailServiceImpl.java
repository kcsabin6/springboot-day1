package com.rab3tech.service;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.InputStreamSource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

@Service
public class EmailServiceImpl implements EmailService {
	
	@Autowired
	private JavaMailSender emailSender;
	
	 @Autowired
	 private SpringTemplateEngine templateEngine;
	 	
	@Override
	public String sendProfileEmail(String to,String from,String name,byte[] oldpic,byte[] newpic) {
		
		try {
			
			MimeMessage message = emailSender.createMimeMessage();
			
			MimeMessageHelper helper = new MimeMessageHelper(message,
	                MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
	                StandardCharsets.UTF_8.name());
			
			Context context=new Context(); 
			Map<String,Object> props=new HashMap<>();
	        props.put("name", name);
	        props.put("sign", "By Cubic Bank");
	        props.put("location", "Fremont CA100 , USA");
	        props.put("email", from);
	        context.setVariables(props);
	        
	        String html = templateEngine.process("profile-pic-update", context);
	        //send data by html string through context object
	        
	        helper.setTo(to);
	        helper.setText(html, true);
	        helper.setSubject("Regarding profile image update!!!!");
	        helper.setFrom(from);
	        
	        InputStreamSource oldImagegeSource =new ByteArrayResource(oldpic);
	        helper.addInline("ob", oldImagegeSource, "image/png");

	        InputStreamSource newImagegeSource =new ByteArrayResource(newpic);
	        helper.addInline("cb", newImagegeSource, "image/png");
	        
	        File file=new ClassPathResource("images/bank-icon.png", EmailServiceImpl.class.getClassLoader()).getFile();
	        byte[] bytes=Files.readAllBytes(file.toPath()); //JAVA7 NIO2
	        InputStreamSource imageSource =new ByteArrayResource(bytes);
	        helper.addInline("bankIcon", imageSource, "image/png");
	        
	        
	        emailSender.send(message);
			
		}catch (Exception e) {
			e.printStackTrace();
		 }		
		
		return "successful";
	}
	

}
