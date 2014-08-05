import java.sql.DriverManager;
import java.util.ArrayList;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;


public class Enterprise 
{
	public Enterprise()
	{
		
	}
	
	public static ArrayList<ScheduleEvent> getAllScheduleEvent()
	{
		ArrayList<ScheduleEvent> schedule = new ArrayList<ScheduleEvent>();
		
		String pilote = "com.mysql.jdbc.Driver";

		try 
		{
			Class.forName(pilote);

			Connection connexion = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/skymanagement", "root", "");

			com.mysql.jdbc.Statement instruction = (Statement) connexion.createStatement();

			com.mysql.jdbc.ResultSet resultat = (com.mysql.jdbc.ResultSet)instruction.executeQuery("select descriptionEvent,dateEvent from sheduleevent where idUtilEvent is null");
			
			while (resultat.next()) 
			{
					ScheduleEvent event = new ScheduleEvent();
					event.libelle = resultat.getString(1);
										
					event.year = resultat.getString(2).substring(0,4);
					event.month = resultat.getString(2).substring(5,7);					
					event.day = resultat.getString(2).substring(8,10);
					
					schedule.add(event);
			}
		} catch (Exception e) {

			System.out.println("echec pilote : " + e);
		}
		
		return schedule;
	}
}
