package vn.com.ktxcomay.cmd.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import vn.com.ktxcomay.cmd.dto.EmployeeDTO;

import java.util.Optional;

public interface EmployeeService {

    Page<EmployeeDTO> getEmployeesWithFilters(String name, String email, String phoneNumber, Pageable pageable);

    Optional<EmployeeDTO> getEmployeeById(Long id);

    EmployeeDTO createEmployee(EmployeeDTO employeeDTO);

    Optional<EmployeeDTO> updateEmployee(Long id, EmployeeDTO employeeDTO);

    boolean deleteEmployee(Long id);
}