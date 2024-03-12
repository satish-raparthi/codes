package jdbc;

import java.awt.Choice;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.Vector;

public class project_tables_columns_constraints_awt {
    public static void main(String[] args) {
        String jdbcUrl = "jdbc:postgresql://192.168.110.48:5432/plf_training?user=plf_training_admin&password=pff123";
        Frame frame = new Frame("Table and Column Dropdowns");
        frame.setLayout(new FlowLayout());
        Choice tableDropdown = new Choice();
        frame.add(new Label("Select Table:"));
        frame.add(tableDropdown);
        Choice columnDropdown = new Choice();
        frame.add(new Label("Select Column:"));
        frame.add(columnDropdown);
        try (Connection connection = DriverManager.getConnection(jdbcUrl)) {
            DatabaseMetaData metaData = connection.getMetaData();
            ResultSet tables = metaData.getTables(null, null, "%", new String[]{"TABLE"});
            while (tables.next()) {
                String tableName = tables.getString("TABLE_NAME");
                tableDropdown.add(tableName);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        tableDropdown.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
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
