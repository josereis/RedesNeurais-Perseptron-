package br.ufpi.ia.rede;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import br.ufpi.ia.camadas.Camada;
import br.ufpi.ia.camadas.CamadaIntermediaria;
import br.ufpi.ia.camadas.CamadaSaida;

public class MultiLayerPerceptron {
	private int epocas;
	private double erroMedio;
	private boolean statusPesos;
	private CamadaSaida camadaSaida;
	private List<CamadaIntermediaria> camadasIntermediarias;
	
	private StringBuffer strResult;

	public int getEpocas() {
		return epocas;
	}

	public void setEpocas(int epocas) {
		this.epocas = epocas;
	}

	public double getErroMedio() {
		return erroMedio;
	}

	public void setErroMedio(double erroMedio) {
		this.erroMedio = erroMedio;
	}

	public boolean getStatusPesos() {
		return statusPesos;
	}

	public void setStatusPesos(boolean statusPesos) {
		this.statusPesos = statusPesos;
	}

	public CamadaSaida getCamadaSaida() {
		return camadaSaida;
	}

	public void setCamadaSaida(CamadaSaida camadaSaida) {
		this.camadaSaida = camadaSaida;
	}

	public List<CamadaIntermediaria> getCamadasIntermediarias() {
		return camadasIntermediarias;
	}

	public void setCamadasIntermediarias(List<CamadaIntermediaria> camadasIntermediarias) {
		this.camadasIntermediarias = camadasIntermediarias;
	}

	public StringBuffer getStrResult() {
		return strResult;
	}

	public void setStrResult(StringBuffer strResult) {
		this.strResult = strResult;
	}
	
	public void setarPesos(double [] pesos1, double [] pesos2, double [] pesos3) {
		inicializarPesos(2);
		
		camadasIntermediarias.get(0).getNeuronios().get(0).setPesos(pesos1);
		camadasIntermediarias.get(0).getNeuronios().get(1).setPesos(pesos2);
		camadaSaida.getNeuronios().get(0).setPesos(pesos3);
	}
	
	private void ajustarCamadasIntermediarias(double [][] entradas, double taxaAprendizagem, int i) {
		camadasIntermediarias.get(camadasIntermediarias.size() - 1).calcularGradiente(camadaSaida);
		
		for(int j = camadasIntermediarias.size() - 1; j > 0; j--) {
			camadasIntermediarias.get(j).ajustarPesos(taxaAprendizagem, camadasIntermediarias.get(j - 1).getSaida());
			
			camadasIntermediarias.get(j - 1).calcularGradiente(camadasIntermediarias.get(j));
		}
		
		camadasIntermediarias.get(0).ajustarPesos(taxaAprendizagem, entradas[i]);
	}
	
	public double calcularErro(double [] esperado) {
		double soma = 0;
		
		for(int i = 0; i < camadaSaida.getSaida().length; i++) {
			soma += esperado[i] - camadaSaida.getSaida()[i];
		}
		
		return soma;
	}
	
	public void inicializarPesos(int tamanho) {
		if(camadasIntermediarias.size() > 0) {
			camadasIntermediarias.get(0).gerarPesos(tamanho);
			
			for(int i = 1; i < camadasIntermediarias.size(); i++) {
				camadasIntermediarias.get(i).gerarPesos(camadasIntermediarias.get(i - 1).getQuantidadeNeuronios());
			}
			camadaSaida.gerarPesos(camadasIntermediarias.get(camadasIntermediarias.size() - 1).getQuantidadeNeuronios());
		} else {
			camadaSaida.gerarPesos(tamanho);
		}
	}
	
	public void treinar(double [][] entradas, double [][] esperado, double taxaAprendizagem, double precisao) {
		@SuppressWarnings("unused")
		double erroAnterior, erroTemp;
		
		if(!statusPesos) {
			inicializarPesos(entradas[0].length);
		}
		
		this.epocas = 0;
		this.erroMedio= 0;
		
		strResult.append("##################### TREINAMENTO ####################\n");
		strResult.append("Pesos Iniciais:\n");
		for(int i = 0; i < camadasIntermediarias.size(); i++) {
			strResult.append("Camada " + i + "\n");
			pesosCamada(camadasIntermediarias.get(i));
		}
		strResult.append("Camada de Saida\n");
		pesosCamada(camadaSaida);
		
		do {
			erroTemp = 0;
			erroAnterior = this.erroMedio;
			
			// pecorre todas as entradas e calcula o erro medio
			for(int i = 0; i < entradas.length; i++) {
				camadasIntermediarias.get(0).combinarEntradas(entradas[i]);
				camadasIntermediarias.get(0).gerarSaida();
				
				for (int j = 1; j < camadasIntermediarias.size(); j++) {
					camadasIntermediarias.get(j).combinarEntradas(camadasIntermediarias.get(j - 1).getSaida());
					camadasIntermediarias.get(j).gerarSaida();
				}
				
				camadaSaida.combinarEntradas(camadasIntermediarias.get(camadasIntermediarias.size() - 1).getSaida()); 
				camadaSaida.gerarSaida();
				
				camadaSaida.calcularGradiente(esperado[i]);
				
				camadaSaida.ajustarPesos(taxaAprendizagem, camadasIntermediarias.get(camadasIntermediarias.size() - 1).getSaida());
				ajustarCamadasIntermediarias(entradas , taxaAprendizagem, i);
				erroTemp += Math.abs(calcularErro(esperado[i]));
			}
			erroMedio = erroTemp/entradas.length;
			this.epocas++;
		} while(this.erroMedio > precisao);
		
		strResult.append("\nTreinado por " + epocas + " epocas.\n");
		strResult.append("\nPesos Finais \n");
		for (int i = 0; i < camadasIntermediarias.size(); i++) {
			strResult.append("Camada " + i + "\n");
			pesosCamada(camadasIntermediarias.get(i));
		}
		
		strResult.append("Camada de Saida\n");
		pesosCamada(camadaSaida);
		
		imprimirCamadaDeSaida();
	}
	
	public void executar(double [] entrada) {
		if(camadasIntermediarias.size() > 0) {
			camadasIntermediarias.get(0).combinarEntradas(entrada);
			camadasIntermediarias.get(0).gerarSaida();
			
			for (int i = 1; i < camadasIntermediarias.size(); i++) {
				camadasIntermediarias.get(i).combinarEntradas(camadasIntermediarias.get(i - 1).getSaida());
				camadasIntermediarias.get(i).gerarSaida();
			}
			
			camadaSaida.combinarEntradas(camadasIntermediarias.get(camadasIntermediarias.size() - 1).getSaida());
			camadaSaida.gerarSaida();
			strResult.append("\nSaida "	+ Arrays.toString(camadaSaida.getSaida()));
		} else {
			camadaSaida.combinarEntradas(entrada);
			camadaSaida.gerarSaida();
		}
		imprimeVetor(camadaSaida.getSaida());
	}
	
	public void imprimeVetor(double[] vetor) {
		for (int i = 0; i < vetor.length; i++) {
//			System.out.println(vetor[i] + " ");
			System.out.println(Math.round(vetor[i]) + " ");
		}
	}
	
	private void imprimirCamadaDeSaida() {
		for (int i = 0; i < camadaSaida.getQuantidadeNeuronios(); i++) {
			for (int j = 0; j < camadaSaida.getNeuronios().get(i).getPesos().length; j++) {
				System.out
						.print(camadaSaida.getNeuronios().get(i).getPesos()[j]
								+ " ");
			}
			System.out.println("\n");
		}
		System.out.println("Treinado por " + epocas + " epocas");
	}
	
	public void pesosCamada(Camada camada) {
		for (int i = 0; i < camada.getQuantidadeNeuronios(); i++) {
			strResult.append("Peso Neuronio " + i + " " + Arrays.toString(camada.getNeuronios().get(i).getPesos()) + "\n");
		}
	}
	
	public MultiLayerPerceptron(List<CamadaIntermediaria> camadasIntermediarias, CamadaSaida camadaDeSaida) {
		this.camadaSaida = camadaDeSaida;
		this.camadasIntermediarias = camadasIntermediarias;
	}
	
	public MultiLayerPerceptron(int [] camadas) {
		this.camadasIntermediarias = new ArrayList<CamadaIntermediaria>();
		for(int i = 0; i < camadas.length - 1; i++) {
			CamadaIntermediaria camadaIntermediaria = new CamadaIntermediaria(camadas[i]);
			
			camadasIntermediarias.add(camadaIntermediaria);
		}
		
		camadaSaida = new CamadaSaida(camadas[camadas.length - 1]);
		
		strResult = new StringBuffer("");
		
		statusPesos = false;
	}
	
}
