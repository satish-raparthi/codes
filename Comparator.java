package Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Employee {
	private int id;
	private String name;
	private String designation;
	private int Salary;

	public Employee(int id, String name, String designation, int Salary) {
		this.id = id;
		this.name = name;
		this.designation = designation;
		this.Salary = Salary;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getDesignation() {
		return designation;
	}

	public int getSalary() {
		return Salary;
	}

	public static void main(String[] args) {
		ArrayList<Employee> lis = new ArrayList<Employee>();
		lis.add(new Employee(101, "Sat", "ASE", 12000));
		lis.add(new Employee(102, "Raju", "ASE", 12500));
		lis.add(new Employee(103, "Satish", "ASE", 121000));
		lis.add(new Employee(104, "SatRaju", "ASE", 13500));
		Collections.sort(lis, new Comparator<Employee>() {
			public int compare(Employee a1, Employee a2) {
				return a1.getSalary() - a2.getSalary();
			}
		});
		for (Employee e : lis)
			System.out.println(e.getDesignation());

	}
}
