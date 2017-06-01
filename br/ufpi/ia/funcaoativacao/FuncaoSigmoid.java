package br.ufpi.ia.funcaoativacao;

public class FuncaoSigmoid extends FuncaoAtivacao {

	@Override
	public double ativar(Double u) {
		double saida = 1/(1 + StrictMath.exp(-u));
		
		return saida;
	}

	@Override
	public double derivada(Double u) {
		double x = this.ativar(u);
		
		return x*(1 - x);
	}

}
