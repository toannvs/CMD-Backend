# Sử dụng JDK 21 để build ứng dụng
FROM eclipse-temurin:21-jdk AS builder

# Đặt thư mục làm việc
WORKDIR /app

# Copy toàn bộ project vào container
COPY . .

# Build ứng dụng bằng Maven wrapper (giả định bạn đang dùng Maven)
RUN ./mvnw clean package -DskipTests

# Sử dụng JRE 21 để chạy ứng dụng (runtime image nhỏ hơn)
FROM eclipse-temurin:21-jre

# Đặt thư mục làm việc trong container
WORKDIR /app

# Copy file JAR từ giai đoạn build
COPY --from=builder /app/target/*.jar app.jar

# Expose cổng server (8091)
EXPOSE 8091

# Lệnh khởi chạy ứng dụng
CMD ["java", "-jar", "app.jar"]