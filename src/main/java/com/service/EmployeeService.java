package com.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.dto.CommonResponse;
import com.dto.EmpResponse;
import com.dto.EmployeeRequest;
import com.dto.EmployeeResponse;

@Service
public interface EmployeeService {

	CommonResponse<EmployeeResponse> recordSave(EmployeeRequest employeeRequest);

	CommonResponse<EmployeeResponse> recordUpdate(Long id, EmployeeRequest employeeRequest);

	List<EmpResponse> employeeFetch();

	List<EmployeeResponse> fetchAll();

	EmpResponse getEmployeeById(Long id);
	CommonResponse<EmployeeResponse> recordDelete(Long id);

}
