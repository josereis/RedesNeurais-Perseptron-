package br.ufpi.ia.rede;

import br.ufpi.ia.funcaoativacao.FuncaoDegrau;
import br.ufpi.ia.neuronios.Neuronio;

public class Perceptron extends Neuronio {
	private int epocas;
	private boolean statusPesos;
	private StringBuffer strResult;
	
	public int getEpocas() {
		return epocas;
	}

	public void setEpocas(int epocas) {
		this.epocas = epocas;
	}

	public boolean isStatusPesos() {
		return statusPesos;
	}

	public void setStatusPesos(boolean statusPesos) {
		this.statusPesos = statusPesos;
	}

	public StringBuffer getStrResult() {
		return strResult;
	}

	public void setStrResult(StringBuffer strResult) {
		this.strResult = strResult;
	}
	
	public void zerarPesos(int tamanho) {
		this.setPesos(new double[tamanho]);
		
		for(int i = 0; i < tamanho; i++) {
			this.getPesos()[i] = 0;
		}
		this.setPesoBias(0);
		this.statusPesos = true;
	}
	
	public void recalculaPeso(double taxaAprendizagem, double saidaDesejada, double [] entradas) {
		setPesoBias(getPesoBias() + taxaAprendizagem*(saidaDesejada - getSaida())*(-1));
		for(int i = 0; i < getPesos().length; i++) {
			this.getPesos()[i] += taxaAprendizagem*(saidaDesejada - getSaida())*entradas[i];
		}
	}
	
	public void treinar(double [][] entradas, double [] saida, double taxaAprendizagem, int epocas) {
		if(!statusPesos) {
			gerarPesos(entradas[0].length);
		}
		
		boolean erro;
		do {
			strResult.append("\nEpoca " + this.epocas + ":\nPesos = [");
			for(double peso: getPesos()) {
				strResult.append(" " + ((Double)peso).toString() + ";");
			}
			strResult.append("]\n");
			
			erro = false;
			for(int i = 0; i < saida.length; i++) {
				somatorio(entradas[i]);
				ativarNeuronio();
				
				if((int)this.getSaida() != (int)saida[i]) {
					recalculaPeso(taxaAprendizagem, saida[i], entradas[i]);
					
					erro = true;
				}
				strResult.append("Amostra = "+ (i+1)+ "; Erro = " + erro + "\n");
			}
			System.out.println(this.epocas);
			
			this.epocas++;
		} while(erro && this.epocas < epocas);
	}
	
	public void executar(double [] entradas) {
		somatorio(entradas);
		ativarNeuronio();
		
		System.out.println("Saida = " + this.getSaida());
	}

	public Perceptron() {
		super(new FuncaoDegrau());
		
		this.epocas = 0;
		this.statusPesos = false;
		this.strResult = new StringBuffer("");
	}
	
}
