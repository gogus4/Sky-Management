
import java.io.Console;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Logger;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.ResultSet;
import com.mysql.jdbc.Statement;

public class Authentification {
	public Authentification() {
	}

	public String checkAccount(String login, String password)
			throws IllegalAccessException, ClassNotFoundException {
		String typeUtilisateur = "";

		String pilote = "com.mysql.jdbc.Driver";

		try {
			Class.forName(pilote);

			Connection connexion = (Connection) DriverManager.getConnection(
					"jdbc:mysql://localhost/skymanagement", "root", "");

			Statement instruction = (Statement) connexion.createStatement();

			ResultSet resultat = (ResultSet) instruction
					.executeQuery("SELECT count(*),typeUtilisateur FROM employe where email = '"
							+ login + "' AND password = '" + password + "'");

			while (resultat.next()) {
				if (resultat.getInt(1) == 1) {
					System.out.println("Authentification correct");
					return resultat.getString(2);
				}

				else
					System.out.println("Authentification incorrect");
			}
		} catch (Exception e) {

			System.out.println("echec pilote : " + e);
		}

		return typeUtilisateur;
	}
}

