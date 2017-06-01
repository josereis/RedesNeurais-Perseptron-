package br.ufpi.ia.testes;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import br.ufpi.ia.rede.MultiLayerPerceptron;

public class MultiLayerPerceptronTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void test() {
		int[] vet = {2,1};
		
		MultiLayerPerceptron mlt = new MultiLayerPerceptron(vet);
		

		double [][] saida = {{0}, {1}, {1}, {0}};
		double [][] entradas = {{0.0,0.0},{0.0,1.0},{1.0,0.0},{1.0,1.0}};
		
		mlt.treinar(entradas, saida, 0.3, 0.3);
		
		for(int i=0; i<4; i++){
			mlt.executar(entradas[i]);
		}
		System.out.println(mlt.getStrResult());
	}

}
