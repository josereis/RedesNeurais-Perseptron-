package br.ufpi.ia.funcaoativacao;

public class FuncaoDegrau extends FuncaoAtivacao {

	@Override
	public double ativar(Double u) {
		if(u >= 0.0) {
			return 1.0;
		} else {
			return 0.0;
		}
	}

	@Override
	public double derivada(Double u) {
		// TODO Auto-generated method stub
		return 0;
	}

}
