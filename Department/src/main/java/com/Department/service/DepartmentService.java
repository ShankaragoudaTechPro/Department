package com.Department.service;

import com.Department.dto.DepartmentDTO;

import java.util.List;

public interface DepartmentService {
    DepartmentDTO createDepartment(DepartmentDTO departmentDTO);

    List<DepartmentDTO> getAllDepartments(int pageNo, int pageSize, String sort, String sortDir);

    DepartmentDTO getByDepartmentId(long id);

    DepartmentDTO updateDepartment(long id, DepartmentDTO departmentDTO);

    void deleteById(long id);
}
