package com.rab3tech.rest.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


class Dog{
	
	private String name;
	private int price;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	@Override
	public String toString() {
		return "Dog [name=" + name + ", price=" + price + "]";
	}
	
	
}


@RestController	//RestController annotation is used to create RESTful web services using Spring MVC
@RequestMapping("/api")	//RequestMapping annotation is used to map web requests onto 
						//specific handler classes and/or handler method
public class ProfileJSONController {
	
	@GetMapping("/cool")
	public String magic() {
		return "How cool !!!!!";
	}

	@PostMapping("/dogs")
	public AppResponse findDog(@RequestBody Dog dog) {	//@RequestBody--> Hey I can take incoming JSON data and
												//will convert Java Objects -->>Jackson mapper.
		System.out.println(dog);
		AppResponse appResponse =new AppResponse();
		appResponse.setCode(101);
		appResponse.setMesage("Hey dog is uploaded sucessfully");
		return appResponse;
	}
	
	
	@GetMapping("/dog")
	public Dog findDog() {	//it is also called resources , mapped with unique URI 
		Dog dog=new Dog();
		dog.setName("Tommy");
		dog.setPrice(999);
		return dog;
	}
	
	
	@GetMapping("/dogs")
	public List<Dog> findDogs() {
		List<Dog> list=new ArrayList<>(); 
		Dog dog1=new Dog();
		dog1.setName("Tommy");
		dog1.setPrice(999);
		list.add(dog1);
		
		Dog dog2=new Dog();
		dog2.setName("Lommy");
		dog2.setPrice(899);
		list.add(dog2);
		
		Dog dog3=new Dog();
		dog3.setName("Kommy");
		dog3.setPrice(799);
		list.add(dog3);
		return list;
	}
	
	
}
