package br.ifpe.lpoa;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;


@Component(value = "Conta")
@Primary
public class Conta {

	private String numero;
	protected double saldo;

	public Conta() {

	}

	public Conta(String numero, double value) {
		this.numero = numero;
		this.saldo = value;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public double getSaldo() {
		return saldo;
	}

	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}

	public void creditar(double valor) {
		this.saldo = this.saldo + valor;
		System.out.println(this.saldo);
	}
	
	public void debitar(double valor) throws Exception {
		if ((this.saldo - valor) > 0.0) {
			this.saldo -= valor;
		} else {
			throw new Exception("Saldo Insuficiente!");
		}		
	}
	
	public void transferir(Conta c, double valor) throws Exception {
		this.debitar(valor);
		c.creditar(valor);
	}

	void testException() throws Exception {
		String test = null;
		test.toString();
	}

	public static void main(String[] args) {
		Conta c1 = new Conta("1234", 100.0);
		Conta c2 = new ContaCorrente("4321", 50.0);

		c1.creditar(10);
		c2.creditar(20);
		try {
			c1.transferir(c2, 20);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			c1.debitar(10);
		} catch (Exception e) {
			e.printStackTrace();
		}
//		
//		try {
//			c1.testException();
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}

	}

	@Override
	public String toString() {
		String tostr = "Numero = " + this.numero;
		tostr += "\nSaldo = " + this.saldo;
		return tostr;
	}

}
