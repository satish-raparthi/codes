package jdbc;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.Scanner;

public class ALLTABLES_new {

    public static void main(String[] args) {
        try {
            Scanner sc = new Scanner(System.in);
            Class.forName("org.postgresql.Driver");
            String url = "jdbc:postgresql://192.168.110.48:5432/plf_training?user=plf_training_admin&password=pff123";
            Connection con = DriverManager.getConnection(url);
            DatabaseMetaData dmd = con.getMetaData();
            ResultSet tables = dmd.getTables(null, null, "%", new String[] { "TABLE" });
            while (tables.next()) {
                String tableName = tables.getString("TABLE_NAME");
                System.out.println("Table name: " + tableName);
            }
            System.out.println();
            System.out.println("--------------------------------------------------------------------------------");
            System.out.println();
            System.out.println("Enter Table Name: ");
            String Tablename = sc.nextLine();
            boolean collength = false;
            ResultSet columns = dmd.getColumns(null, null, Tablename, null);
            while (columns.next()) {
                String colname = columns.getString("COLUMN_NAME");
                collength = true;
                System.out.println("COLUMN NAME:  " + colname);

                // Print constraints for the column
                ResultSet constraints = dmd.getPrimaryKeys(null, null, Tablename);
                while (constraints.next()) {
                    String constraintName = constraints.getString("PK_NAME");
                    System.out.println("Constraint for " + colname + ": " + constraintName);
                }
            }
            if (!collength) {
                System.out.println("No Columns present");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
