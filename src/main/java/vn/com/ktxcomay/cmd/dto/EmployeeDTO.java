package vn.com.ktxcomay.cmd.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class EmployeeDTO {
    private Long id; // Trường không yêu cầu validation

    @NotBlank(message = "Name is mandatory")
    private String name;

    @NotBlank(message = "Email is mandatory")
    @Email(message = "Email should be valid")
    private String email;

    @NotBlank(message = "Phone number is mandatory")
    private String phoneNumber;

    @NotNull(message = "Active status is mandatory")
    private Boolean isActive;

    private Boolean enableLogin = false; // Khởi tạo giá trị mặc định
}