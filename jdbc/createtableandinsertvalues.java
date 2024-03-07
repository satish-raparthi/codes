package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

public class createtableandinsertvalues {

	public static void main(String[] args) {
		EmployeeGroup empgrp = new EmployeeGroup();
		List<Employee> employees = empgrp.employees;
		try {
			Class.forName("org.postgresql.Driver");
			String url = "jdbc:postgresql://192.168.110.48:5432/plf_training?user=plf_training_admin&password=pff123";
			Connection con = DriverManager.getConnection(url);
			if (con != null) {
				System.out.println("memory allocated");
			}
			con.setAutoCommit(false);
			Statement stmt = con.createStatement();
			String table = "create table SAT_NEW_EMPLOYEE(empid int, FullName varchar(20), Salary int, Designation varchar(20), JoiningDate varchar(30), Expeience int)";
			System.out.println("Table created succesfully");
			PreparedStatement ps = con.prepareStatement("insert into SAT_NEW_EMPLOYEE values(?,?,?,?,?,?)");

			for (Employee e : employees) {
				ps.setInt(1, e.getEmpid());
				ps.setString(2, e.getFullName());
				ps.setInt(3, e.getSalary());
				ps.setString(4, e.getDesignation());
				ps.setString(5, e.getJoiningDate());
				ps.setInt(6, e.getExperience());
				ps.addBatch();
			}
			System.out.println("Data Inserted Successfully");
			// stmt.execute(table);
			int[] i = ps.executeBatch();

			System.out.println(i.length + " records inserted");
			stmt.close();
			con.commit();
			con.close();

		} catch (Exception e) {
			System.err.println(e.getClass() + e.getMessage());
			System.exit(0);
		}
		System.out.println("Records created successfully");

	}

}
