package com.rmurugaian.spring.security;

import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author rmurugaian 2019-05-17
 */
@Service
public class EmployeeService {

    private static final Map<String, Employee> EMPLOYEE_DB = new ConcurrentHashMap<>();

    public Collection<Employee> all() {

        return EMPLOYEE_DB.values();
    }

    public Optional<Employee> find(final String uuid) {
        return Optional.of(EMPLOYEE_DB.get(uuid));
    }

    public Employee save(final Employee employee) {
        final String uuid = UUID.randomUUID().toString();
        employee.setUuid(uuid);
        return EMPLOYEE_DB.put(uuid, employee);
    }

    public Employee delete(final String uuid) {
        return EMPLOYEE_DB.remove(uuid);
    }

}
