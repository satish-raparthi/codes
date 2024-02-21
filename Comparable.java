package Test;

import java.util.ArrayList;
import java.util.Collections;

public class Employee implements Comparable<Employee> {
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

	public int compareTo(Employee s) {
		return this.Salary - s.getSalary();
	}

	public static void main(String[] args) {
		ArrayList<Employee> lis = new ArrayList<Employee>();
		lis.add(new Employee(101, "Sat", "ASE", 12000));
		lis.add(new Employee(102, "Raju", "ASE", 12500));
		lis.add(new Employee(103, "Satish", "ASE", 121000));
		lis.add(new Employee(104, "SatRaju", "ASE", 13500));
		Collections.sort(lis);
		for (Employee e : lis)
			System.out.println(e.getName());

	}
}
