/**
 * @author $ user{SATHIRAJU RAPARTHI}
 * $ {tags} Employee_crud
 */


package jdbc;

import java.awt.Button;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Label;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

public class Employee_crud {

	public static void main(String[] args) {
		Frame frame = new Frame("SATHIRAJU RAPARTHI");

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
		
		/* Heading 2*/ 
		Label heading2 = new Label("Employee Details:");
		heading2.setBounds(50, 600, 200, 30);
		heading2.setFont(new Font("Times New Roman", Font.BOLD, 18));
		frame.add(heading2);

		/* Table creation using SWING(JTABLE)*/
		
		DefaultTableModel model = new DefaultTableModel();
		
		/* columns creation for JTABLE*/
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
			String url = "jdbc:postgresql://localhost:5432/postgres?user=postgres&password=Satish@4119";
			Connection con = DriverManager.getConnection(url);
			Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			String query = "SELECT * FROM employees";
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
			table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			    public void valueChanged(ListSelectionEvent event) {
			        if (!event.getValueIsAdjusting()) {
			            int selectedRow = table.getSelectedRow();
			            if (selectedRow != -1) {
			                Object empid = table.getValueAt(selectedRow, 0);
			                Object empname = table.getValueAt(selectedRow, 1);
			                Object job = table.getValueAt(selectedRow, 2);
			                Object hiredate = table.getValueAt(selectedRow, 3);
			                Object salary = table.getValueAt(selectedRow, 4);
			                Object dept_no = table.getValueAt(selectedRow, 5);

			                tfempno.setText(String.valueOf(empid));
			                tfname.setText(String.valueOf(empname));
			                tfjob.setText(String.valueOf(job));
			                tfhiredate.setText(String.valueOf(hiredate));
			                tfsalary.setText(String.valueOf(salary));
			                tfdeptno.setText(String.valueOf(dept_no));
			            }
			        }
			    }
			});
			first.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						 if (rs.isFirst()) {
				                JOptionPane.showMessageDialog(frame, "You are already viewing the first record.");
						 }else {
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
						}
						 }
					
					} catch (Exception ex) {
						ex.printStackTrace();
					}
				}
			});

			Next.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						if (rs.isLast()) {
			                JOptionPane.showMessageDialog(frame, "Hey, this is the last record.");
			            }
						else {
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
						} }
					} catch (Exception ex) {
						ex.printStackTrace();
					}
				}
			});

			Prev.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						if (rs.isFirst()) {
			                JOptionPane.showMessageDialog(frame, "Hey, this is the first record.");
			            }
						else {
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
						} }
					} catch (Exception ex) {
						ex.printStackTrace();
					}
				}
			});
			Last.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						if (rs.isLast()) {
			                JOptionPane.showMessageDialog(frame, "You are already viewing the last record.");
			            } 
						else {
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
						} 
						}
					} catch (Exception ex) {
						ex.printStackTrace();
					}
				}
			});
			Add.addActionListener(new ActionListener() {
			    public void actionPerformed(ActionEvent e) {
			        try {
			            int empid = Integer.parseInt(tfempno.getText());
			            String empname = tfname.getText();
			            String job = tfjob.getText();
			            int salary = Integer.parseInt(tfsalary.getText());
			            Date hiredate = Date.valueOf(tfhiredate.getText());
			            int dept_no = Integer.parseInt(tfdeptno.getText());
			            String insertQuery = "INSERT INTO employees(empid, empname, job, salary, hiredate, deptno) VALUES (" +
			                    empid + ", '" + empname + "', '" + job + "', " + salary + ", '" + hiredate + "', " + dept_no + ")";
			            
			            Statement insertStmt = con.createStatement();
			            int rowsAffected = insertStmt.executeUpdate(insertQuery);
			            if (rowsAffected > 0) {
			                model.addRow(new Object[]{empid, empname, job, hiredate, salary, dept_no});
			                JOptionPane.showMessageDialog(frame, "Record added successfully!");
			            } else {
			                JOptionPane.showMessageDialog(frame, "Failed to add record!");
			            }
			        } catch (Exception ex) {
			            ex.printStackTrace();
			        }
			    }
			});

			Edit.addActionListener(new ActionListener() {
			    public void actionPerformed(ActionEvent e) {
			        try {
			            int empid = Integer.parseInt(tfempno.getText());
			            String empname = tfname.getText();
			            String job = tfjob.getText();
			            int salary = Integer.parseInt(tfsalary.getText());
			            Date hiredate = Date.valueOf(tfhiredate.getText()); // Assuming date format is "yyyy-MM-dd"
			            int dept_no = Integer.parseInt(tfdeptno.getText());

			            String updateQuery = "UPDATE employees SET empname='" + empname + "', job='" + job + "', salary=" + salary +
			                    ", hiredate='" + hiredate + "', deptno=" + dept_no + " WHERE empid=" + empid;
			            Statement updateStmt = con.createStatement();
			            int rowsAffected = updateStmt.executeUpdate(updateQuery);

			            if (rowsAffected > 0) {
			                int selectedRowIndex = table.getSelectedRow();
			                if (selectedRowIndex != -1) {
			                    model.setValueAt(empid, selectedRowIndex, 0);
			                    model.setValueAt(empname, selectedRowIndex, 1);
			                    model.setValueAt(job, selectedRowIndex, 2);
			                    model.setValueAt(hiredate, selectedRowIndex, 3);
			                    model.setValueAt(salary, selectedRowIndex, 4);
			                    model.setValueAt(dept_no, selectedRowIndex, 5);
			                    JOptionPane.showMessageDialog(frame, "Record updated successfully!");
			                } else {
			                    JOptionPane.showMessageDialog(frame, "Please select a row to edit!");
			                }
			            } else {
			                JOptionPane.showMessageDialog(frame, "Failed to update record!");
			            }
			        } catch (Exception ex) {
			            ex.printStackTrace();
			        }
			    }
			});

			Del.addActionListener(new ActionListener() {
			    public void actionPerformed(ActionEvent e) {
			        try {
			            int selectedRowIndex = table.getSelectedRow();
			            if (selectedRowIndex != -1) {
			                int empid = (int) table.getValueAt(selectedRowIndex, 0);

			                String deleteQuery = "DELETE FROM employees WHERE empid=" + empid;
			                Statement deleteStmt = con.createStatement();
			                int rowsAffected = deleteStmt.executeUpdate(deleteQuery);

			                if (rowsAffected > 0) {
			                	
			                    model.removeRow(selectedRowIndex);
			                    JOptionPane.showMessageDialog(frame, "Record deleted successfully!");
			                } else {
			                    JOptionPane.showMessageDialog(frame, "Failed to delete record!");
			                }
			            } else {
			                JOptionPane.showMessageDialog(frame, "Please select a row to delete!");
			            }
			        } catch (Exception ex) {
			            ex.printStackTrace();
			        }
			    }
			});
			Save.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					tfempno.setEditable(false);
			        tfname.setEditable(false);
			        tfjob.setEditable(false);
			        tfsalary.setEditable(false);
			        tfhiredate.setEditable(false);
			        tfdeptno.setEditable(false);
			        
			      
			        first.setEnabled(false);
			        Next.setEnabled(false);
			        Prev.setEnabled(false);
			        Last.setEnabled(false);
			        Add.setEnabled(false);
			        Edit.setEnabled(false);
			        Del.setEnabled(false);
			        Clear.setEnabled(false);
			        JOptionPane.showMessageDialog(frame, "Details have been permanently saved. Modifications are disabled.");
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
			frame.addWindowListener(new WindowAdapter() {
	            public void windowClosing(WindowEvent e) {
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