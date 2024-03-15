package com.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.dto.CommonResponse;
import com.dto.EmpResponse;
import com.dto.EmployeeRequest;
import com.dto.EmployeeResponse;
import com.model.Employee;
import com.repo.EmployeeRepository;
import com.service.EmployeeService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;

	@Override
	public CommonResponse<EmployeeResponse> recordSave(EmployeeRequest employeeRequest) {
		log.info("calling recordSave method present in EmployeeServiceImpl {}", employeeRequest);
		Employee employee = null;
		EmployeeResponse employeeResponse = null;
		CommonResponse<EmployeeResponse> commonResponse = null;
		try {
			employee = new Employee();
			employee.setName(employeeRequest.getName());
			employee.setEmail(employeeRequest.getEmail());
			employee.setPhone(employeeRequest.getPhone());
			employee.setCountry(employeeRequest.getCountry());
			employee.setAddress(employeeRequest.getAddress());
			employee.setTerm(employeeRequest.getTerm());
			employee.setDob(employeeRequest.getDob());
			employee.setGender(employeeRequest.getGender());
			employee.setIs_Active(employeeRequest.getIs_Active());
			employee = employeeRepository.save(employee);

		} catch (Exception exception) {
			System.out.println(exception);
		}
		employeeResponse = new EmployeeResponse();
		employeeResponse.setEmployee(employee);
		commonResponse = new CommonResponse<EmployeeResponse>();
		commonResponse.setResult(true);
		commonResponse.setMessage("Data saved Successfully");
		commonResponse.setStatus(HttpStatus.OK.value());
		commonResponse.setData(employeeResponse);

		return commonResponse;
	}

	@Override
	public List<EmpResponse> employeeFetch() {
		log.info("calling employeeFetch() method present in EmployeeServiceImpl");
		List<EmpResponse> listEmpResponse = new ArrayList<>();
		List<Employee> employeeList = employeeRepository.findAll();
		if (!employeeList.isEmpty()) {
			for (Employee employee : employeeList) {
				EmpResponse employeeResponse = new EmpResponse();
				employeeResponse.setId(employee.getId());
				employeeResponse.setName(employee.getName());
				employeeResponse.setEmail(employee.getEmail());
				employeeResponse.setPhone(employee.getPhone());
				employeeResponse.setCountry(employee.getCountry());
				employeeResponse.setAddress(employee.getAddress());
				employeeResponse.setTerm(employee.getTerm());
				employeeResponse.setDob(employee.getDob());
				employeeResponse.setGender(employee.getGender());
				employeeResponse.setIs_Active(employee.getIs_Active().toString());
				listEmpResponse.add(employeeResponse);

			}

		} else {
			Collections.emptyList();
		}
		return listEmpResponse;
	}

	@Override
	public List<EmployeeResponse> fetchAll() {

		log.info("calling fetchAll() method present in EmployeeServiceImpl");
		List<EmployeeResponse> listEmpResponse = new ArrayList<>();
		List<Employee> employeeList = employeeRepository.findAll();
		if (!employeeList.isEmpty()) {
			for (Employee employee : employeeList) {

				EmployeeResponse employeeResponse = new EmployeeResponse();
				employeeResponse.setEmployee(employee);
				listEmpResponse.add(employeeResponse);

			}

		} else {

			Collections.emptyList();
		}

		return listEmpResponse;

	}

	@Override
	public EmpResponse getEmployeeById(Long id) {
		log.info("calling employeeFetch() method present in EmployeeServiceImpl");
		EmpResponse empResponse = null;
		Optional<Employee> optEmployee = employeeRepository.findById(id);
		if (optEmployee.isPresent()) {
			Employee employee = optEmployee.get();

			empResponse = new EmpResponse();
			empResponse.setId(employee.getId());
			empResponse.setName(employee.getName());
			empResponse.setEmail(employee.getEmail());
			empResponse.setPhone(employee.getPhone());
			empResponse.setCountry(employee.getCountry());
			empResponse.setAddress(employee.getAddress());
			empResponse.setTerm(employee.getTerm());
			empResponse.setDob(employee.getDob());
			empResponse.setGender(employee.getGender());
			empResponse.setIs_Active(employee.getIs_Active().toString());
			return empResponse;

		}

		return empResponse;
	}

	@Override
	public CommonResponse<EmployeeResponse> recordUpdate(Long id, EmployeeRequest employeeRequest) {
		log.info("calling recordUpdate() method present in EmployeeServiceImpl");
		Employee employee=null;
		EmployeeResponse employeeResponse = null;
		CommonResponse<EmployeeResponse> commonResponse = null;
		Optional<Employee> optEmployee = employeeRepository.findById(id);
		if(optEmployee.isPresent())
		{
			employee = optEmployee.get();
			employee.setName(employeeRequest.getName());
			employee.setEmail(employeeRequest.getEmail());
			employee.setPhone(employeeRequest.getPhone());
			employee.setCountry(employeeRequest.getCountry());
			employee.setAddress(employeeRequest.getAddress());
			employee.setTerm(employeeRequest.getTerm());
			employee.setDob(employeeRequest.getDob());
			employee.setGender(employeeRequest.getGender());
			employee.setIs_Active(employeeRequest.getIs_Active());
			employee = employeeRepository.save(employee);
		}
		else {
			
			System.out.println("employee not found");
			
		}
		employeeResponse = new EmployeeResponse();
		employeeResponse.setEmployee(employee);
		commonResponse = new CommonResponse<EmployeeResponse>();
		commonResponse.setResult(true);
		commonResponse.setMessage("Record update Successfully");
		commonResponse.setStatus(HttpStatus.OK.value());
		commonResponse.setData(employeeResponse);

		return commonResponse;
	}

	@Override
	public CommonResponse<EmployeeResponse> recordDelete(Long id) {
		log.info("calling recordDelete() method present in EmployeeServiceImpl");
		EmployeeResponse employeeResponse = null;
		CommonResponse<EmployeeResponse> commonResponse = null;
		Optional<Employee> optEmployee = employeeRepository.findById(id);
		if(optEmployee.isPresent())
		{
			employeeRepository.deleteById(id);
			employeeResponse = new EmployeeResponse();
			commonResponse = new CommonResponse<EmployeeResponse>();
			commonResponse.setResult(true);
			commonResponse.setMessage("Delete Record Successfully");
			commonResponse.setStatus(HttpStatus.OK.value());
			commonResponse.setData(employeeResponse);
			

		
		}
		else {
			System.out.println("Record not found");
		}
		
		return commonResponse;

}
	
}
