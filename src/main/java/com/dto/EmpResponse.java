package com.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmpResponse {
	
	private Long id;
	private String name;
	private String email;
	private String phone;
	private String country;
	private String address;
	private String term;
	private String dob;
	private String gender;
	private String is_Active;

}
