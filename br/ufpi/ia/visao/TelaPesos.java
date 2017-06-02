package br.ufpi.ia.visao;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class TelaPesos {

	public JFrame frmConfigurarRede;
	public JTextField textw1;
	public JTextField textw2;
	public JTextField textw3;
	public JTextField textw4;
	public JTextField textw5;
	public JTextField textw6;
	public JTextField taxa;
	public JTextField limiar;
	public List<String> strCampos = new ArrayList<>(); 
	
	


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaPesos window = new TelaPesos();
					window.frmConfigurarRede.setVisible(false);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public TelaPesos() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmConfigurarRede = new JFrame();
		frmConfigurarRede.setTitle("Configurar Rede");
		frmConfigurarRede.setBounds(100, 100, 331, 455);
		frmConfigurarRede.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmConfigurarRede.getContentPane().setLayout(null);
		
		JLabel lblCamada = new JLabel("Camada 1");
		lblCamada.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblCamada.setBounds(98, 11, 75, 14);
		frmConfigurarRede.getContentPane().add(lblCamada);
		
		JLabel lblCamada_1 = new JLabel("Camada 2");
		lblCamada_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblCamada_1.setBounds(98, 169, 75, 14);
		frmConfigurarRede.getContentPane().add(lblCamada_1);
		
		JLabel lblNeuronio = new JLabel("Neuronio 1");
		lblNeuronio.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblNeuronio.setBounds(10, 38, 75, 14);
		frmConfigurarRede.getContentPane().add(lblNeuronio);
		
		JLabel lblNeuronio_1 = new JLabel("Neuronio 2");
		lblNeuronio_1.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblNeuronio_1.setBounds(10, 106, 75, 14);
		frmConfigurarRede.getContentPane().add(lblNeuronio_1);
		
		JLabel lblNeuronio_2 = new JLabel("Neuronio 3");
		lblNeuronio_2.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblNeuronio_2.setBounds(10, 193, 75, 14);
		frmConfigurarRede.getContentPane().add(lblNeuronio_2);
		
		JLabel lblNewLabel = new JLabel("W1");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblNewLabel.setBounds(20, 74, 46, 14);
		frmConfigurarRede.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("W2");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblNewLabel_1.setBounds(168, 74, 46, 14);
		frmConfigurarRede.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("W3");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblNewLabel_2.setBounds(20, 139, 46, 14);
		frmConfigurarRede.getContentPane().add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("W4");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblNewLabel_3.setBounds(168, 139, 46, 14);
		frmConfigurarRede.getContentPane().add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("W5");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblNewLabel_4.setBounds(19, 234, 46, 14);
		frmConfigurarRede.getContentPane().add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("W6");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblNewLabel_5.setBounds(170, 233, 46, 14);
		frmConfigurarRede.getContentPane().add(lblNewLabel_5);
		
		textw1 = new JTextField();
		textw1.setBounds(76, 70, 75, 20);
		frmConfigurarRede.getContentPane().add(textw1);
		textw1.setColumns(10);
		
		textw2 = new JTextField();
		textw2.setColumns(10);
		textw2.setBounds(230, 71, 75, 20);
		frmConfigurarRede.getContentPane().add(textw2);
		
		textw3 = new JTextField();
		textw3.setColumns(10);
		textw3.setBounds(76, 136, 75, 20);
		frmConfigurarRede.getContentPane().add(textw3);
		
		textw4 = new JTextField();
		textw4.setColumns(10);
		textw4.setBounds(230, 136, 75, 20);
		frmConfigurarRede.getContentPane().add(textw4);
		
		textw5 = new JTextField();
		textw5.setColumns(10);
		textw5.setBounds(75, 231, 75, 20);
		frmConfigurarRede.getContentPane().add(textw5);
		
		textw6 = new JTextField();
		textw6.setColumns(10);
		textw6.setBounds(229, 229, 75, 20);
		frmConfigurarRede.getContentPane().add(textw6);
		
		JLabel label = new JLabel("T.A");
		label.setFont(new Font("Tahoma", Font.BOLD, 12));
		label.setBounds(44, 293, 28, 14);
		frmConfigurarRede.getContentPane().add(label);
		
		taxa = new JTextField();
		taxa.setColumns(10);
		taxa.setBounds(76, 292, 75, 20);
		frmConfigurarRede.getContentPane().add(taxa);
		
		JLabel label_1 = new JLabel("Lim");
		label_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		label_1.setBounds(168, 295, 46, 14);
		frmConfigurarRede.getContentPane().add(label_1);
		
		limiar = new JTextField();
		limiar.setColumns(10);
		limiar.setBounds(200, 292, 75, 20);
		frmConfigurarRede.getContentPane().add(limiar);
		
		JButton btnOk = new JButton("OK");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pegarStrings();
				frmConfigurarRede.setVisible(false);
			}
		});
		btnOk.setBounds(125, 360, 89, 23);
		frmConfigurarRede.getContentPane().add(btnOk);
	}
	
	public void pegarStrings(){
		strCampos.add(textw1.getText());
		strCampos.add(textw2.getText());
		strCampos.add(textw3.getText());
		strCampos.add(textw4.getText());
		strCampos.add(textw5.getText());
		strCampos.add(textw6.getText());
		strCampos.add(taxa.getText());
		strCampos.add(limiar.getText());
		
	}
}
