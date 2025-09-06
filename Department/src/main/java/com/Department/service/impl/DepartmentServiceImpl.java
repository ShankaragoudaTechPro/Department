package com.Department.service.impl;

import com.Department.dto.DepartmentDTO;
import com.Department.entity.Department;
import com.Department.exception.ResourceNotFoundException;
import com.Department.repository.DepartmentRepository;
import com.Department.service.DepartmentService;
import org.hibernate.query.SortDirection;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;



    @Autowired
    private ModelMapper modelMapper;

    @Override
    public DepartmentDTO createDepartment(DepartmentDTO departmentDTO) {
        Department department = mapToEntity(departmentDTO);
        Department department1 = departmentRepository.save(department);
        return mapToDto(department1);
    }

    @Override
    public List<DepartmentDTO> getAllDepartments(int pageNo, int pageSize, String sort, String sortDir) {

        Sort orders = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ?
                Sort.by(sort).ascending() :
                Sort.by(sort).descending();

        PageRequest pageRequest = PageRequest.of(pageNo, pageSize, orders);

        Page<Department> pageResult = departmentRepository.findAll(pageRequest);

        // ✅ use getContent() to fetch only the content list
        List<DepartmentDTO> collect = pageResult.getContent()
                .stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());

        return collect;
    }

    @Override
    public DepartmentDTO getByDepartmentId(long id) {
        Department dept = departmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Department", "id", id));

        return mapToDto(dept);
    }

    @Override
    public DepartmentDTO updateDepartment(long id, DepartmentDTO departmentDTO) {
        Department dept = departmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Department", "id", id));

        dept.setName(departmentDTO.getName());
        dept.setDescription(departmentDTO.getDescription());

        Department upadteDepartment = departmentRepository.save(dept);
        return mapToDto(upadteDepartment);
    }

    @Override
    public void deleteById(long id) {
        Department dept = departmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Department", "id", id));

        departmentRepository.deleteById(id);
    }


    public DepartmentDTO mapToDto (Department department){
        return modelMapper.map(department,DepartmentDTO.class);
    }


    public Department mapToEntity (DepartmentDTO departmentDTO){
        return modelMapper.map(departmentDTO,Department.class);
    }
}

