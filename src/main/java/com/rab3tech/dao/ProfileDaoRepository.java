package com.rab3tech.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.Query;

import com.rab3tech.dao.entity.ProfileEntity;


public interface ProfileDaoRepository extends JpaRepository<ProfileEntity, String>{

ProfileEntity findByUsernameOrEmail(String username,String email);
	
	Optional<ProfileEntity> findByEmail(String email);
	Optional<ProfileEntity> findByUsernameAndPassword(String username,String password);
	
	public List<ProfileEntity> findAllByOrderByEmailAsc();
	
	 @Query("SELECT distinct p.qualification FROM ProfileEntity p") 
	 public List<String> findMyQualification();
	 
	 @Query("SELECT p FROM ProfileEntity p where p.qualification = ?1")
	 public List<ProfileEntity> filterProfiles(String search);
	
}
