package jdbc;


import java.awt.*;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Employee_crud {

	public static void main(String[] args) {
		 Frame frame = new Frame();
		 
		 /* Employee crud heading*/
		 Label heading=new Label("Employee CRUD");
		 heading.setFont(new Font("Times New Roman", Font.BOLD, 18));

		 heading.setBounds(260,50,200,30);
		 frame.add(heading);
		 
		 /* left side headings*/
		 Label empno=new Label("Emp No: ");
		 empno.setFont(new Font("Times New Roman", Font.BOLD, 18)); 
		 empno.setBounds(50,100,100,30);
		 frame.add(empno);
		 
		 Label job=new Label("Job: ");
		 job.setFont(new Font("Times New Roman", Font.BOLD, 18)); 
		 job.setBounds(50,150,100,30);
		 frame.add(job);
		 
		 Label deptno=new Label("Dept No: ");
		 deptno.setFont(new Font("Times New Roman", Font.BOLD, 18)); 
		 deptno.setBounds(50,200,100,30);
		 frame.add(deptno);
		 
		 
		 /* right side headings*/
		 Label name=new Label("Name: ");
		 name.setFont(new Font("Times New Roman", Font.BOLD, 18)); 
		 name.setBounds(450,100,100,30);
		 frame.add(name);
		 
		 
		 Label salary=new Label("Salary: ");
		 salary.setFont(new Font("Times New Roman", Font.BOLD, 18)); 
		 salary.setBounds(450,150,100,30);
		 frame.add(salary);
		 
		 
		 Label hiredate=new Label("Hiredate: ");
		 hiredate.setFont(new Font("Times New Roman", Font.BOLD, 18)); 
		 hiredate.setBounds(450,200,100,30);
		 frame.add(hiredate);
		 
		 /* Buttons*/
		 
		 Button first = new Button("First"); 
		 first.setBounds(100,300,50,50);    
		 frame.add(first);
		 
		 Button Next = new Button("Next"); 
		 Next.setBounds(200,300,50,50);    
		 frame.add(Next);
		 
		 Button Prev = new Button("Prev"); 
		 Prev.setBounds(300,300,50,50);    
		 frame.add(Prev);
		 
		 Button Last = new Button("Last"); 
		 Last.setBounds(400,300,50,50);    
		 frame.add(Last);
		 
		 Button Add = new Button("Add"); 
		 Add.setBounds(100,400,50,50);    
		 frame.add(Add);
		 
		 Button Edit = new Button("Edit"); 
		 Edit.setBounds(200,400,50,50);    
		 frame.add(Edit);
		 
		 Button Del = new Button("Delete"); 
		 Del.setBounds(300,400,50,50);    
		 frame.add(Del);
		 
		 Button Save = new Button("Save"); 
		 Save.setBounds(400,400,50,50);    
		 frame.add(Save);
		 
		 Button Clear = new Button("Clear"); 
		 Clear.setBounds(200,500,50,50);    
		 frame.add(Clear);
		 
		 Button Exit = new Button("Exit"); 
		 Exit.setBounds(300,500,50,50);    
		 frame.add(Exit);
		 
		 /* TextField*/
		 
		 TextField tfempno=new TextField();
		 tfempno.setBounds(150,100,200,30);
		 frame.add(tfempno);
		 
		 TextField tfjob=new TextField();
		 tfjob.setBounds(150,150,200,30);
		 frame.add(tfjob);
		 
		 TextField tfdeptno=new TextField();
		 tfdeptno.setBounds(150,200,200,30);
		 frame.add(tfdeptno);
		 
		 TextField tfname=new TextField();
		 tfname.setBounds(550,100,200,30);
		 frame.add(tfname);
		 
		 TextField tfsalary=new TextField();
		 tfsalary.setBounds(550,150,200,30);
		 frame.add(tfsalary);
		 
		 TextField tfhiredate=new TextField();
		 tfhiredate.setBounds(550,200,200,30);
		 frame.add(tfhiredate);
		 
		 Label heading2=new Label("Employee Details:");
		 heading2.setBounds(50,600,200,30);
		 heading2.setFont(new Font("Times New Roman", Font.BOLD, 18));
		 frame.add(heading2);
		 
		 TextArea area =new TextArea();
		 area.setBounds(50, 650, 800, 150);
		 area.setEditable(false);
		 frame.add(area);
		 
		 
		 frame.setSize(1500,1500);    
		 frame.setLayout(null);    
		 frame.setVisible(true); 
		 
		 
		 try {
			 Class.forName("org.postgresql.Driver");
			    String url = "jdbc:postgresql://localhost/postgres?user=postgres&password=Satish@4119";
			    Connection con = DriverManager.getConnection(url);
			    Statement stmt=con.createStatement();
			    String query = "SELECT * FROM employees";
	            ResultSet rs = stmt.executeQuery(query);
	            while (rs.next()) {
	                int empid1=rs.getInt("empid");
	                String empname1=rs.getString("empname");
	                String job1=rs.getString("job");
	                Date date1=rs.getDate("hiredate");
	                int salary1=rs.getInt("salary");
	                int dept_no1=rs.getInt("dept_no");
	                String record = String.format("%-10d%-20s%-20s%-15s%-10d%-10d\n", empid1, empname1, job1, date1, salary1, dept_no1);
	                area.append(record);
	            }	
			    
			    
		 }catch(Exception e) {
			 e.printStackTrace();
		 }
		 
	}

}
