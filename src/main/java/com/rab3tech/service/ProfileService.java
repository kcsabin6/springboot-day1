package com.rab3tech.service;

import java.util.List;
import com.rab3tech.controller.dto.ProfileDTO;

public interface ProfileService {
	
	void show();

	String createSignup(ProfileDTO profileDTO);

	ProfileDTO findByEmail(String pemail);

	List<ProfileDTO> sortProfiles(String sort);

	List<ProfileDTO> searchProfiles(String search);

	List<ProfileDTO> findAll();

	List<ProfileDTO> filterProfiles(String filterText);

	List<String> findAllQualification();

	ProfileDTO findByUsername(String pusername);

	String updateSignup(ProfileDTO profileDTO);

	void deleteByUsername(String pusername);

	ProfileDTO authUser(String pusername, String ppassword);

	String findPasswordByUserEmail(String usernameEmail);

	String createiSignup(ProfileDTO profileDTO);

	byte[] findPhotoByUsername(String pusername);

	List<ProfileDTO> findAllWithPhoto();

	String updatePhotoiProfiles(ProfileDTO profileDTO);
	
	

}
