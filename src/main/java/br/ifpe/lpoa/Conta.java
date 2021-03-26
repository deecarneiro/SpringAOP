package br.ifpe.lpoa;

import org.springframework.stereotype.Component;

@Component
public class Conta {

	private String numero;
	protected double saldo;
	
	
	public Conta() {
		
	}

	public Conta(String numero, double saldo) {
		super();
		this.numero = numero;
		this.saldo = saldo;
	}
	
	
	
}
