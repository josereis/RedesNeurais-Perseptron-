package br.ufpi.ia.visao;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import br.ufpi.ia.rede.*;

public class TelaPrincipal {

	private JFrame frame;
	private MultiLayerPerceptron neuralNetwork;
	private Perceptron perceptron;
	private JTextField textField_1;
	private JTextField textField_2;
	private Double[] entradas;
	private TelaPesos telaPesos;
	private Double taxaDeAprendizado;
	private Integer questao;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaPrincipal window = new TelaPrincipal();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public TelaPrincipal() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		entradas = new Double[2];
		int [] camadas = {2,1};
		neuralNetwork = new MultiLayerPerceptron(camadas);
		perceptron = new Perceptron();
		
		frame = new JFrame();
		frame.setBounds(100, 100, 759, 435);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(21, 11, 473, 374);
		frame.getContentPane().add(scrollPane);
		
		final JTextArea textArea = new JTextArea();
		scrollPane.setViewportView(textArea);
		
		JButton btnPerceptron = new JButton("Treinar Quest\u00E3o 1");
		btnPerceptron.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				treinarPrimeiraQuestao();
				textArea.setText(perceptron.getStrResult().toString());
			}
		});
		btnPerceptron.setBounds(539, 81, 160, 23);
		frame.getContentPane().add(btnPerceptron);
		
		JButton btnSetPesos = new JButton("Configurar Rede");
		btnSetPesos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				telaPesos = new TelaPesos();
				telaPesos.frmConfigurarRede.setVisible(true);
				
				
				
				neuralNetwork.pesosSetados = true;
				perceptron.pesosSetados = true;
			}
		});
		btnSetPesos.setBounds(539, 23, 160, 23);
		frame.getContentPane().add(btnSetPesos);
		
		textField_1 = new JTextField();
		textField_1.setBounds(563, 299, 51, 20);
		frame.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(668, 299, 51, 20);
		frame.getContentPane().add(textField_2);
		
		JLabel lblX = new JLabel("X1");
		lblX.setHorizontalAlignment(SwingConstants.CENTER);
		lblX.setBounds(525, 302, 28, 14);
		frame.getContentPane().add(lblX);
		
		JLabel lblX_1 = new JLabel("X2");
		lblX_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblX_1.setBounds(630, 302, 28, 14);
		frame.getContentPane().add(lblX_1);
		
		JButton btnExecutar = new JButton("Executar");
		btnExecutar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				entradas[0] = Double.parseDouble(textField_1.getText());
				entradas[1] = Double.parseDouble(textField_2.getText());
				if(questao == 2){
					neuralNetwork.executar(entradas);
					textArea.setText(neuralNetwork.getStrResult().toString());
				}
				if(questao == 1){
					perceptron.executar(entradas);
					textArea.setText(perceptron.getStrResult().toString());
				}
				
			}
		});
		btnExecutar.setBounds(583, 341, 89, 23);
		frame.getContentPane().add(btnExecutar);
		
		JButton btnNewButton = new JButton("Treinar Quest\u00E3o 2");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				treinarSegundaQuestao();
				textArea.setText(neuralNetwork.getStrResult().toString());
			}
		});
		btnNewButton.setBounds(539, 115, 160, 23);
		frame.getContentPane().add(btnNewButton);
	}
	
	public void treinarPrimeiraQuestao(){
		questao = 1;
		
		Double[][] entradas = new Double[4][2];
		entradas[0][0] = 0.0; entradas[0][1] = 0.0;
		entradas[1][0] = 0.0; entradas[1][1] = 1.0;
		entradas[2][0] = 1.0; entradas[2][1] = 0.0;
		entradas[3][0] = 1.0; entradas[3][1] = 1.0;
		
		Double[] saidaDesejada = new Double[4];
		saidaDesejada[0] = 0.0;
		saidaDesejada[1] = 0.0;
		saidaDesejada[2] = 1.0;
		saidaDesejada[3] = 1.0;
		
		taxaDeAprendizado = 0.5;
		
		if(perceptron.pesosSetados){
			Double[] pesos1 = new Double[2];
			Double[] pesos2 = new Double[2];
			Double[] pesos3 = new Double[2];
			
			pesos1[0] = Double.parseDouble(telaPesos.strCampos.get(0));
			pesos1[1] = Double.parseDouble(telaPesos.strCampos.get(1));
			pesos2[0] = Double.parseDouble(telaPesos.strCampos.get(2));
			pesos2[1] = Double.parseDouble(telaPesos.strCampos.get(3));
			pesos3[0] = Double.parseDouble(telaPesos.strCampos.get(4));
			pesos3[1] = Double.parseDouble(telaPesos.strCampos.get(5));
			
//			neuralNetwork.setarPesos(pesos1, pesos2, pesos3);
			perceptron.setPesos(pesos1);
			taxaDeAprendizado = Double.parseDouble(telaPesos.strCampos.get(6));
		}
		
		
		perceptron.treinamento(entradas, saidaDesejada, taxaDeAprendizado, 100);
	}
	
	public void treinarSegundaQuestao(){
		questao = 2;
		Double[][] entradas = new Double[4][2];
		entradas[0][0] = 0.0; entradas[0][1] = 0.0;
		entradas[1][0] = 0.0; entradas[1][1] = 1.0;
		entradas[2][0] = 1.0; entradas[2][1] = 0.0;
		entradas[3][0] = 1.0; entradas[3][1] = 1.0;
		
		Double[][] saidaDesejada = new Double[4][1];
		saidaDesejada[0][0] = 0.0;
		saidaDesejada[1][0] = 1.0;
		saidaDesejada[2][0] = 1.0;
		saidaDesejada[3][0] = 0.0;
		
		taxaDeAprendizado = 0.5;
		
		if(neuralNetwork.pesosSetados){
			Double[] pesos1 = new Double[2];
			Double[] pesos2 = new Double[2];
			Double[] pesos3 = new Double[2];
			
			pesos1[0] = Double.parseDouble(telaPesos.strCampos.get(0));
			pesos1[1] = Double.parseDouble(telaPesos.strCampos.get(1));
			pesos2[0] = Double.parseDouble(telaPesos.strCampos.get(2));
			pesos2[1] = Double.parseDouble(telaPesos.strCampos.get(3));
			pesos3[0] = Double.parseDouble(telaPesos.strCampos.get(4));
			pesos3[1] = Double.parseDouble(telaPesos.strCampos.get(5));
			
			neuralNetwork.setarPesos(pesos1, pesos2, pesos3);
			taxaDeAprendizado = Double.parseDouble(telaPesos.strCampos.get(6));
		}
		
		neuralNetwork.treinamento(entradas, saidaDesejada, taxaDeAprendizado, 0.09);
	}
}
