package vn.com.ktxcomay.cmd.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter // Tự động tạo getter cho các field
@Setter // Tự động tạo setter cho các field
@Configuration // Đánh dấu class này là thành phần cấu hình của ứng dụng (Spring Bean)
@ConfigurationProperties(prefix = "api") // Đọc phần cấu hình bắt đầu bằng "api" trong application.yml
public class ApiProperties {
    private String baseUrl; // URL gốc
    private ApiEndpoints endpoints; // Các endpoint cụ thể

    @Getter
    @Setter
    public static class ApiEndpoints {
        private String employees; // Endpoint API cho employees
    }
}