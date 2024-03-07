package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DBConnect {
	public static void main(String[] args) {
		try {
			Class.forName("org.postgresql.Driver");
			String url = "jdbc:postgresql://192.168.110.48:5432/plf_training?user=plf_training_admin&password=pff123";

			Connection con = DriverManager.getConnection(url);
			if (con != null)
				System.out.println("Connected......");

			Statement st = con.createStatement();

			ResultSet rs = st.executeQuery("select * from sati12");
			while (rs.next()) {
				System.out.println(rs.getInt(1) + " " + rs.getString(2));
			}
			con.close();

		} catch (Exception e) {
			System.out.println(e);
		}
	}

}
