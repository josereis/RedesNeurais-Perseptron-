package br.ufpi.ia.testes;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import br.ufpi.ia.rede.Perceptron;

public class PerceptronTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void test() {
		Perceptron p = new Perceptron();
		
		double saida[] = {0, 0, 1, 1};
		double entradas[][] = {{0.0,0.0},{0.0,1.0},{1.0,0.0},{1.0,1.0}};
		
		p.treinar(entradas, saida, 0.1, 100);
		
		for(int i=0; i<4; i++){
			p.executar(entradas[i]);
		}
		
		System.out.println("\n\nStrResult:\n" + p.getStrResult());
	}

}
