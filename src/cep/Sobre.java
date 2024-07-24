package cep;

import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.net.URI;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Sobre extends JDialog {

	/**
	 * Launch the application.
	 */
	
	public static void main(String[] args) {
		try {
			Sobre dialog = new Sobre();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	
	public Sobre() {
		setModal(true);
		setTitle("Sobre");
		setIconImage(Toolkit.getDefaultToolkit().getImage(Sobre.class.getResource("/imgs/5172948_browser_information_internet_security_spy_icon.png")));
		setBounds(150, 150, 450, 300);
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Rastreador de CEP");
		lblNewLabel.setBounds(163, 27, 126, 37);
		getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Autor: Raul Ralph");
		lblNewLabel_1.setBounds(10, 126, 129, 20);
		getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Web Service:");
		lblNewLabel_2.setBounds(10, 144, 84, 30);
		getContentPane().add(lblNewLabel_2);
		
		JLabel lblWebService = new JLabel("republicavirtual.com.br");
		lblWebService.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				acessar("https://www.republicavirtual.com.br/cep/");
			}
		});
		lblWebService.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblWebService.setForeground(SystemColor.textHighlight);
		lblWebService.setBounds(95, 152, 194, 14);
		getContentPane().add(lblWebService);
		
		JButton btnGitHub = new JButton("");
		btnGitHub.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				acessar("https://github.com/raul-bss/");
			}
		});
		btnGitHub.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnGitHub.setBorder(null);
		btnGitHub.setIcon(new ImageIcon(Sobre.class.getResource("/imgs/aa.png")));
		btnGitHub.setToolTipText("GitHub");
		btnGitHub.setBounds(180, 179, 48, 48);
		getContentPane().add(btnGitHub);
		
		JTextArea txtrAplicaoDesktopPara = new JTextArea();
		txtrAplicaoDesktopPara.setBackground(SystemColor.control);
		txtrAplicaoDesktopPara.setFont(new Font("Tahoma", Font.PLAIN, 11));
		txtrAplicaoDesktopPara.setText("Aplicação desktop para busca automatica de CEP com um Web Service.\r\nContem busca otmizada com:\r\n\t Limitação de caracteres para 8\r\n\tCampo de busca aceita apenas numerais.");
		txtrAplicaoDesktopPara.setBounds(28, 62, 385, 71);
		getContentPane().add(txtrAplicaoDesktopPara);
	}
	
	//FIM DO CONSTRUTOR 
	
	private void acessar(String site) {
		Desktop desktop = Desktop.getDesktop();	
		
		try {
			URI uri = new URI(site);
			desktop.browse(uri);
		}catch(Exception e) {
			System.out.println(e);
		}
	}
}
