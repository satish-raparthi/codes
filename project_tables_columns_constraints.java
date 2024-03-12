package jdbc;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class project_tables_columns_constraints {
	public static void main(String[] args) {
		String jdbcUrl = "jdbc:postgresql://192.168.110.48:5432/plf_training?user=plf_training_admin&password=pff123";
		JFrame frame = new JFrame("Table and Column Dropdowns");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new FlowLayout());
		JComboBox<String> tableDropdown = new JComboBox<>();
		frame.add(new JLabel("Select Table:"));
		frame.add(tableDropdown);
		JComboBox<String> columnDropdown = new JComboBox<>();
		frame.add(new JLabel("Select Column:"));
		frame.add(columnDropdown);
		try (Connection connection = DriverManager.getConnection(jdbcUrl)) {
			DatabaseMetaData metaData = connection.getMetaData();
			ResultSet tables = metaData.getTables(null, null, "%", new String[] { "TABLE" });
			while (tables.next()) {
				String tableName = tables.getString("TABLE_NAME");
				tableDropdown.addItem(tableName);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		tableDropdown.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String selectedTable = (String) tableDropdown.getSelectedItem();
				if (selectedTable != null) {
					try (Connection connection = DriverManager.getConnection(jdbcUrl)) {
						DatabaseMetaData metaData = connection.getMetaData();
						ResultSet columns = metaData.getColumns(null, null, selectedTable, null);
						Vector<String> columnNames = new Vector<>();
						while (columns.next()) {
							String columnName = columns.getString("COLUMN_NAME");
							columnNames.add(columnName);
						}
						columnDropdown.setModel(new DefaultComboBoxModel<>(columnNames));
					} catch (Exception ex) {
						ex.printStackTrace();
					}
				}
			}
		});
		frame.pack();
		frame.setVisible(true);
	}
}
