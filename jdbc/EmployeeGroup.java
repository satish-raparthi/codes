package jdbc;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class EmployeeGroup {
	List<Employee> employees = new ArrayList<>();

	public EmployeeGroup() {
		employees.add(new Employee(1, "Sathiraju", 25000, "ASE", "2024 / 01 / 20", 2));
		employees.add(new Employee(2, "Sat", 52000, "IT", "2012 / 10 / 18", 1));
		employees.add(new Employee(3, "Satish", 76000, "SQA", "2022 / 01 / 20", 3));
		employees.add(new Employee(4, "Anjan", 12000, "BA", "2018 / 01 / 23", 4));
		employees.add(new Employee(5, "Ramu", 98000, "SDE", "2021 / 01 / 03", 5));
	}

	public List<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
	}

}
