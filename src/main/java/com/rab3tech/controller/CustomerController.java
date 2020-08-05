package com.rab3tech.controller;


import java.io.IOException;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.rab3tech.controller.dto.ProfileDTO;
import com.rab3tech.controller.utils.Utils;
import com.rab3tech.service.ProfileService;

@Controller
public class CustomerController {

	@Autowired	// dependency injection of ProfileService class thus no need to object 
	private ProfileService profileService;
	
	@Autowired 
	private MailSender mailsender;
	

	/* spring container says do not use -->> HttpServletRequest */

	@PostMapping("/changeImage") // data is retrieved from the database so use
	public String updateImage(@RequestParam("file") MultipartFile file,
			@RequestParam("username") String username,@ModelAttribute ProfileDTO profileDTO) {
		//here write code
		//upadate photo into database
		profileDTO.setFile(file);
		profileDTO.setUsername(username);		
		profileService.updatePhotoiProfiles(profileDTO);
		
		SimpleMailMessage email = new SimpleMailMessage();
		email.setTo(username);
		email.setSubject("Regarding image upload");
		email.setText("Hello your profile image is updated");
		mailsender.send(email);
		
		return "redirect:/iprofiles";
	}
	
	
	
	
	@GetMapping("/iprofiles") // data is retrieved from the database so use
	public String iprofiles(Model model) {
		List<ProfileDTO> profileDTOs = profileService.findAllWithPhoto();
		model.addAttribute("profileDTOs", profileDTOs);
		model.addAttribute("listoptions", profileService.findAllQualification());
		return "iprofiles";
	}
	
	
	@GetMapping("/load/image")
	public void findPhotoByUsername(@RequestParam String username,HttpServletResponse response) throws IOException {
		response.setContentType("image/png");
		byte[] photo=profileService.findPhotoByUsername(username);
		ServletOutputStream outputStream=response.getOutputStream();
					//outputStream points to the body of response
		if(photo!=null && photo.length>0) {
		outputStream.write(photo);  //write to the body
		outputStream.flush();		// send to GUI
		}
		outputStream.close();
	}
	
	
	@GetMapping("/signup")
	public String goSignup() {
		return "signup";
	}
	
	
	
	// <form action="signup" method="post">
	@PostMapping("/signup") // data is sent to the database so use @PostMapping
							// annotation
	public String signUp(@ModelAttribute ProfileDTO profileDTO, Model model) {
		String password = Utils.generateRandomPassword(5);
		profileDTO.setPassword(password);
		profileDTO.setUsername(profileDTO.getEmail());
		profileService.createSignup(profileDTO);
		model.addAttribute("hmmmm", "Hi , " + profileDTO.getName() + " , you have done signup successfully!!!!!!!!!!!");
		return "login";

	}
	
	@GetMapping("/isignup")
	public String goiSignup() {
		return "isignup";
	}
	
	@PostMapping("/isignup")
	public String isignUp(@ModelAttribute ProfileDTO profileDTO, Model model) {
		String password = Utils.generateRandomPassword(5);
		profileDTO.setPassword(password);
		profileDTO.setUsername(profileDTO.getEmail());
		profileService.createiSignup(profileDTO);
		model.addAttribute("hmmmm", "Hi , " + profileDTO.getName() + " , you have done signup successfully!!!!!!!!!!!");
		return "login";
	}

	@PostMapping("/usignup") // data is retrieved from the database so use
	// @GetMapping annotation
	public String usignup(@ModelAttribute ProfileDTO profileDTO) {
		profileService.updateSignup(profileDTO);
		return "redirect:/profiles";

	}

	@GetMapping("/deleteProfile") // data is retrieved from the database so use
									// @GetMapping annotation
	public String deleteProfile(@RequestParam("username") String username) {
		profileService.deleteByUsername(username);
		return "profiles";

	}

	@GetMapping("/editProfile") // data is retrieved from the database so use
								// @GetMapping annotation
	public String editProfile(@RequestParam("username") String username, Model model) {
		ProfileDTO profileDTO = profileService.findByUsername(username);
		model.addAttribute("profileDTO", profileDTO);
		return "esignup";

	}

	@GetMapping("/filterProfile") // data is retrieved from the database so use
									// @GetMapping annotation
	public String filterProfile(@RequestParam("filterText") String filterText, Model model) {
		List<ProfileDTO> profileDTOs = null;
		if (!"Select".equalsIgnoreCase(filterText)) {
			profileDTOs = profileService.filterProfiles(filterText);
		} else {
			profileDTOs = profileService.findAll();
		}
		model.addAttribute("listoptions", profileService.findAllQualification());
		model.addAttribute("profileDTOs", profileDTOs);
		return "profiles";
	}

	@GetMapping("/profiles") // data is retrieved from the database so use
								// @GetMapping annotation
	public String profiles(Model model) {
		List<ProfileDTO> profileDTOs = profileService.findAll();
		model.addAttribute("profileDTOs", profileDTOs);
		model.addAttribute("listoptions", profileService.findAllQualification());
		return "profiles";
	}

	@GetMapping("/searchProfile") // data is retrieved from the database so use
									// @GetMapping annotation
	public String searchProfile(Model model, @RequestParam("search") String search) {
		List<ProfileDTO> profileDTOs = profileService.searchProfiles(search);
		model.addAttribute("listoptions", profileService.findAllQualification());
		model.addAttribute("profileDTOs", profileDTOs);
		return "profiles";
	}

	@GetMapping("/sortProfile") // data is retrieved from the database so use
								// @GetMapping annotation
	public String sortProfile(@RequestParam("sort") String sort, Model model) {
		List<ProfileDTO> profileDTOs = profileService.sortProfiles(sort);
		model.addAttribute("listoptions", profileService.findAllQualification());
		model.addAttribute("profileDTOs", profileDTOs);
		return "profiles";
	}

	@GetMapping("/LoggedUser") // data is retrieved from the database so use
	// @GetMapping annotation
	public String loggedUser(Model model) {
		Set<ProfileDTO> loggedUsers = ProfileDTO.loggedInUser();
		model.addAttribute("profileDTOs", loggedUsers);
		return "loggedUsers";

	}
	
	@GetMapping("/fPassword") // data is retrieved from the database so use
	// @GetMapping annotation
	public String forgetPassword() {
		
		return "forgotPassword";

	}
	
	@PostMapping("/fPassword") // data is submitted to the database 
		public String forgotPassword(@RequestParam("usernameEmail") String usernameEmail,Model model) {
		String password=profileService.findPasswordByUserEmail(usernameEmail);
		if(password.length()==0) {
			model.addAttribute("passmsg","Username or email is missing in the database");
		}
		else {
		model.addAttribute("passmsg",password);
		}
		return "forgotPassword";

	}

}
