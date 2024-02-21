package Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;

public class Employee {
	private int id;
	private String name;
	private String designation;
	private int Salary;
	private Date dob;

	public Employee(int id, String name, String designation, int Salary, Date dob) {
		this.id = id;
		this.name = name;
		this.designation = designation;
		this.Salary = Salary;
		this.dob = dob;
	}

	public int getId() {
		return id;
	}

	public Date getDob() {
		return dob;
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
		lis.add(new Employee(101, "Sat", "ASE", 12000, new Date("2003/04/28")));
		lis.add(new Employee(102, "Raju", "ASE", 12500, new Date("2002/02/16")));
		lis.add(new Employee(103, "Satish", "ASE", 121000, new Date("2001/01/20")));
		lis.add(new Employee(104, "SatRaju", "ASE", 13500, new Date("2003/02/23")));
		Collections.sort(lis, new Comparator<Employee>() {
			public int compare(Employee a1, Employee a2) {
				return a1.getDob().compareTo(a2.getDob());
			}
		});
		for (Employee e : lis)
			System.out.println(e.getName());

	}
}
