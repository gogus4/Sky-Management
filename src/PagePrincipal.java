
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.image.ImageObserver;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.RootPaneContainer;
import javax.swing.SwingConstants;
import java.awt.event.MouseAdapter;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class PagePrincipal implements ActionListener
{
	public static JPanel p = new JPanel();
	public Container c = new Container();
	public static JPanel panelPrincipal = new JPanel();
	

	// Boutons de la toolBar
	private JButton accueil = new JButton(new ImageIcon("home.png")),
			ficheDePayes = new JButton(new ImageIcon("ficheDePaie.png")),
			congesPayes = new JButton(new ImageIcon("vacance.png")),
			compteRendu = new JButton(new ImageIcon("compteRendu.png")),
			calendrier = new JButton(new ImageIcon("calendrier.png")),
			emploiDuTps = new JButton(new ImageIcon("agenda.png")),
			chat = new JButton(new ImageIcon("chat.png")),
			upload = new JButton(new ImageIcon("upload.png")),
			demandeConge = new JButton("Demande de congés payé"),
			creationTache = new JButton("Création de tâche"),
			consultCompteRendu = new JButton("Consulter les comptes rendu"),
			statut = new JButton(new ImageIcon("statut.png")),
			annuaire = new JButton(new ImageIcon("annuaire.png"));

	private JSeparator sep = new JSeparator();
	private JLabel etatUtilisateur = new JLabel("Status : ");
	private JToolBar toolBar = new JToolBar();

	public PagePrincipal(String typeUtilisateur) 
	{		
			// Création du Timer pour mettre à jour le nombre de message de l'utilisateur
		Timer timer = new Timer();
        timer.schedule (new TimerTask() {
            public void run()
            {
                // System.out.printf("%tR\n", new Date());
            	
            	
            }
        }, 0, 5000);
		
		p.setLayout(new BorderLayout());

		accueil.setText("Accueil");
		accueil.setVerticalTextPosition(SwingConstants.BOTTOM);
		accueil.setHorizontalTextPosition(SwingConstants.CENTER);

		calendrier.setText(" ");
		calendrier.setVerticalTextPosition(SwingConstants.BOTTOM);
		calendrier.setHorizontalTextPosition(SwingConstants.CENTER);

		compteRendu.setText("Entreprise");
		compteRendu.setVerticalTextPosition(SwingConstants.BOTTOM);
		compteRendu.setHorizontalTextPosition(SwingConstants.CENTER);

		annuaire.setText(" ");
		annuaire.setVerticalTextPosition(SwingConstants.BOTTOM);
		annuaire.setHorizontalTextPosition(SwingConstants.CENTER);

		congesPayes.setText("Personnel");
		congesPayes.setVerticalTextPosition(SwingConstants.BOTTOM);
		congesPayes.setHorizontalTextPosition(SwingConstants.CENTER);

		chat.setText("Relation");
		chat.setVerticalTextPosition(SwingConstants.BOTTOM);
		chat.setHorizontalTextPosition(SwingConstants.CENTER);

		emploiDuTps.setText(" ");
		emploiDuTps.setVerticalTextPosition(SwingConstants.BOTTOM);
		emploiDuTps.setHorizontalTextPosition(SwingConstants.CENTER);

		ficheDePayes.setText(" ");
		ficheDePayes.setVerticalTextPosition(SwingConstants.BOTTOM);
		ficheDePayes.setHorizontalTextPosition(SwingConstants.CENTER);

		chat.setText("Chat");
		chat.setVerticalTextPosition(SwingConstants.BOTTOM);
		chat.setHorizontalTextPosition(SwingConstants.CENTER);
				
		// Barre d'outils
		toolBar.add(accueil);
		toolBar.add(getSep(Color.white, "1"));
		toolBar.add(getSep(Color.blue, "0"));

		toolBar.add(calendrier);
		toolBar.add(getSep(Color.white, "1"));
		toolBar.add(compteRendu);
		toolBar.add(getSep(Color.white, "1"));
		toolBar.add(annuaire);
		toolBar.add(getSep(Color.white, "1"));
		toolBar.add(getSep(Color.blue, "0"));

		//toolBar.add(emploiDuTps);
		toolBar.add(getSep(Color.white, "1"));
		toolBar.add(congesPayes);
		toolBar.add(getSep(Color.white, "1"));
		toolBar.add(ficheDePayes);
		toolBar.add(getSep(Color.white, "1"));

		toolBar.add(chat);
		toolBar.add(getSep(Color.white, "1"));
		toolBar.add(getSep(Color.white, "1"));
		toolBar.add(getSep(Color.white, "1"));
		toolBar.add(getSep(Color.white, "1"));
		toolBar.add(getSep(Color.white, "1"));
		toolBar.add(getSep(Color.white, "1"));
		toolBar.add(getSep(Color.white, "1"));
		toolBar.add(getSep(Color.white, "1"));
		toolBar.add(getSep(Color.white, "1"));
		toolBar.add(getSep(Color.white, "1"));
		toolBar.add(getSep(Color.white, "1"));
		toolBar.add(getSep(Color.white, "1"));
		toolBar.add(getSep(Color.white, "1"));

		accueil.setToolTipText("ACCUEIL"); // Info bulle

		annuaire.setToolTipText("ANNUAIRE"); // Info bulle

		chat.setToolTipText("CHAT"); // Info bulle

		compteRendu.setToolTipText("COMPTE RENDU"); // Info bulle

		upload.setToolTipText("UPLOAD"); // Info bulle

		congesPayes.setToolTipText("CONGE PAYE"); // Info bulle

		statut.setToolTipText("STATUT"); // Info bulle

		ficheDePayes.setToolTipText("FICHES DE PAYES"); // Info bulle

		calendrier.setToolTipText("CALENDRIER"); // Info bulle

		emploiDuTps.setToolTipText("Emploi du temps"); // Info bulle

		if (typeUtilisateur.equalsIgnoreCase("DRH"))
			toolBarDRH();

		if (typeUtilisateur.equalsIgnoreCase("EMPLOYEE"))
			toolBarEmployee();

		if (typeUtilisateur.equalsIgnoreCase("MANAGER"))
			toolBarManager();

		if (typeUtilisateur.equalsIgnoreCase("ADMIN"))
			toolBarAdmin();
		
		calendrier.addActionListener(this);
		annuaire.addActionListener(this);
		upload.addActionListener(this);
		
		toolBar.setFloatable(false);
		p.add(toolBar,BorderLayout.NORTH);
		p.setSize(800, 600);
	}
	
	public void actionPerformed(ActionEvent e) 
	{
		if (e.getSource() == calendrier) 
		{
			p.remove(panelPrincipal);
			panelPrincipal.removeAll();
			panelPrincipal.repaint();
			
			panelPrincipal = new Calendrier();
			panelPrincipal.validate();
			panelPrincipal.repaint();
			p.add(panelPrincipal,BorderLayout.CENTER);
			
			SkyManagement.f.setContentPane(p);
		}
		
		if (e.getSource() == annuaire) 
		{
			p.remove(panelPrincipal);
			panelPrincipal.removeAll();
			panelPrincipal.repaint();
			
			JTabbedPane onglet = new JTabbedPane();
			onglet.removeAll();
			onglet = new Annuary();
			
			panelPrincipal.add(onglet);
			panelPrincipal.validate();
			panelPrincipal.repaint();
			p.add(panelPrincipal);
			
			SkyManagement.f.setContentPane(p);
		}
		
		if(e.getSource() == upload)
		{
			p.remove(panelPrincipal);
			panelPrincipal.removeAll();
			panelPrincipal.repaint();
			
			System.out.println("UPLOAD");
			
			PaySlip.create(panelPrincipal);
			
			p.add(panelPrincipal);
			panelPrincipal.validate();
			panelPrincipal.repaint();
			
			SkyManagement.f.setContentPane(p);
		}
	}		

	private void toolBarAdmin() 
	{
		statut.setText("Admin");
		statut.setVerticalTextPosition(SwingConstants.BOTTOM);
		statut.setHorizontalTextPosition(SwingConstants.CENTER);
		toolBar.add(statut);
	}

	private void toolBarDRH() 
	{
		System.out.println("OK DRH");
		toolBar.add(upload);
		toolBar.add(demandeConge);

		statut.setText("DRH");
		statut.setVerticalTextPosition(SwingConstants.BOTTOM);
		statut.setHorizontalTextPosition(SwingConstants.CENTER);
		toolBar.add(statut);
	}

	// Eliott
	private void panelDRH()
	{
		PaySlip.create(p);
	}

	private JSeparator getSep(Color color, String test) 
	{
		JSeparator sep1 = new JSeparator();
		if (test.equals("0")) {
			sep1.setBackground(color);
			sep1.setOrientation(SwingConstants.VERTICAL);
			sep1.setForeground(color);
			sep1.setMaximumSize(new Dimension(15, 55));
		}

		else 
		{
			sep1.setBackground(null);
			sep1.setOrientation(SwingConstants.VERTICAL);
			sep1.setForeground(p.getBackground());
			sep1.setMaximumSize(new Dimension(15, 55));
		}

		return sep1;
	}

	private void toolBarManager() {
		toolBar.add(compteRendu);
		toolBar.add(upload);
		toolBar.add(demandeConge);
		toolBar.add(consultCompteRendu);
		toolBar.add(creationTache);

		statut.setText("Manager");
		statut.setVerticalTextPosition(SwingConstants.BOTTOM);
		statut.setHorizontalTextPosition(SwingConstants.CENTER);
		toolBar.add(statut);
	}

	private void toolBarEmployee() {
		statut.setText("Employe");
		statut.setVerticalTextPosition(SwingConstants.BOTTOM);
		statut.setHorizontalTextPosition(SwingConstants.CENTER);
		toolBar.add(statut);
	}
}

