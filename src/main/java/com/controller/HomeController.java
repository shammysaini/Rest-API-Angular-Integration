package com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.dto.CommonResponse;
import com.dto.EmpResponse;
import com.dto.EmployeeRequest;
import com.dto.EmployeeResponse;
import com.service.EmployeeService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class HomeController {

	@Autowired
	private EmployeeService employeeService;

	@CrossOrigin(origins = "http://localhost:4200/save")
	@PostMapping("/save")
	public ResponseEntity<CommonResponse<EmployeeResponse>> saveRecord(@RequestBody EmployeeRequest employeeRequest) {
		CommonResponse<EmployeeResponse> commonResponse = null;
		log.info("calling saveRecord method present in HomeController class {}", employeeRequest);
		commonResponse = employeeService.recordSave(employeeRequest);
		if (commonResponse != null) {
			return new ResponseEntity<CommonResponse<EmployeeResponse>>(commonResponse, HttpStatus.OK);
		} else {

			return new ResponseEntity<CommonResponse<EmployeeResponse>>(commonResponse, HttpStatus.BAD_REQUEST);

		}

	}

	@GetMapping("/employee")
	public ResponseEntity<List<EmpResponse>> fetchEmployee() {
		log.info("calling fetchEmployee() method present in HomeController class");
		List<EmpResponse> listEmpResponse = null;
		listEmpResponse = employeeService.employeeFetch();
		if (!listEmpResponse.isEmpty()) {
			return new ResponseEntity<List<EmpResponse>>(listEmpResponse, HttpStatus.OK);
		} else {

			return new ResponseEntity<List<EmpResponse>>(listEmpResponse, HttpStatus.OK);

		}
	}
	
	@GetMapping("/record")
	public ResponseEntity<List<EmployeeResponse>> AllEmployee() {
		log.info("calling fetchEmployee() method present in HomeController class");
		List<EmployeeResponse> listEmpResponse = null;
		listEmpResponse = employeeService.fetchAll();
		if (!listEmpResponse.isEmpty()) {
			return new ResponseEntity<List<EmployeeResponse>>(listEmpResponse, HttpStatus.OK);
		} else {

			return new ResponseEntity<List<EmployeeResponse>>(listEmpResponse, HttpStatus.OK);

		}
	}
	
	@GetMapping("/employee/{id}")
	public ResponseEntity<EmpResponse> fetchEmployeeById(@PathVariable("id")Long id) {
		
		log.info("calling fetchEmployeeById() method present in HomeController class");
		
		EmpResponse employeeResponse = null;
		employeeResponse = employeeService.getEmployeeById(id);
		if (employeeResponse != null) {
			return new ResponseEntity<EmpResponse>(employeeResponse, HttpStatus.OK);
		} else {

			return new ResponseEntity<EmpResponse>(employeeResponse, HttpStatus.OK);

		}
		
	}
	
	@PostMapping("employee/update/{id}")
	public ResponseEntity<CommonResponse<EmployeeResponse>> updateRecord(@PathVariable("id") Long id, @RequestBody EmployeeRequest employeeRequest) {
		CommonResponse<EmployeeResponse> commonResponse = null;
		log.info("calling saveRecord method present in HomeController class {}", employeeRequest);
		commonResponse = employeeService.recordUpdate(id,employeeRequest);
		if (commonResponse != null) {
			return new ResponseEntity<CommonResponse<EmployeeResponse>>(commonResponse, HttpStatus.OK);
		} else {

			return new ResponseEntity<CommonResponse<EmployeeResponse>>(commonResponse, HttpStatus.BAD_REQUEST);

		}
	
	}
	
	@DeleteMapping("employee/delete/{id}")
	public ResponseEntity<CommonResponse<EmployeeResponse>> deleteRecord(@PathVariable("id") Long id) {
		CommonResponse<EmployeeResponse> commonResponse = null;
		log.info("calling deleteRecord method present in HomeController class}");
		commonResponse = employeeService.recordDelete(id);
		if (commonResponse != null) {
			return new ResponseEntity<CommonResponse<EmployeeResponse>>(commonResponse, HttpStatus.OK);
		} else {

			return new ResponseEntity<CommonResponse<EmployeeResponse>>(commonResponse, HttpStatus.BAD_REQUEST);

		}
	
	}

}
