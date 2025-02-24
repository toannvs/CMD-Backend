package vn.com.ktxcomay.cmd.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.com.ktxcomay.cmd.dto.EmployeeDTO;
import vn.com.ktxcomay.cmd.entity.Employee;
import vn.com.ktxcomay.cmd.mapper.EmployeeMapper;
import vn.com.ktxcomay.cmd.repository.EmployeeRepository;
import vn.com.ktxcomay.cmd.service.EmployeeService;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final EmployeeMapper employeeMapper;

    @Override
    public Page<EmployeeDTO> getEmployeesWithFilters(String name, String email, String phoneNumber, Pageable pageable) {
        return employeeRepository.findWithFilters(name, email, phoneNumber, pageable)
                .map(employeeMapper::toDTO);
    }

    @Override
    public Optional<EmployeeDTO> getEmployeeById(Long id) {
        return employeeRepository.findById(id).map(employeeMapper::toDTO);
    }

    @Override
    public EmployeeDTO createEmployee(EmployeeDTO employeeDTO) {
        Employee employee = employeeMapper.toEntity(employeeDTO);
        return employeeMapper.toDTO(employeeRepository.save(employee));
    }

    @Override
    public Optional<EmployeeDTO> updateEmployee(Long id, EmployeeDTO employeeDTO) {
        return employeeRepository.findById(id).map(employee -> {
            employeeMapper.updateEntityFromDTO(employeeDTO, employee);
            return employeeMapper.toDTO(employeeRepository.save(employee));
        });
    }

    @Override
    public boolean deleteEmployee(Long id) {
        if (employeeRepository.existsById(id)) {
            employeeRepository.deleteById(id);
            return true;
        }
        return false;
    }
}