package jdbc;

import java.awt.Button;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Label;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class Employee_crud {

	public static void main(String[] args) {
		Frame frame = new Frame();

		/* Employee crud heading */
		Label heading = new Label("Employee CRUD");
		heading.setFont(new Font("Times New Roman", Font.BOLD, 18));

		heading.setBounds(260, 50, 200, 30);
		frame.add(heading);

		/* left side headings */
		Label empno = new Label("Emp No: ");
		empno.setFont(new Font("Times New Roman", Font.BOLD, 18));
		empno.setBounds(50, 100, 100, 30);
		frame.add(empno);

		Label job = new Label("Job: ");
		job.setFont(new Font("Times New Roman", Font.BOLD, 18));
		job.setBounds(50, 150, 100, 30);
		frame.add(job);

		Label deptno = new Label("Dept No: ");
		deptno.setFont(new Font("Times New Roman", Font.BOLD, 18));
		deptno.setBounds(50, 200, 100, 30);
		frame.add(deptno);

		/* right side headings */
		Label name = new Label("Name: ");
		name.setFont(new Font("Times New Roman", Font.BOLD, 18));
		name.setBounds(450, 100, 100, 30);
		frame.add(name);

		Label salary = new Label("Salary: ");
		salary.setFont(new Font("Times New Roman", Font.BOLD, 18));
		salary.setBounds(450, 150, 100, 30);
		frame.add(salary);

		Label hiredate = new Label("Hiredate: ");
		hiredate.setFont(new Font("Times New Roman", Font.BOLD, 18));
		hiredate.setBounds(450, 200, 100, 30);
		frame.add(hiredate);

		/* Buttons */

		Button first = new Button("First");
		first.setBounds(100, 300, 50, 50);
		frame.add(first);

		Button Next = new Button("Next");
		Next.setBounds(200, 300, 50, 50);
		frame.add(Next);

		Button Prev = new Button("Prev");
		Prev.setBounds(300, 300, 50, 50);
		frame.add(Prev);

		Button Last = new Button("Last");
		Last.setBounds(400, 300, 50, 50);
		frame.add(Last);

		Button Add = new Button("Add");
		Add.setBounds(100, 400, 50, 50);
		frame.add(Add);

		Button Edit = new Button("Edit");
		Edit.setBounds(200, 400, 50, 50);
		frame.add(Edit);

		Button Del = new Button("Delete");
		Del.setBounds(300, 400, 50, 50);
		frame.add(Del);

		Button Save = new Button("Save");
		Save.setBounds(400, 400, 50, 50);
		frame.add(Save);

		Button Clear = new Button("Clear");
		Clear.setBounds(200, 500, 50, 50);
		frame.add(Clear);

		Button Exit = new Button("Exit");
		Exit.setBounds(300, 500, 50, 50);
		frame.add(Exit);

		/* TextField */

		TextField tfempno = new TextField();
		tfempno.setBounds(150, 100, 200, 30);
		frame.add(tfempno);

		TextField tfjob = new TextField();
		tfjob.setBounds(150, 150, 200, 30);
		frame.add(tfjob);

		TextField tfdeptno = new TextField();
		tfdeptno.setBounds(150, 200, 200, 30);
		frame.add(tfdeptno);

		TextField tfname = new TextField();
		tfname.setBounds(550, 100, 200, 30);
		frame.add(tfname);

		TextField tfsalary = new TextField();
		tfsalary.setBounds(550, 150, 200, 30);
		frame.add(tfsalary);

		TextField tfhiredate = new TextField();
		tfhiredate.setBounds(550, 200, 200, 30);
		frame.add(tfhiredate);

		Label heading2 = new Label("Employee Details:");
		heading2.setBounds(50, 600, 200, 30);
		heading2.setFont(new Font("Times New Roman", Font.BOLD, 18));
		frame.add(heading2);

		DefaultTableModel model = new DefaultTableModel();
		String[] columns = { "Emp No", "Name", "Job", "Hiredate", "Salary", "Dept No" };
		for (String column : columns) {
			model.addColumn(column);
		}

		JTable table = new JTable(model);

		table.setFont(new Font("Monospaced", Font.PLAIN, 12));
		table.setRowHeight(20);

		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(50, 650, 800, 150);
		frame.add(scrollPane);

		try {
			Class.forName("org.postgresql.Driver");
			String url = "jdbc:postgresql://192.168.110.48:5432/plf_training?user=plf_training_admin&password=pff123";
			Connection con = DriverManager.getConnection(url);
			Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			String query = "SELECT * FROM sat_employees";
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				int empid1 = rs.getInt("empid");
				String empname1 = rs.getString("empname");
				Date date1 = rs.getDate("hiredate");
				int salary1 = rs.getInt("salary");
				int dept_no1 = rs.getInt("deptno");
				String job1 = rs.getString("job");
				model.addRow(new Object[] { empid1, empname1, job1, date1, salary1, dept_no1 });

			}
			first.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						if (rs.first()) {
							int empid = rs.getInt("empid");
							String empname = rs.getString("empname");
							Date date = rs.getDate("hiredate");
							int salary = rs.getInt("salary");
							int dept_no = rs.getInt("deptno");
							String job = rs.getString("job");
							tfempno.setText(String.valueOf(empid));
							tfname.setText(empname);
							tfhiredate.setText(date.toString());
							tfsalary.setText(String.valueOf(salary));
							tfdeptno.setText(String.valueOf(dept_no));
							tfjob.setText(job);
						} else {
							tfempno.setText("");
							tfname.setText("");
							tfhiredate.setText("");
							tfsalary.setText("");
							tfdeptno.setText("");
							tfjob.setText("");
						}
					} catch (Exception ex) {
						ex.printStackTrace();
					}
				}
			});

			Next.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						if (rs.next()) {
							int empid = rs.getInt("empid");
							String empname = rs.getString("empname");
							Date date = rs.getDate("hiredate");
							int salary = rs.getInt("salary");
							int dept_no = rs.getInt("deptno");
							String job = rs.getString("job");
							tfempno.setText(String.valueOf(empid));
							tfname.setText(empname);
							tfhiredate.setText(date.toString());
							tfsalary.setText(String.valueOf(salary));
							tfdeptno.setText(String.valueOf(dept_no));
							tfjob.setText(job);
						} else {
							tfempno.setText("");
							tfname.setText("");
							tfhiredate.setText("");
							tfsalary.setText("");
							tfdeptno.setText("");
							tfjob.setText("");
						}
					} catch (Exception ex) {
						ex.printStackTrace();
					}
				}
			});

			Prev.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						if (rs.previous()) {
							int empid = rs.getInt("empid");
							String empname = rs.getString("empname");
							Date date = rs.getDate("hiredate");
							int salary = rs.getInt("salary");
							int dept_no = rs.getInt("deptno");
							String job = rs.getString("job");
							tfempno.setText(String.valueOf(empid));
							tfname.setText(empname);
							tfhiredate.setText(date.toString());
							tfsalary.setText(String.valueOf(salary));
							tfdeptno.setText(String.valueOf(dept_no));
							tfjob.setText(job);
						} else {
							tfempno.setText("");
							tfname.setText("");
							tfhiredate.setText("");
							tfsalary.setText("");
							tfdeptno.setText("");
							tfjob.setText("");
						}
					} catch (Exception ex) {
						ex.printStackTrace();
					}
				}
			});
			Last.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						if (rs.last()) {
							int empid = rs.getInt("empid");
							String empname = rs.getString("empname");
							Date date = rs.getDate("hiredate");
							int salary = rs.getInt("salary");
							int dept_no = rs.getInt("deptno");
							String job = rs.getString("job");
							tfempno.setText(String.valueOf(empid));
							tfname.setText(empname);
							tfhiredate.setText(date.toString());
							tfsalary.setText(String.valueOf(salary));
							tfdeptno.setText(String.valueOf(dept_no));
							tfjob.setText(job);
						} else {
							tfempno.setText("");
							tfname.setText("");
							tfhiredate.setText("");
							tfsalary.setText("");
							tfdeptno.setText("");
							tfjob.setText("");
						}
					} catch (Exception ex) {
						ex.printStackTrace();
					}
				}
			});
			Add.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {

				}
			});
			Edit.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {

				}
			});
			Del.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {

				}
			});
			Save.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {

				}
			});
			Clear.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					tfempno.setText("");
					tfname.setText("");
					tfhiredate.setText("");
					tfsalary.setText("");
					tfdeptno.setText("");
					tfjob.setText("");
				}
			});
			Exit.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					frame.dispose();
				}
			});

		} catch (Exception e) {
			e.printStackTrace();
		}
		frame.setSize(1000, 1000);
		frame.setLayout(null);
		frame.setVisible(true);
	}
}