package br.ufpi.ia.camadas;

public class CamadaIntermediaria extends Camada {

	public void calcularGradiente(Camada camada){
		double somaT;
		
		for(int i = 0; i < this.getNeuronios().size(); i++) {
			somaT = 0;
			
			for(int j = 0; j < camada.getNeuronios().size(); j++) {
				somaT += camada.getNeuronios().get(j).getPesos()[i]*camada.getGradientes()[j];
			}

			getGradientes()[i] = somaT*getNeuronios().get(i).derivada();
		}
	}
	
	public CamadaIntermediaria(int quantidadeNeuronios) {
		super(quantidadeNeuronios);
	}

}
