package vn.com.ktxcomay.cmd.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import vn.com.ktxcomay.cmd.dto.EmployeeDTO;
import vn.com.ktxcomay.cmd.entity.Employee;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {

    EmployeeDTO toDTO(Employee employee);

    Employee toEntity(EmployeeDTO employeeDTO);

    void updateEntityFromDTO(EmployeeDTO employeeDTO, @MappingTarget Employee employee);
}