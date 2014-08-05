import java.sql.SQLException;

import javax.swing.SwingUtilities;

public class Main {
	public static void main(String[] args) throws SQLException 
	{
		Runnable r = new Runnable() {
			public void run() {
				new SkyManagement();
			}
		};

		SwingUtilities.invokeLater(r);
		
	}

}
