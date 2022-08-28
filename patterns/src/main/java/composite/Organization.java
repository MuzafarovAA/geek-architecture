package composite;

import java.util.ArrayList;
import java.util.List;

public class Organization {

    private final List<Employee> employees;

    public Organization() {
        this.employees = new ArrayList<>();
    }

    public void addEmployee(Employee employee) {
        this.employees.add(employee);
    }

    public float getNetSalaries() {
        float netSalary = 0;

        for (Employee employee : employees) {
            netSalary += employee.getSalary();
        }

        return netSalary;
    }

}
