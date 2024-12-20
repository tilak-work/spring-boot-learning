package com.tilak.basicproject.services;

import com.tilak.basicproject.entities.Employee;
import com.tilak.basicproject.repsitories.EmployeeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service

public class EmployeeService {
    @Autowired
    EmployeeRepository employeeRepository;
    Logger logger= LoggerFactory.getLogger(EmployeeService.class);

    public List<Employee> getAllEmployees(){
        logger.info("All Employees are retrived from database");
        return employeeRepository.findAll();
    }

    public Optional<Employee> getEmployeeById(int id){
        logger.info("Getting employee by id:"+id);
        return employeeRepository.findById(id);
    }
    public Employee createEmployee(Employee employee){
        logger.info("new Employee is added as "+ employee);
        return employeeRepository.save(employee);
    }
    public Employee updateEmployee(int id,Employee updatedEmployee){
        logger.info("Employee is updated with id:"+id);
        return employeeRepository.findById(id).map(employee -> {
            employee.setName(updatedEmployee.getName());
            employee.setDepartment(updatedEmployee.getDepartment());
            employee.setSalary(updatedEmployee.getSalary());
            return employeeRepository.save(employee);
        }).orElseThrow( ()-> new RuntimeException("Employee not found with id "+id));
    }
    public void deleteEmployee(int id){
        logger.warn("Employee with id:"+id+" is deleted from database");
        employeeRepository.deleteById(id);
    }
}
