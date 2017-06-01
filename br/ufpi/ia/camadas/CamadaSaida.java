package br.ufpi.ia.camadas;

public class CamadaSaida extends Camada {

	public void calcularGradiente(double [] esperado) {
		for(int i = 0; i < getNeuronios().size(); i++) {
			this.getGradientes()[i] = (esperado[i] - getSaida()[i])*getNeuronios().get(i).derivada();
		}
		
		this.somarGradiente();
	}
	
	public CamadaSaida(int quantidadeNeuronios) {
		super(quantidadeNeuronios);
	}
	
}
