package jdbc;

public class Employee {
	private int empid;
	private String FullName;
	private int Salary;
	private String Designation;
	private String JoiningDate;
	private int Experience;

	public Employee(int empid, String fullName, int salary, String designation, String joiningDate, int experience) {
		this.empid = empid;
		FullName = fullName;
		Salary = salary;
		Designation = designation;
		JoiningDate = joiningDate;
		Experience = experience;
	}

	public int getEmpid() {
		return empid;
	}

	public void setEmpid(int empid) {
		this.empid = empid;
	}

	public String getFullName() {
		return FullName;
	}

	public void setFullName(String fullName) {
		FullName = fullName;
	}

	public int getSalary() {
		return Salary;
	}

	public void setSalary(int salary) {
		Salary = salary;
	}

	public String getDesignation() {
		return Designation;
	}

	public void setDesignation(String designation) {
		Designation = designation;
	}

	public String getJoiningDate() {
		return JoiningDate;
	}

	public void setJoiningDate(String joiningDate) {
		JoiningDate = joiningDate;
	}

	public int getExperience() {
		return Experience;
	}

	public void setExperience(int experience) {
		Experience = experience;
	}

}
