package br.ufpi.ia.neuronios;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Random;

import br.ufpi.ia.funcaoativacao.FuncaoAtivacao;
import br.ufpi.ia.funcaoativacao.FuncaoDegrau;

public class Neuronio {
	private double saida;
	private double [] pesos;
	private double pesoBias;
	private double potencialAtivacao;
	private FuncaoAtivacao funcaoAtivacao;
	
	public double getSaida() {
		return saida;
	}
	
	public void setSaida(double saida) {
		this.saida = saida;
	}
	
	public double[] getPesos() {
		return pesos;
	}
	
	public void setPesos(double[] pesos) {
		this.pesos = pesos;
	}
	
	public double getPesoBias() {
		return pesoBias;
	}
	
	public void setPesoBias(double pesoBias) {
		this.pesoBias = pesoBias;
	}
	
	public double getPotencialAtivacao() {
		return potencialAtivacao;
	}
	
	public void setPotencialAtivacao(double potencialAtivacao) {
		this.potencialAtivacao = potencialAtivacao;
	}
	
	public FuncaoAtivacao getFuncaoAtivacao() {
		return funcaoAtivacao;
	}
	
	public void setFuncaoAtivacao(FuncaoAtivacao funcaoAtivacao) {
		this.funcaoAtivacao = funcaoAtivacao;
	}
	
	@Override
	public String toString() {
		return "Neuronio [pesos = " + Arrays.toString(pesos) + "; potencial de ativacao = " + potencialAtivacao + "; peso bias = " + pesoBias + "; saida = " + saida + "]";
	}
	
	public void gerarPesos(int tamanho) {
		this.pesos = new double[tamanho];
		
		for(int i = 0; i < tamanho; i++) {
			this.pesos[i] = new Random().nextDouble()*1;
		}
	}
	
	public void somatorio(double [] conexoes) {
		double soma = 0;
		
		soma += pesoBias*(-1);
		for(int i = 0; i < conexoes.length; i++) {
			soma += conexoes[i]*pesos[i];
		}
		
		this.potencialAtivacao = soma;
	}
	
	public void ajustarPesos(double taxaAprendizagem, double [] entrada, double gradiente) {
		for(int i = 0; i < this.pesos.length; i++) {
			this.pesos[i] += taxaAprendizagem*gradiente*entrada[i]*funcaoAtivacao.derivada(potencialAtivacao);
		}
		this.pesoBias += taxaAprendizagem*gradiente*(-1)*funcaoAtivacao.derivada(potencialAtivacao);
	}
	
	public void ativarNeuronio() {
		this.saida = funcaoAtivacao.ativar(potencialAtivacao);
	}
	
	public double derivada() {
		return this.funcaoAtivacao.derivada(potencialAtivacao);
	}
	
	public Neuronio() {
		this.potencialAtivacao = 0;
		this.funcaoAtivacao = new FuncaoDegrau(); // verificar se não é melhor substituir pela funcao Logistica ou a Signoda
		this.pesoBias = new Random().nextDouble()*1;
	}
	
	/**
	 * 
	 * @param funcaoAtivacao
	 */
	public Neuronio(FuncaoAtivacao funcaoAtivacao) {
		this.potencialAtivacao = 0;
		this.funcaoAtivacao = funcaoAtivacao;
		this.pesoBias = new Random().nextDouble()*1;
	}
	
	/**
	 * 
	 * @param pesos
	 * @param funcaoAtivacao
	 * @param pesoBias
	 */
	public Neuronio(double [] pesos, FuncaoAtivacao funcaoAtivacao, double pesoBias) {
		this.pesos = pesos;
		this.pesoBias = pesoBias;
		this.potencialAtivacao = 0;
		this.funcaoAtivacao = funcaoAtivacao;
	}
	
	/**
	 * 
	 * @param pesos
	 * @param funcaoAtivacao
	 * @param potencialAtivacao
	 * @param pesoBias
	 */
	public Neuronio(double [] pesos, FuncaoAtivacao funcaoAtivacao, double potencialAtivacao, double pesoBias) {
		this.pesos = pesos;
		this.pesoBias = pesoBias;
		this.funcaoAtivacao = funcaoAtivacao;
		this.potencialAtivacao = potencialAtivacao;
	}
	
}
