package br.ufpi.ia.camadas;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import br.ufpi.ia.funcaoativacao.FuncaoAtivacao;
import br.ufpi.ia.funcaoativacao.FuncaoSigmoid;
import br.ufpi.ia.neuronios.Neuronio;

public class Camada {
	private double [] saida;
	private double [] entrada;
	private double [] gradientes;
	private double gradienteCamada;
	private int quantidadeNeuronios;
	private List<Neuronio> neuronios;
	
	public double[] getSaida() {
		return saida;
	}
	
	public void setSaida(double[] saida) {
		this.saida = saida;
	}
	
	public double[] getEntrada() {
		return entrada;
	}
	
	public void setEntrada(double[] entrada) {
		this.entrada = entrada;
	}
	
	public double[] getGradientes() {
		return gradientes;
	}
	
	public void setGradientes(double[] gradientes) {
		this.gradientes = gradientes;
	}
	
	public double getGradienteCamada() {
		return gradienteCamada;
	}
	
	public void setGradienteCamada(double gradienteCamada) {
		this.gradienteCamada = gradienteCamada;
	}
	
	public int getQuantidadeNeuronios() {
		return quantidadeNeuronios;
	}

	public void setQuantidadeNeuronios(int quantidadeNeuronios) {
		this.quantidadeNeuronios = quantidadeNeuronios;
	}
	
	public List<Neuronio> getNeuronios() {
		return neuronios;
	}
	
	public void setNeuronios(List<Neuronio> neuronios) {
		this.neuronios = neuronios;
	}
	
	@Override
	public String toString() {
		return "Camada[\nEntrada = " + Arrays.toString(entrada) + "\nSaida = " + Arrays.toString(saida) + "\nQuantidade de Neuronios = " + quantidadeNeuronios + "\nGradientes(vetor) = " + Arrays.toString(gradientes) + "Gradiente da Camada = " + gradienteCamada +"\n]";
	}
	
	public void setFuncaoAtivacao(FuncaoAtivacao funcaoAtivacao) {
		for(Neuronio n: this.neuronios) {
			n.setFuncaoAtivacao(funcaoAtivacao);
		}
	}
	
	public double [][] getPesos() {
		double [][] matrizPesos = new double[this.quantidadeNeuronios][entrada.length];
		for(int i = 0; i < quantidadeNeuronios; i++) {
			matrizPesos[i] = this.neuronios.get(i).getPesos();
		}
		
		return matrizPesos;
	}
	
	public void adicionarNeuronios() {
		for(int i = 0; i < this.quantidadeNeuronios; i++) {
			Neuronio neuronio = new Neuronio();
			neuronio.setFuncaoAtivacao(new FuncaoSigmoid());
			
			this.neuronios.add(neuronio);
		}
	}
	
	public void combinarEntradas(double [] entradas) {
		for(int i = 0; i < this.quantidadeNeuronios; i++) {
			neuronios.get(i).somatorio(entradas);
			this.entrada[i] = neuronios.get(i).getPotencialAtivacao();
		}
	}
	
	public void gerarSaida() {
		for(int i = 0; i < this.neuronios.size(); i++) {
			this.neuronios.get(i).ativarNeuronio();
			this.saida[i] = this.neuronios.get(i).getSaida();
		}
	}
	
	public void somarGradiente() {
		this.gradienteCamada = 0;
		for(int i = 0; i < neuronios.size(); i++) {
			for(int j = 0; j < this.neuronios.get(i).getPesos().length; j++) {
				this.gradienteCamada += gradientes[i]*neuronios.get(i).getPesos()[j];
			}
		}
	}
	
	public void gerarPesos(int quantidadeEntradas) {
		for(int i = 0; i < this.quantidadeNeuronios; i++) {
			this.neuronios.get(i).gerarPesos(quantidadeEntradas);
		}
	}
	
	public void ajustarPesos(double taxaAprendizagem, double [] entrada) {
		for(int i = 0; i < this.quantidadeNeuronios; i++ ) {
			this.neuronios.get(i).ajustarPesos(taxaAprendizagem, entrada, this.gradientes[i]);
		}
	}
	
	public Camada(int quantidadeNeuronios) {
		this.quantidadeNeuronios = quantidadeNeuronios;
		this.neuronios = new ArrayList<Neuronio>();
		this.saida = new double[quantidadeNeuronios];
		this.entrada = new double[quantidadeNeuronios];
		this.gradientes = new double[quantidadeNeuronios];
		
		this.adicionarNeuronios();
		
	}
	
}
