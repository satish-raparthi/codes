package jdbc;

import java.awt.Choice;
import java.awt.Frame;
import java.awt.Label;
import java.awt.TextArea;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.Vector;

public class project_tables_columns_constraints_awt {
	public static void main(String[] args) {
		String jdbcUrl = "jdbc:postgresql://192.168.110.48:5432/plf_training?user=plf_training_admin&password=pff123";
		Frame frame = new Frame("Table and Column Dropdowns");
		frame.setLayout(null);

		Choice tableDropdown = new Choice();
		tableDropdown.setBounds(120, 50, 200, 20);
		frame.add(tableDropdown);

		Label tableLabel = new Label("Select Table:");
		tableLabel.setBounds(20, 50, 100, 20);
		frame.add(tableLabel);

		Choice columnDropdown = new Choice();
		columnDropdown.setBounds(120, 100, 200, 20);
		frame.add(columnDropdown);

		Label columnLabel = new Label("Select Column:");
		columnLabel.setBounds(20, 100, 100, 20);
		frame.add(columnLabel);

		TextArea constraintsTextArea = new TextArea();
		constraintsTextArea.setBounds(20, 150, 300, 100);
		constraintsTextArea.setEditable(false);
		frame.add(constraintsTextArea);

		try (Connection connection = DriverManager.getConnection(jdbcUrl)) {
			DatabaseMetaData metaData = connection.getMetaData();
			ResultSet tables = metaData.getTables(null, null, "%", new String[] { "TABLE" });
			while (tables.next()) {
				String tableName = tables.getString("TABLE_NAME");
				tableDropdown.add(tableName);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		tableDropdown.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				String selectedTable = tableDropdown.getSelectedItem();
				if (selectedTable != null) {
					try (Connection connection = DriverManager.getConnection(jdbcUrl)) {
						DatabaseMetaData metaData = connection.getMetaData();
						ResultSet columns = metaData.getColumns(null, null, selectedTable, null);
						Vector<String> columnNames = new Vector<>();
						while (columns.next()) {
							String columnName = columns.getString("COLUMN_NAME");
							columnNames.add(columnName);
						}
						columnDropdown.removeAll();
						for (String columnName : columnNames) {
							columnDropdown.add(columnName);
						}

						ResultSet constraints = metaData.getColumns(null, null, selectedTable, null);
						StringBuilder constraintsText = new StringBuilder();
						if (!constraints.next()) {
							constraintsText.append("No columns present");
						} else {
							constraints.beforeFirst();
							while (constraints.next()) {
								String columnName = constraints.getString("COLUMN_NAME");
								String columnType = constraints.getString("TYPE_NAME");
								int columnSize = constraints.getInt("COLUMN_SIZE");
								String cons = constraints.getString("CONSTRAINT");
								constraintsText.append("Column Name: ").append(columnName).append(", Type: ")
										.append(columnType).append(", Size: ").append(columnSize)
										.append(", constraints:  ").append(cons).append("\n");
							}
						}
						constraintsTextArea.setText(constraintsText.toString());
					} catch (Exception ex) {
						ex.printStackTrace();
					}
				}
			}
		});

		frame.setSize(350, 300);
		frame.setVisible(true);
	}
}
