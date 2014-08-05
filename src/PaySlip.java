import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import java.io.Console;
import java.io.File;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Logger;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.ResultSet;
import com.mysql.jdbc.Statement;

public class PaySlip 
{
	private int id;
	private String lienPDF;
	private String date; // 2013-04-22
	private int idUtil;

	private static String pilote = "com.mysql.jdbc.Driver";
	private static String url = "jdbc:mysql://localhost/skymanagement";
	private static String user = "root";
	private static String password = "";
	private static Statement stmt;
	private static Connection con;
	
	// TEST
	static JLabel label_intro = new JLabel("Veuillez entrer les éléments relatif à  l'enregistrement d'une nouvelle fiche de paye");
	static JLabel label_link = new JLabel("Emplacement du fichier à  importer");
	static JButton search = new JButton("Recherche");
	static JLabel label_date = new JLabel("Date");
	static JLabel label_employe = new JLabel("Employé concerné");
	static JTextField textField_link = new JTextField("");
	static JTextField textField_date = new JTextField("");
	static JButton save = new JButton("Enregistrer");
	static JPanel p = new JPanel();

	static String[] tab = {"Diego", "Cedric", "Eliott"};
	static int[] idTab = {2, 3, 4};
	static JComboBox textField_employe;
	static JLabel end = new JLabel("La nouvelle fiche de paye à bien été enregistrée.");

	public PaySlip(){}

	
	public PaySlip(int i, String l, String d, int iu){
		id = i;
		lienPDF = l;
		idUtil = iu;
		date = d;
	}	

	public void afficheOne(){
		System.out.println("id : " + this.id + "  #  link : " + this.lienPDF + "  #  idUtil : " + this.idUtil + "  #  date : " + this.date  );
	}

	public static Connection getConnection(){

		try{
			try {
				Class.forName(pilote);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			con = (Connection) DriverManager.getConnection(url, user, password);
		}catch(SQLException e){
			e.printStackTrace();
		}

		return con;
	}

	public void add(){
		String insert = "INSERT INTO `payslip` (`idPaySlip`, `linkPDF`, `datePaySlip`, `idUtilPS`) VALUES (NULL, '" + this.lienPDF + "', '" + this.date + "', '" + this.idUtil + "');";

		try{
			Connection con = getConnection();
			stmt = (Statement) con.createStatement();
			stmt.executeUpdate(insert);
			stmt.close();
			
		}catch (Exception e){
			System.out.println("echec pilote : " + e);
			e.printStackTrace();
		}
	}

	public void update(){
		String update = "UPDATE payslip SET  datePaySlip =  '" + this.date + "',  linkPDF =  '" + this.lienPDF + "',  idUtilPS =  " + this.idUtil + "  WHERE  idPaySlip = " + this.id + ";";

		try{
			Connection con = getConnection();
			stmt = (Statement) con.createStatement();
			stmt.executeUpdate(update);
			stmt.close();
			
		}catch (Exception e){
			System.out.println("echec pilote : " + e);
			e.printStackTrace();
		}
		
	}

	public void getOneById(int idToGet){
		String select = "SELECT * FROM payslip WHERE idPaySlip = '" + idToGet + "';";
		System.out.println(select);

		try{
			Connection con = getConnection();
			stmt = (Statement) con.createStatement();
			ResultSet resultat = (ResultSet) stmt.executeQuery(select);
			
			while (resultat.next()){
				System.out.println(resultat.getString("linkPDF"));
				System.out.println(resultat.getString("datePaySlip"));
				System.out.println(resultat.getInt("idPaySlip"));
				System.out.println(resultat.getInt("idUtilPS"));

				this.id = resultat.getInt("idPaySlip");
				this.lienPDF = resultat.getString("linkPDF");
				this.date = resultat.getString("datePaySlip");
				this.idUtil = resultat.getInt("idUtilPS");

			}
			stmt.close();
			
		}catch( SQLException e){
			System.out.println("Echec : " + e);
			e.printStackTrace();
		}
	}

	public ArrayList<PaySlip> getAll(){
		String select = "SELECT * FROM payslip;";
		System.out.println(select);
		ArrayList<PaySlip> payslips = new ArrayList<PaySlip>();

		try{
			Connection con = getConnection();
			stmt = (Statement) con.createStatement();
			ResultSet resultat = (ResultSet) stmt.executeQuery(select);

			while(resultat.next()){
				payslips.add(new PaySlip(resultat.getInt(1), resultat.getString(2), resultat.getString(3), resultat.getInt(4)));
			}//id, lien, date, idutil

			for(int i = 0; i < payslips.size(); i ++)
				payslips.get(i).afficheOne();

		}catch(SQLException e){
			e.printStackTrace();
		}
		return payslips;
	}

	public static void create(final JPanel p2){

		
		//JTextField textField_employe = new JTextField("liste des employés");
		/*Employe guy = new Employe();
		for (int i = 0; i < guy.emp.size(); i++){
			tab[i] = guy.emp.get(i).firstName + " " + guy.emp.get(i).lastName;
			idTab[i] = guy.emp.get(i).idUtilisateur;
		}*/
		textField_employe = new JComboBox(tab);
		
		//JFrame test = new JFrame();
		/*
		test.setSize(800, 800);
		test.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		test.setContentPane(p);*/

		textField_link.setPreferredSize(new Dimension(200, 30));

		GridBagLayout gbl = new GridBagLayout();
		p.setLayout(gbl);
		
		final GridBagConstraints gbc = new GridBagConstraints();
		
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 3 ;
		gbc.gridheight = 1 ;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		p.add(label_intro, gbc);

		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.gridwidth = 1 ;
		gbc.gridheight = 1 ;
		p.add(label_link, gbc);

		gbc.gridx = 1;
		gbc.gridy = 2;
		gbc.gridwidth = 1 ;
		gbc.gridheight = 1 ;
		//gbc.fill = GridBagConstraints.BOTH;
		p.add(textField_link, gbc);

		gbc.gridx = 2;
		gbc.gridy = 2;
		gbc.gridwidth = 1 ;
		gbc.gridheight = 1 ;
		p.add(search, gbc);

		gbc.gridx = 0;
		gbc.gridy = 3;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		p.add(label_date, gbc);

		gbc.gridx = 1;
		gbc.gridy = 3;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		p.add(textField_date, gbc);

		gbc.gridx = 0;
		gbc.gridy = 4;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		p.add(label_employe, gbc);

		gbc.gridx = 1;
		gbc.gridy = 4;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		p.add(textField_employe, gbc);

		gbc.gridx = 2;
		gbc.gridy = 5;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbc.fill = GridBagConstraints.BOTH;
		p.add(save, gbc);
		
		//test.setVisible(true);

		final JFileChooser fc = new JFileChooser();
	
		search.addMouseListener(new MouseAdapter(){

			public void mouseClicked(MouseEvent arg0) {
				int returnVal = fc.showOpenDialog(fc);
				
				if (returnVal == JFileChooser.APPROVE_OPTION){
					File file = fc.getSelectedFile();
					if (file.getPath().endsWith(".pdf"))
						textField_link.setText(file.getPath());
					else
						textField_link.setText("Extention du fichier non valide");
				}
			}
		});

		textField_date.addMouseListener(new MouseAdapter(){

			public void mouseClicked(MouseEvent arg0) {
				Date date = new Date();
				DateFormat formate = new SimpleDateFormat("yyyy-MM-dd");
				String now = formate.format(date);
				textField_date.setText(now);

			}
		});

		save.addMouseListener(new MouseAdapter(){

			public void mouseClicked(MouseEvent e)
			{
				PaySlip tosave = new PaySlip();
				tosave.lienPDF = textField_link.getText();
				tosave.date = textField_date.getText();
				//System.out.println("contenu : " + textField_employe.getSelectedItem());
				for(int i = 0; i < tab.length; i++)
				{
					if (textField_employe.getSelectedItem() == tab[i])
					{
						tosave.idUtil = idTab[i];
					}
				}
				
				tosave.afficheOne();
				tosave.add();
				p.removeAll();

				p2.repaint();
				p.add(end, gbc);
			}
		});

		p2.add(p, BorderLayout.CENTER);
	}

	public void displayAll(ArrayList<PaySlip> ps){
		for(int i = 0; i < ps.size(); i++){
			ps.get(i).afficheOne();
		}
	}

	/*public static void main(String [] argc){

		//PaySlip test = new PaySlip(1, "docs/yo.pdf", "2013-04-22", 1);
		//test.addPaySlip();

		PaySlip recup = new PaySlip();
		//recup.getOneById(3);
		//recup.update();
		//recup.afficheOne();
		recup.getAll();

		create();

	}*/
}