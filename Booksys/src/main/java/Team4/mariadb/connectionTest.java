package Team4.mariadb;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Iterator;
import java.util.Vector;

public class connectionTest {
	public void viewtest() {
			System.out.println("����Ŭ ���� mariadb ���� �׽�Ʈ...");
			try {
				Statement stmt = Database.getInstance().getConnection().createStatement();
				System.out.println("����Ŭ ���� mariadb ���� ����");
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		
}


