
import java.sql.DriverManager;
import java.util.ArrayList;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.ResultSet;
import com.mysql.jdbc.Statement;


public class Employe 
{
	public String lastName;
	public String firstName;
	public String userType;
	public String email;
	public String birthDay;
	public String city;
	public int idUtilisateur;
	public int idManager;
	public int socket;
	public static ArrayList<Employe> emp = new ArrayList<Employe>();

	public Employe()
	{

	}

	public void afficheOne()
	{
		System.out.println("lastName : " + this.lastName + " ### firstName : " + this.firstName + " ### userType : " + this.userType + " ### email : " + this.email + " ### birthDay : " + this.birthDay + " ### city : " + this.city + " ### idUtilisateur : " + this.idUtilisateur + " ### idManager : " + this.idManager);
	}
	
	public static void afficheAll(){
		for (int i = 0; i < emp.size(); i++)
			emp.get(i).afficheOne();
	}
	
	public static ArrayList<Employe> getAllFavoritesEmploye()
	{
		ArrayList<Employe> favoritesEmploye = new ArrayList<Employe>();
		
		String pilote = "com.mysql.jdbc.Driver";

		try {
			Class.forName(pilote);

			Connection connexion = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/skymanagement", "root", "");

			Statement instruction = (Statement) connexion.createStatement();
			
			ResultSet resultat = (ResultSet) instruction.executeQuery("SELECT employe.idUtilisateur,employe.nom,employe.prenom,employe.typeUtilisateur,employe.email,employe.dateNaissance,employe.adresse,employe.idManager FROM `employeefavorites`,`employe` WHERE id_mainUtilisateur = 2 AND employe.idUtilisateur = employeefavorites.id_utilisateur");			
			while (resultat.next()) 
			{
				Employe guy = new Employe();
				guy.idUtilisateur = resultat.getInt(1);
				guy.lastName = resultat.getString(2);
				guy.firstName = resultat.getString(3);
				guy.userType = resultat.getString(4);
				guy.email = resultat.getString(5);
				guy.birthDay = resultat.getString(6);
				guy.city = resultat.getString(7);
				
				if(!(guy.userType.equalsIgnoreCase("MANAGER")))
					guy.idManager = resultat.getInt(8);

				favoritesEmploye.add(guy);
			}
		} catch (Exception e) {

			System.out.println("echec pilote : " + e);
		}
		
		return favoritesEmploye;
	}
	
	public static ArrayList<Employe> getAllEmployeWithOutFavorites()
	{
		ArrayList<Employe> favoritesEmploye = new ArrayList<Employe>();
		
		String pilote = "com.mysql.jdbc.Driver";
		String requete = "WHERE ";
		
		ArrayList<Employe> employe = Employe.getAllFavoritesEmploye();
		
		for(int i = 0 ; i< employe.size() ; i++)
		{
			if(i != employe.size() - 1)
				requete += " idUtilisateur != " + employe.get(i).idUtilisateur + " AND";
			
			else
				requete += " idUtilisateur != " + employe.get(i).idUtilisateur;
		}
		
		if(employe.size() == 0)
			requete = "";
		
		try 
		{
			Class.forName(pilote);

			Connection connexion = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/skymanagement", "root", "");

			Statement instruction = (Statement) connexion.createStatement();

			ResultSet resultat = (ResultSet) instruction.executeQuery("SELECT idUtilisateur,nom,prenom,typeUtilisateur,email,dateNaissance,adresse,idManager FROM employe " + requete);			
			while (resultat.next()) 
			{
				Employe guy = new Employe();
				guy.idUtilisateur = resultat.getInt(1);
				guy.lastName = resultat.getString(2);
				guy.firstName = resultat.getString(3);
				guy.userType = resultat.getString(4);
				guy.email = resultat.getString(5);
				guy.birthDay = resultat.getString(6);
				guy.city = resultat.getString(7);
				
				if(!(guy.userType.equalsIgnoreCase("MANAGER")))
					guy.idManager = resultat.getInt(8);

				favoritesEmploye.add(guy);
			}
		} catch (Exception e) {

			System.out.println("echec pilote : " + e);
		}
		
		return favoritesEmploye;
	}
	
	public static void getAllEmploye()
	{
		emp.removeAll(emp);
		String pilote = "com.mysql.jdbc.Driver";

		try {
			Class.forName(pilote);

			Connection connexion = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/skymanagement", "root", "");

			Statement instruction = (Statement) connexion.createStatement();

			ResultSet resultat = (ResultSet) instruction.executeQuery("SELECT idUtilisateur,nom,prenom,typeUtilisateur,email,dateNaissance,adresse,idManager FROM employe");			
			while (resultat.next()) 
			{
				Employe guy = new Employe();
				guy.idUtilisateur = resultat.getInt(1);
				guy.lastName = resultat.getString(2);
				guy.firstName = resultat.getString(3);
				guy.userType = resultat.getString(4);
				guy.email = resultat.getString(5);
				guy.birthDay = resultat.getString(6);
				guy.city = resultat.getString(7);
				
				if(!(guy.userType.equalsIgnoreCase("MANAGER")))
					guy.idManager = resultat.getInt(8);

				emp.add(guy);
			}
		} catch (Exception e) {

			System.out.println("echec pilote : " + e);
		}
	}
	
	public static void insertEmployeeFavorites(int idUtilisateur)
	{
		String pilote = "com.mysql.jdbc.Driver";

		try 
		{
			Class.forName(pilote);

			Connection connexion = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/skymanagement", "root", "");

			Statement instruction = (Statement) connexion.createStatement();
			String requete = "INSERT INTO employeefavorites VALUES(" + 2 + "," + idUtilisateur + ")";
			int test = instruction.executeUpdate("INSERT INTO employeefavorites VALUES(" + 2 + "," + idUtilisateur + ")");			
			
		} catch (Exception e) {

			System.out.println("echec pilote : " + e);
		}
	}
	
	public static void deleteEmployeeFavorites(int idUtilisateur)
	{
		String pilote = "com.mysql.jdbc.Driver";

		try 
		{
			Class.forName(pilote);

			Connection connexion = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/skymanagement", "root", "");

			Statement instruction = (Statement) connexion.createStatement();
			String requete = "delete from employeefavorites where id_mainUtilisateur =  2 AND id_utilisateur = " + idUtilisateur;
			int test = instruction.executeUpdate(requete);			
			
		} catch (Exception e) {

			System.out.println("echec pilote : " + e);
		}
	}
	
	public String toString() 
	{
		  return lastName + " " +  firstName;
	}
}
