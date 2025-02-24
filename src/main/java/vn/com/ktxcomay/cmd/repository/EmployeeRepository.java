package vn.com.ktxcomay.cmd.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import vn.com.ktxcomay.cmd.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    @Query("SELECT e FROM Employee e WHERE " +
            "(:name IS NULL OR LOWER(e.name) LIKE LOWER(CONCAT('%', :name, '%'))) AND " +
            "(:email IS NULL OR LOWER(e.email) LIKE LOWER(CONCAT('%', :email, '%'))) AND " +
            "(:phoneNumber IS NULL OR e.phoneNumber LIKE CONCAT('%', :phoneNumber, '%'))")
    Page<Employee> findWithFilters(String name, String email, String phoneNumber, Pageable pageable);
}