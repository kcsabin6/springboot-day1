package com.rab3tech.dao;

import java.util.List;

import com.rab3tech.dao.entity.ProfileEntity;


public interface ProfileDao {

	void show();

	String createSignup(ProfileEntity profileDTO);

	ProfileEntity findByEmail(String pemail);

	List<ProfileEntity> sortProfiles(String sort);

	List<ProfileEntity> searchProfiles(String search);

	List<ProfileEntity> findAll();

	List<ProfileEntity> filterProfiles(String filterText);

	List<String> findAllQualification();

	ProfileEntity findByUsername(String pusername);

	String updateSignup(ProfileEntity profileDTO);

	void deleteByUsername(String pusername);

	ProfileEntity authUser(String pusername, String ppassword);

	String findPasswordByUserEmail(String usernameEmail);

	String createiSignup(ProfileEntity profileDTO);

	byte[] findPhotoByUsername(String pusername);

	List<ProfileEntity> findAllWithPhoto();

	String updatePhotoiProfiles(ProfileEntity profileDTO);
	
	
	

}
