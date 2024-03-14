package jdbc;

import java.awt.Choice;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Label;
import java.awt.TextArea;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.Scanner;

public class Data_Dictionary {
	public static void main(String[] args) {
		Frame frame = new Frame();
		Label heading = new Label("Data Dictionary");
		heading.setFont(new Font("Times New Roman", Font.BOLD, 18));
		heading.setBounds(300, 50, 200, 30);
		frame.add(heading);
		Choice tables_choice = new Choice();
		tables_choice.setBounds(50, 100, 300, 100);
		Choice columns_choice = new Choice();
		columns_choice.setBounds(400, 100, 300, 100);

		Label sideheading = new Label("Constraints");
		sideheading.setFont(new Font("Times New Roman", Font.BOLD, 18));
		sideheading.setBounds(50, 300, 500, 100);
		frame.add(sideheading);
		TextArea textarea = new TextArea();
		textarea.setBounds(50, 400, 500, 250);
		frame.add(textarea);

		try {
			Scanner sc = new Scanner(System.in);
			Class.forName("org.postgresql.Driver");
			String url = "jdbc:postgresql://192.168.110.48:5432/plf_training?user=plf_training_admin&password=pff123";
			Connection con = DriverManager.getConnection(url);
			DatabaseMetaData dmd = con.getMetaData();
			ResultSet tables = dmd.getTables(null, null, "%", new String[] { "TABLE" });
			while (tables.next()) {
				String tableName = tables.getString("TABLE_NAME");
				tables_choice.add(tableName);
			}

			tables_choice.addItemListener(new ItemListener() {
				public void itemStateChanged(ItemEvent e) {
					columns_choice.removeAll();
					String selectedTable = tables_choice.getSelectedItem();

					try {
						ResultSet columns = dmd.getColumns(null, null, selectedTable, null);
						while (columns.next()) {
							String columnName = columns.getString("COLUMN_NAME");
							columns_choice.add(columnName);

						}

					} catch (Exception ex) {
						ex.printStackTrace();
					}
				}
			});

		} catch (Exception e) {
			e.printStackTrace();
		}
		frame.add(tables_choice);
		frame.add(columns_choice);
		frame.setSize(1500, 1500);
		frame.setLayout(null);
		frame.setVisible(true);
	}

}
