package cep;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.Iterator;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.text.PlainDocument;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import Atxy2k.CustomTextField.RestrictedTextField;
import java.awt.Font;

public class Cep extends JFrame {

	private JPanel contentPane;
	private JTextField txtCep;
	private JTextField txtEndereco;
	private JTextField txtBairro;
	private JTextField txtCidade;
	private JComboBox cboUf;
	private JLabel lblStatus;

	/**
	 * Launch the application.
	 */
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Cep frame = new Cep();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Cep() {
		setBackground(new Color(128, 128, 64));
		setResizable(false);
		setTitle("Buscar CEP");
		setIconImage(Toolkit.getDefaultToolkit().getImage(Cep.class.getResource("/imgs/5172948_browser_information_internet_security_spy_icon.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.window);
		contentPane.setBorder(null);

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		cboUf = new JComboBox();
		cboUf.setModel(new DefaultComboBoxModel(new String[] {"AC", "AL", "AP", "AM", "BA", "CE", "DF", "ES", "GO", "MA", "MT", "MS", "MG", "PA", "PB", "PR", "PE", "PI", "RJ", "RN", "RS", "RO", "RR", "SC", "SP", "SE", "TO"}));
		cboUf.setBounds(273, 151, 52, 22);
		contentPane.add(cboUf);
		
		JLabel lblNewLabel = new JLabel("CEP");
		lblNewLabel.setFont(new Font("SansSerif", Font.BOLD, 12));
		lblNewLabel.setBounds(27, 55, 36, 35);
		contentPane.add(lblNewLabel);
		
		txtCep = new JTextField();
		txtCep.setBounds(83, 62, 139, 21);
		contentPane.add(txtCep);
		txtCep.setColumns(10);
		
		/* Validação campo CEP */
		
		 PlainDocument doc = (PlainDocument) txtCep.getDocument();
	        doc.setDocumentFilter(new FiltroCampoCep(8));
		
		JLabel lblNewLabel_1 = new JLabel("Endereço");
		lblNewLabel_1.setFont(new Font("SansSerif", Font.BOLD, 12));
		lblNewLabel_1.setBounds(27, 97, 58, 14);
		contentPane.add(lblNewLabel_1);
		
		txtEndereco = new JTextField();
		txtEndereco.setBounds(83, 94, 242, 20);
		contentPane.add(txtEndereco);
		txtEndereco.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Bairro");
		lblNewLabel_2.setFont(new Font("SansSerif", Font.BOLD, 12));
		lblNewLabel_2.setBounds(27, 128, 46, 14);
		contentPane.add(lblNewLabel_2);
		
		txtBairro = new JTextField();
		txtBairro.setBounds(83, 125, 139, 20);
		contentPane.add(txtBairro);
		txtBairro.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Cidade");
		lblNewLabel_3.setFont(new Font("SansSerif", Font.BOLD, 12));
		lblNewLabel_3.setBounds(27, 155, 46, 14);
		contentPane.add(lblNewLabel_3);
		
		txtCidade = new JTextField();
		txtCidade.setBounds(83, 152, 139, 20);
		contentPane.add(txtCidade);
		txtCidade.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("UF");
		lblNewLabel_4.setBounds(239, 155, 24, 14);
		contentPane.add(lblNewLabel_4);
		
		JButton btnLimpar = new JButton("Limpar");
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				limpar();
			}
		});
		btnLimpar.setForeground(Color.RED);
		btnLimpar.setBackground(Color.WHITE);
		btnLimpar.setBounds(27, 204, 89, 23);
		contentPane.add(btnLimpar);
		
		JButton btnBuscar = new JButton("Buscar");
		
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(txtCep.getText().length() < 8) {
					JOptionPane.showMessageDialog(null, "Insira 8 números");
					txtCep.requestFocus();
				}else {
					
					//O METODO JÁ PREENCHE OS CAMPOS POR SI SÓ
					buscarCep();
				}
			}
		});
		
		btnBuscar.setBounds(286, 58, 117, 29);
		contentPane.add(btnBuscar);
		
		JButton btnSobre = new JButton("");
		btnSobre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
					Sobre sobre = new Sobre();
					sobre.setVisible(true);
				
			}
		});
		btnSobre.setToolTipText("Sobre");
		btnSobre.setIcon(new ImageIcon(Cep.class.getResource("/imgs/system_help_16955 (1).png")));
		btnSobre.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnSobre.setBorder(null);
		btnSobre.setBackground(SystemColor.control);
		btnSobre.setBounds(357, 190, 46, 48);
		contentPane.add(btnSobre);	
		
		lblStatus = new JLabel("");
		lblStatus.setBounds(229, 64, 20, 20);
		contentPane.add(lblStatus);
		
	}
	
	private void buscarCep() {

		String logradouro = "";
		String tipo_logradouro = "";
		String resultado = null;
		String cep = txtCep.getText();
		
		try {
			//CLASSE QUE LE CONTEUDO WEB
			URL url = new URL("http://cep.republicavirtual.com.br/web_cep.php?cep="+txtCep.getText()+"&formato=xml");
			
			SAXReader xml = new SAXReader();
			Document doc = xml.read(url);
			Element root = doc.getRootElement();
			
			for (Iterator<Element> it = root.elementIterator(); it.hasNext();) {
				//NAVEGA ATRAVEES DO DOCUMENTO XML
		        Element element = it.next();
		        
		        if(element.getQualifiedName().equals("resultado")) {
		        	resultado = element.getText();
		        	if(resultado.equals("1")) {	
		        		lblStatus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs/282474_ok_done_check_icon (2).png")));
		        		
		        	}else {
		        		JOptionPane.showMessageDialog(null,"CEP não encontrado. Tente novamente!");
		        	}
		        	
		        }
		        
		     // seta o icone de "OK" se encontrar o cep
        		
        			
        		if(element.getQualifiedName().equals("cidade")) {
		        	txtCidade.setText(element.getText());
		        }
		        
		        if(element.getQualifiedName().equals("bairro")) {
		        	txtBairro.setText(element.getText());
		        }
		        
		        if(element.getQualifiedName().equals("logradouro")) {
		        	logradouro = element.getText();
		        }
		        
		        if(element.getQualifiedName().equals("tipo_logradouro")) {
		        	tipo_logradouro = element.getText();
		        }
		        
		        if(element.getQualifiedName().equals("uf")) {
		        	cboUf.setSelectedItem(element.getText());
		        }
		        
		        txtEndereco.setText(tipo_logradouro +" "+ logradouro); 
		        
		        //BUSCA PELAs TAGs 
		        
		        
		        
		    }
			
			
			
		}catch(Exception e) {
			System.out.println(e);
		}
				
	}
	
	private void limpar() {
		
		txtCep.setText(null);
		System.out.println(txtCep.getText());
		txtEndereco.setText(null);
		txtBairro.setText(null);
		txtCidade.setText(null);
		cboUf.setSelectedItem(null);
		txtCep.requestFocus();
		lblStatus.setIcon(null);
	}
}
