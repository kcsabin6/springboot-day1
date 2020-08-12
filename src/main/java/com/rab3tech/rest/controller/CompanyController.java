package com.rab3tech.rest.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rab3tech.rest.vo.CompanyVO;
import com.rab3tech.rest.vo.EmployeeVO;

@RestController
@RequestMapping("/api2")
public class CompanyController {

/*	private int cid;							private int eid;
    private String name;						private String name;
	private String location;					private String email;
	private List<EmployeeVO> employeeVOs;		private int salary;
*/
	
	@GetMapping("/companies/{cid}/employees")
	public List<EmployeeVO> findCompanyEmployees(@PathVariable int cid) {
		List<EmployeeVO> employeeVOs=new ArrayList<EmployeeVO>();
		return employeeVOs;
	}
	
	@GetMapping("/companies/{cid}/employees/{eid}")
	public EmployeeVO findCompanyEmployee(@PathVariable int cid,@PathVariable int eid) {
		return new EmployeeVO();
	}
	
	@PostMapping("/companies/{cid}/employees")
	public List<EmployeeVO> createCompanyEmployees(@RequestBody EmployeeVO employeeVO,@PathVariable int cid) {
		List<EmployeeVO> employeeVOs=new ArrayList<EmployeeVO>();
		return employeeVOs;
	}
	
	@DeleteMapping("/companies/{cid}/employees/{eid}")
	public AppResponse deleteCompanyEmployee(@PathVariable int cid,@PathVariable int eid) {
		AppResponse appResponse=new AppResponse();
		appResponse.setCode(101);
		appResponse.setMesage("Company-Employee is deleted sucessfully");
		return appResponse;
	}
	
	
	@DeleteMapping("/companies/{cid}")
	public AppResponse deleteCompanyByCid(@PathVariable int cid) {
		AppResponse appResponse=new AppResponse();
		appResponse.setCode(101);
		appResponse.setMesage("Company is deleted sucessfully");
		return appResponse;
	}
	
	@DeleteMapping("/companies")
	public AppResponse deleteCompanies() {
		AppResponse appResponse=new AppResponse();
		appResponse.setCode(101);
		appResponse.setMesage("Companies are deleted sucessfully");
		return appResponse;
	}
	
	@GetMapping("/companies")
	public List<CompanyVO> findCompanies() {
		List<CompanyVO> companyVO=new ArrayList<CompanyVO>();
		return companyVO;
	}
		
	@PostMapping("/companies")
	public CompanyVO createCompany(@RequestBody CompanyVO companyVO) {
		System.out.println(companyVO);
		return companyVO;
	}
	
	@PutMapping("/companies")
	public CompanyVO updateCompany(@RequestBody CompanyVO companyVO) {
		System.out.println(companyVO);
		return companyVO;
	}
	
	
	
}
