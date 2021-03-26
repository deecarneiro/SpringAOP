package br.ifpe.lpoa;

import org.springframework.stereotype.Component;

@Component(value="ContaCorrente")
public class ContaCorrente extends Conta {

	public ContaCorrente() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ContaCorrente(String numero, double saldo) {
		super(numero, saldo);
		// TODO Auto-generated constructor stub
	}

	public void creditar(double valor) {
		this.saldo += valor;
		System.out.println("C - Saldo Conta Corrente =" + this.saldo);
	}

}
