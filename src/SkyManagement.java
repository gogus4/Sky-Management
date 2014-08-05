import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseAdapter;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class SkyManagement {
	public static JFrame f = new JFrame();
	private JPanel container = new JPanel();
	private JLabel label_Email = new JLabel("Email");
	private JLabel label_Password = new JLabel("Mot de Passe");
	private JTextField textField_Email = new JTextField("Votre email ...");
	private JTextField textField_Password = new JTextField("Votre mot de passe ...");
	private JButton button_Connection = new JButton("Connection");

	public JToolBar toolBar = new JToolBar();

	public SkyManagement(){
		f.setTitle("Sky Management");
		f.setSize(800,600);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setLocationRelativeTo(null);
		
		// Changement du look and feel
		try {
			UIManager
					.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
			SwingUtilities.updateComponentTreeUI(f);
		} catch (InstantiationException e) {
		} catch (ClassNotFoundException e) {
		} catch (UnsupportedLookAndFeelException e) {
		} catch (IllegalAccessException e) {
		}

		container.setBackground(Color.white);
		container.setLayout(new BorderLayout());

		JPanel center = new JPanel();
		Font police = new Font("Arial", Font.BOLD, 14);
		GridBagLayout gbl = new GridBagLayout();
		center.setLayout(gbl);
		GridBagConstraints gbc = new GridBagConstraints();

		textField_Email.setFont(police);
		textField_Email.setPreferredSize(new Dimension(200, 30));
		textField_Email.setForeground(Color.BLUE);
		textField_Password.setFont(police);
		textField_Password.setPreferredSize(new Dimension(200, 30));
		textField_Password.setForeground(Color.BLUE);

		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		center.add(label_Email, gbc);

		gbc.gridx = 1;
		gbc.gridy = 0;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		center.add(textField_Email, gbc);

		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		center.add(label_Password, gbc);

		gbc.gridx = 1;
		gbc.gridy = 1;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		center.add(textField_Password, gbc);

		gbc.gridx = 2;
		gbc.gridy = 0;
		gbc.gridwidth = 2;
		gbc.gridheight = 2;
		gbc.fill = GridBagConstraints.BOTH;
		center.add(button_Connection, gbc);

		container.setVisible(true);
		container.add(center, BorderLayout.CENTER);

		f.setContentPane(container);

		ActionListener EvenementButtonAuthentification = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String typeUtilisateur = new String();
				Authentification auth = new Authentification();
				try {
					typeUtilisateur = auth.checkAccount(
							textField_Email.getText(),
							textField_Password.getText());

					if (typeUtilisateur != "") 
					{
						System.out.println("Type utilisateur : " + typeUtilisateur);
						PagePrincipal page = new PagePrincipal(typeUtilisateur);
						f.setContentPane(page.p);
					}
				} catch (IllegalAccessException e1) {
					e1.printStackTrace();
				} catch (ClassNotFoundException e1) {
					e1.printStackTrace();
				}
			}
		};
		
		textField_Password.addKeyListener(new KeyAdapter(){
			public void keyPressed(KeyEvent e) 
			{
				if(e.getKeyCode() == KeyEvent.VK_ENTER) 
				{
					String typeUtilisateur = new String();
					Authentification auth = new Authentification();
					try {
						typeUtilisateur = auth.checkAccount(
								textField_Email.getText(),
								textField_Password.getText());

						if (typeUtilisateur != "") 
						{
							PagePrincipal page = new PagePrincipal(typeUtilisateur);
							f.setContentPane(page.p);
						}
					} catch (IllegalAccessException e1) {
						e1.printStackTrace();
					} catch (ClassNotFoundException e1) {
						e1.printStackTrace();
					}
					System.out.println("L'utilisateur a appuyé sur Entrer");
				}
			}
		});
		
		
		textField_Password.addMouseListener(new MouseAdapter() {
			String vide = new String("");

			public void mouseClicked(MouseEvent arg0) {
				textField_Password.setText(vide);
			}

			public void mousePressed(MouseEvent e) {
				textField_Password.setText(vide);
			}
		});

		textField_Email.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				textField_Email.setText("");
			}

			public void mousePressed(MouseEvent e) {
				textField_Email.setText("");
			}
		});
		button_Connection.addActionListener(EvenementButtonAuthentification);

		f.setVisible(true);
	}
}
