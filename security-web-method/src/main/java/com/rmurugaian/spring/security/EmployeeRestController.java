package com.rmurugaian.spring.security;

import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Optional;

/**
 * @author rmurugaian 2019-05-17
 */
@RestController
@RequestMapping("/employees")
public class EmployeeRestController {

    private final EmployeeService employeeService;

    public EmployeeRestController(final EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    public Collection<Employee> all() {
        return employeeService.all();
    }

    @PostMapping
    public Employee save(@RequestBody final Employee employee) {
        return employeeService.save(employee);
    }

    @DeleteMapping(path = "/{uuid}")
    public Employee delete(@PathVariable final String uuid) {
        return employeeService.delete(uuid);
    }

    @GetMapping(path = "/{uuid}")
    public Optional<Employee> find(@PathVariable final String uuid) {
        return employeeService.find(uuid);
    }
}


