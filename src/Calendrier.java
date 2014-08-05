import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class Calendrier extends JPanel implements ActionListener {
	public enum month 
	{
		JANVIER(0), FEVRIER(1), MARS(2), AVRIL(3), MAI(4), JUIN(5), JUILLET(6), AOUT(7), SEPTEMBRE(8), OCTOBRE(9), NOVEMBRE(10), DECEMBRE(11);
		private final int value;

		private month(int value) 
		{
			this.value = value;
		}

		public int getValue() 
		{
			return this.value;
		}
	};
	
	private JButton today = new JButton("Today");
	static ArrayList<JLabel> jourSem = new ArrayList<JLabel>();
	static ArrayList<JButton> numMonth = new ArrayList<JButton>();
	static ArrayList<JButton> labelNumMonth = new ArrayList<JButton>();
	private JButton previousMonth = new JButton(new ImageIcon("gauche.png"));
	private JButton nextMonth = new JButton(new ImageIcon("droite.png"));
	private JButton nextEvent = new JButton("Prochain évènement");
	
	ArrayList<ScheduleEvent> event = ScheduleEvent.getAllScheduleEvent();

	JLabel currentMonth = new JLabel("");
	private int monthEnCours, yearEnCours, yearInit, monthInit, dayInit;
	
	private GridBagLayout gbl = new GridBagLayout();
	GridBagConstraints gbc = new GridBagConstraints();

	public Calendrier() 
	{
		// Changement du look and feel
		try {
			UIManager
					.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch (InstantiationException e) {
		} catch (ClassNotFoundException e) {
		} catch (UnsupportedLookAndFeelException e) {
		} catch (IllegalAccessException e) {
		}
		this.removeAll();
		previousMonth.setFocusPainted(false);
		previousMonth.setMargin(null);           
		previousMonth.setBorder(BorderFactory.createEmptyBorder());
		previousMonth.setContentAreaFilled(false);
		
		nextMonth.setFocusPainted(false);
		nextMonth.setMargin(null);           
		nextMonth.setBorder(BorderFactory.createEmptyBorder());
		nextMonth.setContentAreaFilled(false);

		setLayout(new BorderLayout());

		Calendar date = Calendar.getInstance();
		yearInit = date.get(Calendar.YEAR);
		monthInit = date.get(Calendar.MONTH);
		dayInit = date.get(Calendar.DAY_OF_MONTH);

		System.out.println("Year : " + yearInit);
		System.out.println("Month : " + monthInit);
		System.out.println("Day : " + dayInit);

		monthEnCours = date.get(Calendar.MONTH);
		yearEnCours = date.get(Calendar.YEAR);
		jourSem.clear();
		// Création des jours de la semaine :
		jourSem.add(new JLabel("    Dim"));
		jourSem.add(new JLabel("    Lun"));
		jourSem.add(new JLabel("    Mar"));
		jourSem.add(new JLabel("    Mer"));
		jourSem.add(new JLabel("    Jeu"));
		jourSem.add(new JLabel("    Ven"));
		jourSem.add(new JLabel("    Sam"));

		afficheMonth(monthEnCours, yearEnCours);
		this.validate();
		for (int i = 0; i < numMonth.size(); i++)
			numMonth.get(i).addActionListener((ActionListener) this);

		nextMonth.addActionListener((ActionListener) this);
		previousMonth.addActionListener((ActionListener) this);
		today.addActionListener((ActionListener) this);
		nextEvent.addActionListener((ActionListener) this);

		setVisible(true);
	}

	public void actionPerformed(ActionEvent e) 
	{
		if (e.getSource() == previousMonth) 
		{
			// Incrementation du numero de mois
			monthEnCours--;

			if (monthEnCours == -1) {
				monthEnCours = 11;
				yearEnCours--;
			}

			for (int i = 0; i < numMonth.size(); i++)
				numMonth.get(i).removeActionListener((ActionListener) this);

			numMonth.clear();
			labelNumMonth.clear();
			this.removeAll();

			afficheMonth(monthEnCours, yearEnCours);
			this.validate();
			for (int i = 0; i < numMonth.size(); i++)
				numMonth.get(i).addActionListener((ActionListener) this);
		}

		else if (e.getSource() == nextMonth) 
		{
			// Incrémentation du numero de mois
			monthEnCours++;

			if (monthEnCours == 12) 
			{
				monthEnCours = 0;
				yearEnCours++;
			}

			for (int i = 0; i < numMonth.size(); i++)
				numMonth.get(i).removeActionListener((ActionListener) this);

			numMonth.clear();
			labelNumMonth.clear();
			this.removeAll();

			afficheMonth(monthEnCours, yearEnCours);
			this.validate();
			
			for (int i = 0; i < numMonth.size(); i++)
				numMonth.get(i).addActionListener((ActionListener) this);
		}

		else if (e.getSource() == today) {
			for (int i = 0; i < numMonth.size(); i++)
				numMonth.get(i).removeActionListener((ActionListener) this);

			numMonth.clear();
			labelNumMonth.clear();
			this.removeAll();

			monthEnCours = monthInit;
			afficheMonth(monthInit, yearInit);
			this.validate();

			for (int i = 0; i < numMonth.size(); i++)
				numMonth.get(i).addActionListener((ActionListener) this);
			
		}
		
		else if(e.getSource() == nextEvent) 
		{
			for(int i = 0 ; i < event.size() ; i++)
			{
				System.out.println("Evenement : Year : " + Integer.parseInt(event.get(i).year) + " month : " + Integer.parseInt(event.get(i).month));
				System.out.println("Year : " + yearEnCours + " month : " +monthEnCours );
				if(Integer.parseInt(event.get(i).year) >= yearInit && Integer.parseInt(event.get(i).month)-1 > monthInit)
				{
					for (int u = 0; u < numMonth.size(); u++)
						numMonth.get(u).removeActionListener((ActionListener) this);

					numMonth.clear();
					labelNumMonth.clear();
					this.removeAll();
					monthEnCours = Integer.parseInt(event.get(i).month)-1;
					afficheMonth(Integer.parseInt(event.get(i).month)-1,Integer.parseInt(event.get(i).year));
					this.validate();

					for (int h = 0; h < numMonth.size(); h++)
						numMonth.get(h).addActionListener((ActionListener) this);
					
					break;
				}
			}
		}

		else {
			for (int i = 0; i < numMonth.size(); i++) 
			{
				if (e.getSource() == numMonth.get(i)) 
				{
					System.out.println(numMonth.get(i).getText());
					if (i >= 9) {
						for (int j = 0; j < event.size(); j++) {
							if ((monthEnCours + 1) == Integer.parseInt(event.get(j).month)&& Integer.parseInt(numMonth.get(i).getText().substring(6, 8)) == Integer.parseInt(event.get(j).day)&& yearEnCours == Integer.parseInt(event.get(j).year)) {
								JFrame frame = new JFrame();
								JLabel label = new JLabel(
										"Description de l'evenement : ");
								Font font = new Font("Arial", Font.BOLD, 26);
								label.setFont(font);
								frame.setLayout(new BorderLayout());
								frame.add(label, BorderLayout.NORTH);

								JLabel labelDescription = new JLabel(
										"Description de l'evenement : ");
								Font fontDescription = new Font("Arial",
										Font.BOLD, 18);
								labelDescription.setText(event.get(j).libelle);
								labelDescription.setFont(fontDescription);
								frame.add(labelDescription, BorderLayout.CENTER);

								frame.setVisible(true);
								frame.pack();
							}
						}
					}

					else {
						for (int j = 0; j < event.size(); j++) {
							if ((monthEnCours + 1) == Integer.parseInt(event
									.get(j).month)
									&& Integer.parseInt(numMonth.get(i)
											.getText().substring(6, 7)) == Integer
											.parseInt(event.get(j).day)
									&& yearEnCours == Integer.parseInt(event
											.get(j).year)) {
								JFrame frame = new JFrame();
								JLabel label = new JLabel("Description de l'evenement : ");
								Font font = new Font("Arial", Font.BOLD, 26);
								label.setFont(font);
								frame.setLayout(new BorderLayout());
								frame.add(label, BorderLayout.NORTH);

								JLabel labelDescription = new JLabel("Description de l'evenement : ");
								Font fontDescription = new Font("Arial",Font.BOLD, 18);
								labelDescription.setText("Description de l evenement ....................");
								labelDescription.setFont(fontDescription);
								frame.add(labelDescription, BorderLayout.CENTER);

								frame.setVisible(true);
								frame.pack();
							}
						}
					}
				}
			}
		}
	}

	private String recupMonth(int month) {
		switch (month) {
		case 0:
			return "Janvier";
		case 1:
			return "Février";
		case 2:
			return "Mars";
		case 3:
			return "Avril";
		case 4:
			return "Mai";
		case 5:
			return "Juin";
		case 6:
			return "Juillet";
		case 7:
			return "Aout";
		case 8:
			return "Septembre";
		case 9:
			return "Octobre";
		case 10:
			return "Novembre";
		case 11:
			return "Décembre";
		}
		return null;
	}

	private void afficheMonth(int month, int year) 
	{
		this.removeAll();
		this.setLayout(gbl);

		this.removeAll();
		this.repaint();
		Calendar date = Calendar.getInstance();

		date.set(Calendar.MONTH, month);
		date.set(Calendar.YEAR, year);

		int daysInMonth = date.getActualMaximum(Calendar.DAY_OF_MONTH);
		
		// Ajout des boutons du calendrier
		gbc.gridx = 6;
		gbc.gridy = 0;
		gbc.gridwidth = 3;
		gbc.gridheight = 1;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		this.add(nextEvent, gbc);
		
		currentMonth.setText(recupMonth(month) + " " + year);
		Font font = new Font("Arial", Font.BOLD, 26);
		currentMonth.setFont(font);
		currentMonth.setPreferredSize(new Dimension(250, 40));
		currentMonth.setMinimumSize(new Dimension(250, 40));
		gbc.gridx = 3;
		gbc.gridy = 1;
		gbc.gridwidth = 3;
		gbc.gridheight = 1;
		gbc.fill = GridBagConstraints.BOTH;
		this.add(currentMonth, gbc);

		gbc.gridx = 1;
		gbc.gridy = 1;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		this.add(previousMonth, gbc);

		gbc.gridx = 5;
		gbc.gridy = 1;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		this.add(nextMonth, gbc);

		gbc.gridx = 6;
		gbc.gridy = 1;
		gbc.gridwidth = 3;
		gbc.gridheight = 1;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		this.add(today, gbc);

		// Ajout des noms des jours de la semaine
		for (int i = 0; i < jourSem.size(); i++) {
			gbc.gridx = i;
			gbc.gridy = 2;
			gbc.gridwidth = 1;
			gbc.gridheight = 1;
			gbc.fill = GridBagConstraints.HORIZONTAL;

			this.add(jourSem.get(i), gbc);
		}

		int y = 3;
		date.set(Calendar.DAY_OF_MONTH,
				date.getActualMinimum(Calendar.DAY_OF_MONTH));
		java.util.Date debutMois1 = date.getTime();

		int numFirstDay = debutMois1.getDay();
		int h = 0;
		int n = 0;
		n = numFirstDay;

		for (int i = numFirstDay; i < daysInMonth + numFirstDay; i++) {
			String descriptionEvent;

			if ((i - numFirstDay + 1) == dayInit && month == monthInit
					&& year == yearInit) // Test pour savoir si il s'agit du
											// jour d'aujourd'hui
			{
				descriptionEvent = "<HTML><span style='color:red;'>"
						+ (i - numFirstDay + 1)
						+ "</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<br><br><br><HTML>";
			}

			else
				descriptionEvent = "<HTML>"
						+ (i - numFirstDay + 1)
						+ "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<br><br><br><HTML>";

			JButton label = new JButton(descriptionEvent);
			label.setBackground(Color.LIGHT_GRAY);
			label.setBorder(javax.swing.BorderFactory
					.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
			labelNumMonth.add(label);

			// Vérification évènement
			for (int j = 0; j < event.size(); j++) 
			{
				if ((month + 1) == Integer.parseInt(event.get(j).month) && (i - numFirstDay + 1) == Integer.parseInt(event.get(j).day) && year == Integer.parseInt(event.get(j).year)) {
					if ((i - numFirstDay + 1) == dayInit && month == monthInit&& year == yearInit) // Test pour savoir si il// s'agit du jour// d'aujourd'hui
					{
						descriptionEvent = "<HTML><span style='color:red;'>"+ (i - numFirstDay + 1)+ "</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<br><br><b>"+ event.get(j).libelle.substring(0, 12)+ "...</b><br><HTML>";
					}

					else
						descriptionEvent = "<HTML>"+ (i - numFirstDay + 1)+ "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<br><br><b>"+ event.get(j).libelle.substring(0, 12)+ "...</b><br><HTML>";

					label = new JButton(descriptionEvent);
					label.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
					label.setToolTipText(event.get(j).libelle);
					labelNumMonth.set(h, label);
				}
			}
			
			numMonth.add(labelNumMonth.get(h));
			numMonth.get(h).setBackground(Color.black);
			numMonth.get(h).setBorder(BorderFactory.createTitledBorder(BorderFactory.createSoftBevelBorder(2), "", 2, 3,new Font("Arial", Font.BOLD, 12),Color.black)
);
			numMonth.get(h).setContentAreaFilled(false);

			if (i % 7 == 0) {
				y += 1;
				n = 0;
			}

			gbc.gridx = n;
			gbc.gridy = y;
			gbc.gridwidth = 1;
			gbc.gridheight = 1;
			gbc.fill = GridBagConstraints.HORIZONTAL;

			this.add(numMonth.get(h), gbc);
			h++;
			n++;
		}
	}
}
