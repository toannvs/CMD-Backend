package vn.com.ktxcomay.cmd.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import vn.com.ktxcomay.cmd.dto.EmployeeDTO;
import vn.com.ktxcomay.cmd.service.EmployeeService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("${api.base-url}${api.endpoints.employees}") // Lấy URL từ cấu hình
@RequiredArgsConstructor // Tự động tạo constructor với các field "final"
@Validated // Kích hoạt validation cho các request
public class EmployeeController {

    private final EmployeeService employeeService; // Inject service vào controller

    // API: GET /employees (Có phân trang và bộ lọc)
    @GetMapping
    public ResponseEntity<Page<EmployeeDTO>> getAllEmployees(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String email,
            @RequestParam(required = false) String phoneNumber,
            Pageable pageable) {
        Page<EmployeeDTO> employees = employeeService.getEmployeesWithFilters(name, email, phoneNumber, pageable);
        return ResponseEntity.ok(employees);
    }

    // API: GET /employees/{id}
    @GetMapping("/{id}")
    public ResponseEntity<EmployeeDTO> getEmployeeById(@PathVariable Long id) {
        return employeeService.getEmployeeById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    // API: POST /employees
    @PostMapping
    public ResponseEntity<EmployeeDTO> createEmployee(@Valid @RequestBody EmployeeDTO employeeDTO) {
        EmployeeDTO createdEmployee = employeeService.createEmployee(employeeDTO);
        return new ResponseEntity<>(createdEmployee, HttpStatus.CREATED);
    }

    // API: PUT /employees/{id}
    @PutMapping("/{id}")
    public ResponseEntity<EmployeeDTO> updateEmployee(@PathVariable Long id, @Valid @RequestBody EmployeeDTO employeeDTO) {
        return employeeService.updateEmployee(id, employeeDTO)
                .map(updatedEmployee -> new ResponseEntity<>(updatedEmployee, HttpStatus.OK))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    // API: DELETE /employees/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable Long id) {
        if (employeeService.deleteEmployee(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}