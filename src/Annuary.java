import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;


public class Annuary extends JTabbedPane implements ActionListener
{
	private GridBagLayout gbl = new GridBagLayout();
	GridBagConstraints gbc = new GridBagConstraints();
	private GridBagLayout gbcDroite = new GridBagLayout();
	private	JButton buttonAdd = new JButton("Ajouter à vos favoris");
	private	JButton buttonDel = new JButton("Supprimer de vos favoris");
	
	
	private DefaultListModel dlmAnnuary = packListBox(); // Remplir la JList
	private DefaultListModel dlmFavorites = packListBoxFavorites(); // Remplir la JList
	private JList listboxAnnuary;
	private JList listboxFavorites;
	private JPanel panel = new JPanel();
	
	public Annuary()
	{
		setVisible(true);
		
		listboxAnnuary = new JList(dlmAnnuary);
		listboxFavorites = new JList(dlmFavorites);
		
		createFirstTab();
		createSecondTab();
		
		buttonAdd.addActionListener((ActionListener) this);
		buttonDel.addActionListener((ActionListener) this);
	}
	
	public void actionPerformed(ActionEvent e) 
	{
		if (e.getSource() == buttonAdd) 
		{
			// Ajout dans la listBox correspondant aux favoris de l'utilisateur et suppression dans la listbox de l'annuaire
			Employe selection = (Employe) listboxAnnuary.getSelectedValue();
			Employe.insertEmployeeFavorites(selection.idUtilisateur);
			
			if(listboxAnnuary.getSelectedValue() != null)
			{
				dlmFavorites.addElement(listboxAnnuary.getSelectedValue());
				dlmAnnuary.removeElement(listboxAnnuary.getSelectedValue());
			}
			
			System.out.println("Prenom : " + selection.firstName);
		}
		
		if (e.getSource() == buttonDel) 
		{
			// Ajout dans la listBox correspondant aux favoris de l'utilisateur et suppression dans la listbox de l'annuaire
			Employe selection = (Employe) listboxFavorites.getSelectedValue();
			
			Employe.deleteEmployeeFavorites(selection.idUtilisateur);
	
			if(listboxFavorites.getSelectedValue() != null)
			{
				dlmAnnuary.addElement(listboxFavorites.getSelectedValue());
				dlmFavorites.removeElement(listboxFavorites.getSelectedValue());
			}
		}
	}
	
	private void createFirstTab()
	{
		//DefaultListModel dlm = packListBox(); // Remplir la JList
		
		listboxAnnuary.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
		panel.setLayout(gbl);
				
		// Ajout des boutons du calendrier
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbc.fill = GridBagConstraints.BOTH;
		panel.add(listboxAnnuary, gbc);
		
		JScrollPane scrollPane = new JScrollPane(listboxAnnuary);
		scrollPane.setSize(230, 200);
		scrollPane.setPreferredSize(new Dimension(410,420));
		
		// Ajout des boutons du calendrier
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbc.fill = GridBagConstraints.BOTH;
		panel.add(scrollPane, gbc);
		
		// Le nom
	    JPanel panNom = new JPanel();
	    panNom.setLayout(gbcDroite);
	    panNom.setPreferredSize(new Dimension(320, 420));
	    panNom.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
	    
	    JLabel nomLabel = new JLabel("Nom ");
	    final JTextField nom = new JTextField();
	    nom.setPreferredSize(new Dimension(200, 25));
	    nom.setEditable(false);
	    
	    JLabel prenomLabel = new JLabel("Prénom ");
	    final JTextField prenom = new JTextField();
	    prenom.setPreferredSize(new Dimension(200, 25));
	    prenom.setEditable(false);
	    
	    JLabel emailLabel = new JLabel("Email ");
	    final JTextField email = new JTextField();
	    email.setPreferredSize(new Dimension(200, 25));
	    email.setEditable(false);
	    
	    JLabel naissanceLabel = new JLabel("Date de naissance ");
	    final JTextField naissance = new JTextField();
	    naissance.setPreferredSize(new Dimension(200, 25));
	    naissance.setEditable(false);
	    
	    JLabel test = new JLabel("");
	    test.setPreferredSize(new Dimension(10,290));
	    
	    	// Label de bourrage
	    JLabel bourrage = new JLabel("");
	    bourrage.setPreferredSize(new Dimension(10,5));
	    
	    JLabel bourrage1 = new JLabel("");
	    bourrage1.setPreferredSize(new Dimension(10,5));
	    
	    JLabel bourrage2 = new JLabel("");
	    bourrage2.setPreferredSize(new Dimension(10,5));
	    
	    gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbc.fill = GridBagConstraints.BOTH;
		panNom.add(nomLabel, gbc);
		
	    gbc.gridx = 1;
		gbc.gridy = 0;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbc.fill = GridBagConstraints.BOTH;
		panNom.add(nom, gbc);
			
	    gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbc.fill = GridBagConstraints.BOTH;
		panNom.add(bourrage1, gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbc.fill = GridBagConstraints.BOTH;
		panNom.add(prenomLabel, gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 2;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbc.fill = GridBagConstraints.BOTH;
		panNom.add(prenom, gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 3;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbc.fill = GridBagConstraints.BOTH;
		panNom.add(bourrage2, gbc);
						
		gbc.gridx = 0;
		gbc.gridy = 4;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		panNom.add(naissanceLabel, gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 4;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		panNom.add(naissance, gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 5;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbc.fill = GridBagConstraints.BOTH;
		panNom.add(bourrage, gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 6;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		panNom.add(emailLabel, gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 6;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		panNom.add(email, gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 7;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		panNom.add(test, gbc);
		
		JPanel panel1 = new JPanel();
		
		gbc.gridx = 1;
		gbc.gridy = 0;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		panel.add(panNom, gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		panel.add(buttonAdd, gbc);
		
		listboxAnnuary.addListSelectionListener(new ListSelectionListener() 
		{
		      public void valueChanged(ListSelectionEvent e) 
		      {
		    	  if(!e.getValueIsAdjusting())
		    	  {
		    		  try
		    		  {
		    		  JList source = (JList)e.getSource();
				      Employe selection = (Employe) source.getSelectedValue();
				      //System.out.println("Nom : " + selection.firstName);
				      
				      nom.setText(selection.lastName);
				      prenom.setText(selection.firstName);
				      naissance.setText(selection.birthDay);
				      email.setText(selection.email);
		    		  }
		    		  catch(Exception e1)
		    		  {
		    			  nom.setText("");
					      prenom.setText("");
					      naissance.setText("");
					      email.setText("");
		    		  }
		    	  }
		      }
		    });
		
		addTab("Annuaire", null, panel, null);
	}
	
	private void createSecondTab()
	{
		JPanel panel2 = new JPanel();
		listboxFavorites.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
		panel2.setLayout(gbl);
				
		// Ajout des boutons du calendrier
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbc.fill = GridBagConstraints.BOTH;
		panel2.add(listboxFavorites, gbc);
		
		JScrollPane scrollPane = new JScrollPane(listboxFavorites);
		scrollPane.setSize(230, 200);
		scrollPane.setPreferredSize(new Dimension(410,420));
		
		// Ajout des boutons du calendrier
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbc.fill = GridBagConstraints.BOTH;
		panel2.add(scrollPane, gbc);
		
		// Le nom
	    JPanel panNom = new JPanel();
	    panNom.setLayout(gbcDroite);
	    panNom.setPreferredSize(new Dimension(320, 420));
	    panNom.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
	    
	    JLabel nomLabel = new JLabel("Nom ");
	    final JTextField nom = new JTextField();
	    nom.setPreferredSize(new Dimension(200, 25));
	    nom.setEditable(false);
	    
	    JLabel prenomLabel = new JLabel("Prénom ");
	    final JTextField prenom = new JTextField();
	    prenom.setPreferredSize(new Dimension(200, 25));
	    prenom.setEditable(false);
	    
	    JLabel emailLabel = new JLabel("Email ");
	    final JTextField email = new JTextField();
	    email.setPreferredSize(new Dimension(200, 25));
	    email.setEditable(false);
	    
	    JLabel naissanceLabel = new JLabel("Date de naissance ");
	    final JTextField naissance = new JTextField();
	    naissance.setPreferredSize(new Dimension(200, 25));
	    naissance.setEditable(false);
	    
	    JLabel test = new JLabel("");
	    test.setPreferredSize(new Dimension(10,290));
	    
	    	// Label de bourrage
	    JLabel bourrage = new JLabel("");
	    bourrage.setPreferredSize(new Dimension(10,5));
	    
	    JLabel bourrage1 = new JLabel("");
	    bourrage1.setPreferredSize(new Dimension(10,5));
	    
	    JLabel bourrage2 = new JLabel("");
	    bourrage2.setPreferredSize(new Dimension(10,5));
	    
	    gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbc.fill = GridBagConstraints.BOTH;
		panNom.add(nomLabel, gbc);
		
	    gbc.gridx = 1;
		gbc.gridy = 0;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbc.fill = GridBagConstraints.BOTH;
		panNom.add(nom, gbc);
			
	    gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbc.fill = GridBagConstraints.BOTH;
		panNom.add(bourrage1, gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbc.fill = GridBagConstraints.BOTH;
		panNom.add(prenomLabel, gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 2;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbc.fill = GridBagConstraints.BOTH;
		panNom.add(prenom, gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 3;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbc.fill = GridBagConstraints.BOTH;
		panNom.add(bourrage2, gbc);
						
		gbc.gridx = 0;
		gbc.gridy = 4;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		panNom.add(naissanceLabel, gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 4;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		panNom.add(naissance, gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 5;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbc.fill = GridBagConstraints.BOTH;
		panNom.add(bourrage, gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 6;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		panNom.add(emailLabel, gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 6;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		panNom.add(email, gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 7;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		panNom.add(test, gbc);
		
		JPanel panel1 = new JPanel();
		
		gbc.gridx = 1;
		gbc.gridy = 0;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		panel2.add(panNom, gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		panel2.add(buttonDel, gbc);
				
		listboxFavorites.addListSelectionListener(new ListSelectionListener() 
		{
		      public void valueChanged(ListSelectionEvent e) 
		      {
		    	  if(!e.getValueIsAdjusting())
		    	  {
		    		  try
		    		  {
		    		  JList source = (JList)e.getSource();
				      Employe selection = (Employe) source.getSelectedValue();
				      //System.out.println("Nom : " + selection.firstName);
				      
				      nom.setText(selection.lastName);
				      prenom.setText(selection.firstName);
				      naissance.setText(selection.birthDay);
				      email.setText(selection.email);
		    		  }
		    		  catch(Exception e1)
		    		  {
		    			  nom.setText("");
					      prenom.setText("");
					      naissance.setText("");
					      email.setText("");
		    		  }
		    	  }
		      }
		    });
		
		addTab("Vos favoris", null, panel2, null);
	}
	
	private DefaultListModel packListBox()
	{
		DefaultListModel dlm = new DefaultListModel();
		dlm.removeAllElements();
		ArrayList<Employe> employe = Employe.getAllEmployeWithOutFavorites();
		
		for(int i = 0 ; i< employe.size() ; i++)
		{
			System.out.println(employe.get(i).firstName);
			dlm.addElement(employe.get(i));
		}
		
		return dlm;
	}
	
	private DefaultListModel packListBoxFavorites()
	{
		DefaultListModel dlm = new DefaultListModel();
		dlm.removeAllElements();
		ArrayList<Employe> employe = Employe.getAllFavoritesEmploye();
		
		for(int i = 0 ; i< employe.size() ; i++)
		{
			System.out.println(employe.get(i).firstName);
			dlm.addElement(employe.get(i));
		}
		
		return dlm;
	}
	
}
