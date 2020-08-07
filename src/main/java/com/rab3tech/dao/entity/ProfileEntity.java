package com.rab3tech.dao.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.web.multipart.MultipartFile;
@Entity
@Table(name="iuser_login_tbl")
public class ProfileEntity {

		@Id
		@Column(length=30)
		private String username;
		@Column(length=30)
		private String password;
		@Column(length=100)
		private String name;
		@Column(length=100)
		private String email;
		@Column(length=12)
		private String mobile;
		@Column(length=7)
		private String gender;
		@Transient
		private String photo;
		@Column(length=100)
		private String qualification;
		@Transient
		private MultipartFile file;
		
		@Column(name="photo",columnDefinition="longblob")
		private byte[] tphoto;
		
		public byte[] getTphoto() {
			return tphoto;
		}

		public void setTphoto(byte[] tphoto) {
			this.tphoto = tphoto;
		}

		public MultipartFile getFile() {
			return file;
		}

		public void setFile(MultipartFile file) {
			this.file = file;
		}

			   

		public ProfileEntity() {}
		
		public ProfileEntity(String username, String password, String name, String email, String mobile, String gender,
				String photo, String qualification) {
			super();
			this.username = username;
			this.password = password;
			this.name = name;
			this.email = email;
			this.mobile = mobile;
			this.gender = gender;
			this.photo = photo;
			this.qualification = qualification;
		}

		private Timestamp createdate;

		public String getUsername() {
			return username;
		}

		public void setUsername(String username) {
			this.username = username;
		}

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public String getMobile() {
			return mobile;
		}

		public void setMobile(String mobile) {
			this.mobile = mobile;
		}

		public String getGender() {
			return gender;
		}

		public void setGender(String gender) {
			this.gender = gender;
		}

		public String getPhoto() {
			return photo;
		}

		public void setPhoto(String photo) {
			this.photo = photo;
		}

		public String getQualification() {
			return qualification;
		}

		public void setQualification(String qualification) {
			this.qualification = qualification;
		}

		public Timestamp getCreatedate() {
			return createdate;
		}

		public void setCreatedate(Timestamp createdate) {
			this.createdate = createdate;
		}

	

	
	
}
