package com.superduperemployee.employee1;

import com.superduperemployee.employee1.EmployeeAlreadyAddedException;
import com.superduperemployee.employee1.EmployeeNotFoundException;
import com.superduperemployee.employee1.EmployeeStorageIsFullException;
import com.superduperemployee.employee1.Employee;
import com.superduperemployee.employee1.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping("/add")
    public Employee addEmployee(@RequestParam String firstName, @RequestParam String lastName) {
        return employeeService.addEmployee(firstName, lastName);
    }

    @DeleteMapping("/remove")
    public Employee removeEmployee(@RequestParam String firstName, @RequestParam String lastName) {
        return employeeService.removeEmployee(firstName, lastName);
    }

    @GetMapping("/find")
    public Employee findEmployee(@RequestParam String firstName, @RequestParam String lastName) {
        return employeeService.findEmployee(firstName, lastName);
    }

    @GetMapping("/all")
    public Set<Employee> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

    @ControllerAdvice
    public class GlobalExceptionHandler {

        @ExceptionHandler(EmployeeNotFoundException.class)
        @ResponseStatus(HttpStatus.NOT_FOUND)
        public void handleEmployeeNotFound() {}

        @ExceptionHandler(EmployeeStorageIsFullException.class)
        @ResponseStatus(HttpStatus.BAD_REQUEST)
        public void handleStorageFull() {}

        @ExceptionHandler(EmployeeAlreadyAddedException.class)
        @ResponseStatus(HttpStatus.CONFLICT)
        public void handleAlreadyAdded() {}
    }
}


