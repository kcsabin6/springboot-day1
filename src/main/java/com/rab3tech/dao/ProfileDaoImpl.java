package com.rab3tech.dao;

import java.io.IOException;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.support.SqlLobValue;
import org.springframework.jdbc.support.lob.DefaultLobHandler;
import org.springframework.jdbc.support.lob.LobHandler;
import org.springframework.stereotype.Repository;

import com.rab3tech.dao.entity.ProfileEntity;


@Repository // @Service, @Component, @Controller
public class ProfileDaoImpl implements ProfileDao{
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	/*@Autowired
	@Qualifier("pdataSource")
	private DataSource datasource;
	
	JdbcTemplate jdbcTemplate=null;
	
	@PostConstruct //this annotation ensures this method will be called for sure only once after all the beans creation
	public void magicMethod(){
		//because of this line JdbcTemplate can talk to database
		jdbcTemplate = new JdbcTemplate(datasource);
	}*/
	
	
	@Override
	public String updatePhotoiProfiles(ProfileEntity profileDTO) {
		byte[] images=null;
		
		try {
			images = profileDTO.getFile().getBytes();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		LobHandler lobHadler = new DefaultLobHandler();
		SqlLobValue sqlLobValue = new SqlLobValue(images,lobHadler);
		
		String sql = "update iuser_login_tbl set photo=? where username=?";
		Object data[]={sqlLobValue,profileDTO.getUsername()};
		
		int[] dataType=new int[] {Types.BLOB,Types.VARCHAR};
		jdbcTemplate.update(sql,data,dataType);
		return "success";
	}
	
	//String sql = "update Student set age = ? where id = ?";
	//jdbcTemplateObject.update(sql, age, id);
	
	
	
	
@Override
	
	public String updateSignup(ProfileEntity profileDTO) {
		
		String sql = "update user_login_tbl set name=?,email=?,qualification=?,mobile=?,photo=?,gender=? where username=?";
		
		Object data[]={ profileDTO.getName(),profileDTO.getEmail(),profileDTO.getQualification(),profileDTO.getMobile(),
				profileDTO.getPhoto(),profileDTO.getGender(),profileDTO.getUsername()};
		jdbcTemplate.update(sql,data);
		return "success";
	}

	@Override
	public String createSignup(ProfileEntity profileDTO) {
		String sql = "insert into  user_login_tbl(username,password,name,email,qualification,mobile,photo,gender,createdate) values(?,?,?,?,?,?,?,?,?)";
		Object data[]={profileDTO.getUsername(),profileDTO.getPassword(),profileDTO.getName(),profileDTO.getEmail(),
				profileDTO.getQualification(),profileDTO.getMobile(),profileDTO.getPhoto(),profileDTO.getGender(),new Timestamp(new Date().getTime())};
		jdbcTemplate.update(sql,data);
		return "success";
	}
	
	@Override
	public String createiSignup(ProfileEntity profileDTO) {
		byte[] images=null;
		
		try {
			images = profileDTO.getFile().getBytes();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		LobHandler lobHadler = new DefaultLobHandler();
		SqlLobValue sqlLobValue = new SqlLobValue(images,lobHadler);
		
		String sql = "insert into  iuser_login_tbl(username,password,name,email,qualification,mobile,photo,gender,createdate) values(?,?,?,?,?,?,?,?,?)";
		Object data[]={profileDTO.getUsername(),profileDTO.getPassword(),profileDTO.getName(),profileDTO.getEmail(),
				profileDTO.getQualification(),profileDTO.getMobile(),sqlLobValue,profileDTO.getGender(),new Timestamp(new Date().getTime())};
		
		int[] dataType=new int[] { Types.VARCHAR, Types.VARCHAR,Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR,
				Types.BLOB,Types.VARCHAR,Types.TIMESTAMP };
		jdbcTemplate.update(sql,data,dataType);
		return "success";
	}

	@Override
	public List<ProfileEntity> sortProfiles(String sort) {
		String sql = "select username,password,name,email,qualification,mobile,photo,gender,createdate from user_login_tbl order by email "
				+ sort;
		List<ProfileEntity> profileDTOs=jdbcTemplate.query(sql,new BeanPropertyRowMapper<>(ProfileEntity.class));
		return profileDTOs;
	}
	
	@Override
	public List<String> findAllQualification() {
		String sql = "select distinct qualification from user_login_tbl";
		List<String> qualifications = jdbcTemplate.queryForList(sql,String.class);
		return qualifications;
	}
	
	
	@Override
	public List<ProfileEntity>  filterProfiles(String filterText) {
		// I need to fetch whole profiles data from database
		String sql = "select username,password,name,email,qualification,mobile,photo,gender,createdate from user_login_tbl  where qualification = ?";
		Object[] data={filterText};
		List<ProfileEntity> profileDTOs=jdbcTemplate.query(sql,data,new BeanPropertyRowMapper<>(ProfileEntity.class));
		return profileDTOs;
	}

	@Override
	public List<ProfileEntity> searchProfiles(String search) {
		// I need to fetch whole profiles data from database
		String sql = "select username,password,name,email,qualification,mobile,photo,gender,createdate from user_login_tbl  where name like '%"
				+ search + "%'  or  qualification like '%" + search + "%'";
		List<ProfileEntity> profileDTOs=jdbcTemplate.query(sql,new BeanPropertyRowMapper<>(ProfileEntity.class));
		return profileDTOs;
	}

	@Override
	public List<ProfileEntity> findAll() {
		String sql = "select username,password,name,email,qualification,mobile,photo,gender,createdate from user_login_tbl";
		List<ProfileEntity> profileDTOs=jdbcTemplate.query(sql,new BeanPropertyRowMapper<>(ProfileEntity.class));
		return profileDTOs;
	}
	
	
	@Override
	public ProfileEntity findByEmail(String pemail) {
		String sql = "select username,password,name,email,qualification,mobile,photo,gender,createdate from user_login_tbl where email=?";
		Object[] data={pemail};
		ProfileEntity profileDTO=jdbcTemplate.queryForObject(sql, data,new BeanPropertyRowMapper<>(ProfileEntity.class));
		return profileDTO;
		/*List<ProfileDTO> profileDTOs=jdbcTemplate.query(sql,data,new BeanPropertyRowMapper(ProfileDTO.class));
		return profileDTOs.get(0);*/
	}

	@Override
	public ProfileEntity findByUsername(String pusername) {
		String sql = "select username,password,name,email,qualification,mobile,photo,gender,createdate from user_login_tbl where username=?";
		Object[] data={pusername};
		ProfileEntity profileDTO=jdbcTemplate.queryForObject(sql, data,new BeanPropertyRowMapper<>(ProfileEntity.class));
		return profileDTO;
	}
	
	@Override
	public byte[] findPhotoByUsername(String pusername) {
		String sql = "select photo from iuser_login_tbl where username='"+pusername+"'";
		byte[] photo=jdbcTemplate.queryForObject(sql,byte[].class);
		return photo;
	}

	@Override
	public List<ProfileEntity> findAllWithPhoto() {
		String sql = "select username,password,name,email,qualification,mobile,gender,createdate from iuser_login_tbl";
		List<ProfileEntity> profileDTOs=jdbcTemplate.query(sql,new BeanPropertyRowMapper<>(ProfileEntity.class));
		return profileDTOs;
	}
	
	
	@Override
	public void deleteByUsername(String pusername) {
		String sql = "delete from user_login_tbl where username=?";
		Object[] data={pusername};
		jdbcTemplate.update(sql,data);
	}

	@Override
	public ProfileEntity authUser(String pusername, String ppassword) {
		ProfileEntity profileDTO = null;
		String sql = "select username,password,name,email,qualification,mobile,photo,gender,createdate from user_login_tbl where username=? and password=?";
		Object[] data={pusername,ppassword};
		try{
		profileDTO=jdbcTemplate.queryForObject(sql, data,new BeanPropertyRowMapper<>(ProfileEntity.class));
		}
		catch(DataAccessException e){
			System.out.println(e.getMessage());
		}
		
		return profileDTO;

	}

	@Override
	public String findPasswordByUserEmail(String usernameEmail){
		String password="";
		String sql = "select password from user_login_tbl where username=? or password=?";
		Object[] data={usernameEmail,usernameEmail};
		try{
		password=jdbcTemplate.queryForObject(sql, data,String.class);
		}
		catch(Exception e){
			System.out.println(e.getMessage());
		}
		
		
		return password;
	}
	
	public void show(){
		
		}
	
	/*@Override
	public void show(){
		try {
		Connection conn=datasource.getConnection();
		if( conn!=null)
		System.out.println("Connection is established!!!!!!!!!!!!!!!"+ conn);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}*/
}
