package com.rab3tech.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rab3tech.controller.dto.ProfileDTO;
import com.rab3tech.dao.ProfileDao;
import com.rab3tech.dao.entity.ProfileEntity;

@Service
public class ProfileServiceImpl implements ProfileService {
	
	@Autowired
	private ProfileDao profileDao;

	@Override
	public void show() {
			
	}

	@Override
	public String createSignup(ProfileDTO profileDTO) {
		ProfileEntity profileEntity=new ProfileEntity();
		BeanUtils.copyProperties(profileDTO, profileEntity);
		//BeanUtils.copyProperties(source, target);
		String result=profileDao.createSignup(profileEntity);
		return result;
	}

	@Override
	public ProfileDTO findByEmail(String pemail) {
		ProfileEntity profileEntity=profileDao.findByEmail(pemail);
		ProfileDTO profileDTO=new ProfileDTO();
		BeanUtils.copyProperties(profileEntity, profileDTO);
		return profileDTO;
	}
	
	private List<ProfileDTO> convertList(List<ProfileEntity> profileEntitys){
		List<ProfileDTO> profileDTOs=new ArrayList<ProfileDTO>();
		for(ProfileEntity profileEntity:profileEntitys) {
			ProfileDTO profileDTO=new ProfileDTO();
			BeanUtils.copyProperties(profileEntity, profileDTO);
			profileDTOs.add(profileDTO);
		}
		return profileDTOs;
	}	

	@Override
	public List<ProfileDTO> sortProfiles(String sort) {
		List<ProfileEntity> profileEntitys=profileDao.sortProfiles(sort);
		List<ProfileDTO> profileDTOs=convertList(profileEntitys);
		return profileDTOs;
	}

	@Override
	public List<ProfileDTO> searchProfiles(String search) {
		List<ProfileEntity> profileEntitys=profileDao.searchProfiles(search);
		List<ProfileDTO> profileDTOs=convertList(profileEntitys);
		return profileDTOs;
	}

	@Override
	public List<ProfileDTO> findAll() {
		List<ProfileEntity> profileEntitys=profileDao.findAll();
		List<ProfileDTO> profileDTOs=convertList(profileEntitys);
		return profileDTOs;
	}

	@Override
	public List<ProfileDTO> filterProfiles(String filterText) {
		List<ProfileEntity> profileEntitys=profileDao.filterProfiles(filterText);
		List<ProfileDTO> profileDTOs=convertList(profileEntitys);
		return profileDTOs;
	}

	@Override
	public List<String> findAllQualification() {
		return profileDao.findAllQualification();
		}

	@Override
	public ProfileDTO findByUsername(String pusername) {
		ProfileEntity profileEntity=profileDao.findByUsername(pusername);
		ProfileDTO profileDTO=new ProfileDTO();
		BeanUtils.copyProperties(profileEntity, profileDTO);
		return profileDTO;
	}

	@Override
	public String updateSignup(ProfileDTO profileDTO) {
		ProfileEntity profileEntity=new ProfileEntity();
		BeanUtils.copyProperties(profileDTO, profileEntity);
		String result=profileDao.updateSignup(profileEntity);
		return result;
	}

	@Override
	public void deleteByUsername(String pusername) {
		profileDao.deleteByUsername(pusername);
	}

	@Override
	public ProfileDTO authUser(String pusername, String ppassword) {
		ProfileEntity profileEntity=profileDao.authUser(pusername, ppassword);
		ProfileDTO profileDTO = new ProfileDTO();
		BeanUtils.copyProperties(profileEntity, profileDTO);
		return profileDTO;
	}

	@Override
	public String findPasswordByUserEmail(String usernameEmail) {
		String result=profileDao.findPasswordByUserEmail(usernameEmail);
		return result;
	}

	@Override
	public String createiSignup(ProfileDTO profileDTO) {
		ProfileEntity profileEntity=new ProfileEntity();
		BeanUtils.copyProperties(profileDTO, profileEntity);
		return profileDao.createiSignup(profileEntity);
	}

	@Override
	public byte[] findPhotoByUsername(String pusername) {
		return profileDao.findPhotoByUsername(pusername);
	}

	@Override
	public List<ProfileDTO> findAllWithPhoto() {
		List<ProfileEntity> profileEntitys=profileDao.findAllWithPhoto();
		List<ProfileDTO> profileDTOs=convertList(profileEntitys);
		return profileDTOs;
	}

	@Override
	public String updatePhotoiProfiles(ProfileDTO profileDTO) {
		ProfileEntity profileEntity = new ProfileEntity();
		BeanUtils.copyProperties(profileDTO, profileEntity);
		return profileDao.updatePhotoiProfiles(profileEntity);
	}

}
