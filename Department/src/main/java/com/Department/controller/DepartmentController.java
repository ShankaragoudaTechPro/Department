package com.Department.controller;

import com.Department.dto.DepartmentDTO;
import com.Department.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/departments")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;


    //http://localhost:8081/api/departments
    @PostMapping
    public ResponseEntity<DepartmentDTO>createDepartment(@RequestBody DepartmentDTO departmentDTO){
        return ResponseEntity.ok(departmentService.createDepartment(departmentDTO));
    }


    //Get : http://localhost:8081/api/departments

    //http://localhost:8081/api/departments?pageNo=1&pageSize=2&sort=name&sortDir=desc


    @GetMapping
    public ResponseEntity<List<DepartmentDTO>> getAllDepartment(
            @RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = "3", required = false) int pageSize,
            @RequestParam(value = "sort", defaultValue = "name", required = false) String sort,
            @RequestParam(value = "sortDir", defaultValue = "asc", required = false) String sortDir
    ) {
        // ✅ Call your service instead of this same controller method

        List<DepartmentDTO> allDepartments = departmentService.getAllDepartments(pageNo, pageSize, sort, sortDir);

        return new ResponseEntity<>(allDepartments, HttpStatus.OK);
    }

    //Get : http://localhost:8081/api/departments/id

    @GetMapping("/{id}")
    public ResponseEntity<DepartmentDTO>getByDepartmentId(@PathVariable long id){
        return ResponseEntity.ok(departmentService.getByDepartmentId(id));
    }

     // http://localhost:8081/api/departments/id
    @PutMapping("{id}")
    public ResponseEntity<DepartmentDTO>updateDepartment(@PathVariable long id ,
                                                         @RequestBody DepartmentDTO departmentDTO){

        return ResponseEntity.ok(departmentService.updateDepartment(id ,departmentDTO));

    }

    //  http://localhost:8081/api/departments/id

    @DeleteMapping("/{id}")
    public ResponseEntity<String>deleteById(@PathVariable long id){
        departmentService.deleteById(id);
        return ResponseEntity.ok("Department is successfully Deleted ");
    }


}
