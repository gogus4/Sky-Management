import java.awt.List;
import java.sql.DriverManager;
import java.util.ArrayList;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;


public class ScheduleEvent 
{
	public String month;
	public String year;
	public String day;
	public String libelle;
	
	public ScheduleEvent()
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

			com.mysql.jdbc.ResultSet resultat = (com.mysql.jdbc.ResultSet)instruction.executeQuery("select descriptionEvent,dateEvent from sheduleevent where idUtilEvent is null  order by DateEvent asc");
			
			while (resultat.next()) 
			{
					//System.out.println("Description evenement : " + resultat.getString(1));
					//System.out.println("Date evenement : " + resultat.getString(2));
					
					ScheduleEvent event = new ScheduleEvent();
					event.libelle = resultat.getString(1);
										
					event.year = resultat.getString(2).substring(0,4);
					
					//System.out.println("Year : " + event.year);
					
					event.month = resultat.getString(2).substring(5,7);
					//System.out.println("Month : " + event.month);
					
					event.day = resultat.getString(2).substring(8,10);
					//System.out.println("Day : " + event.day);
					
					schedule.add(event);
			}
		} catch (Exception e) {

			System.out.println("echec pilote : " + e);
		}
		
		return schedule;
	}
}
