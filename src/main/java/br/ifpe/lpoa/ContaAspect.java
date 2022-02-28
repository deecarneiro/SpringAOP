package br.ifpe.lpoa;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;

@Aspect
@Component
public class ContaAspect {
	
	/*
	 * 1 - Criar um pointcut para interceptar a execução do método creditar de Conta
	 * 2 - Criar um pointcut para interceptar a execução do método creditar de Conta Corrente 
	 * 3 - Criar um pointcut para interceptar a execução do método creditar de Conta e de Conta Corrente 
	 * 4 - Criar um pointcut para interceptar toda vez que o saldo for alterado ?? Set não é suportado por AOP Spring
	 * 5 - Criar um pointcut para interceptar toda vez que o saldo for alterado dentro de Conta ?? Set não é suportado por AOP Spring
	 * 6 - Criar um pointcut para interceptar toda vez que o saldo for alterado dentro de um objeto de Conta ?? Set não é suportado por AOP Spring
	 * 7 - Criar um pointcut para interceptar toda chamada após a execução do método transferir de Conta, dentro de Conta 
	 * 8 - Criar um pointcut para interceptar a chamada do  método creditar de Conta dentro do código do método transferir ?? Withincode não é suportado pelo AOP Spring 
	 * 9 - Criar um pointcut para interceptar qualquer excpetion levantada dentro da classe Conta ?? Handler não é suportado
	 * 10 - Criar um pointcut para interceptar toda vez que o saldo for acessado dentro do método creditar da Conta Corrente ?? Get não é suportado por AOP Spring
	 * 
	 */
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	// 01 Join Point
	@Before(value = "execution(void Conta.creditar(double)) && this(Conta) && args(valor,..))")
	public void before01(JoinPoint joinPoint, double valor) {
		logger.info("Creditaremos " + valor + " em conta");
	}

	@Around(value = "execution(void Conta.creditar(double)) && this(Conta) && args(valor,..) && target(c))")
	public void around01(ProceedingJoinPoint joinPoint, double valor, Conta c) throws Throwable {
		logger.info("Estamos a creditar o valor de " + valor);
		joinPoint.proceed();
	}

	@After(value = "execution(void Conta.creditar(double)) && this(Conta) && args(valor,..) && target(c))")
	public void point01(JoinPoint joinPoint,  double valor, Conta c) {
		logger.info("Creditamos " + valor + ". Seu saldo atualizado é " + c.saldo);
	}

	// Fim do 01 Join Point

	// 02 Join Point
	@Before(value = "execution(void ContaCorrente.creditar(double)) && within(ContaCorrente)  && args(valor,..))")
	public void before02(JoinPoint joinPoint, double valor) {
		logger.info("Creditaremos " + valor + " em conta corrente");
	}

	@Around(value = "execution(void ContaCorrente.creditar(double)) && within(ContaCorrente) && args(valor,..))")
	public void around02(ProceedingJoinPoint joinPoint, double valor) throws Throwable {
		logger.info("Estamos a creditar o valor de " + valor + " na sua conta corrente");
		joinPoint.proceed();
	}

	@After(value = "execution(void ContaCorrente.creditar(double)) && within(ContaCorrente) && args(valor,..) && target(c))")
	public void point02(JoinPoint joinPoint, double valor, ContaCorrente c) {
		logger.info("Creditamos " + valor + " na sua conta corrente. Seu saldo atualizado é " + c.saldo);
	}

	// Fim do 02 Join Point

	// 03 Join Point
	@Before(value = "execution(void Conta*.creditar(double)) && args(valor,..))")
	public void before03(JoinPoint joinPoint, double valor) {
		logger.info("Valor a ser creditado:" + valor);
	}

	@Around(value = "execution(void Conta*.creditar(double))")
	public void around03(ProceedingJoinPoint joinPoint) throws Throwable {
		logger.info("Processando credito...");
		joinPoint.proceed();
	}

	@After(value = "execution(void Conta*.creditar(double)) && target(c))")
	public void point03(JoinPoint joinPoint, Conta c) {
		logger.info("Valor do saldo atualizado:" + c.saldo);

	}

	// Fim do 03 Join Point

	// 04 Join Point
	//  Error creating bean with name 'aulaAop2Application': 
	//Pointcut expression 'set (* Conta.saldo)' contains unsupported pointcut primitive 'set'
	
	
//	@Before(value = "set (* Conta.saldo) && target(c))")
//	public void before04(JoinPoint joinPoint, Conta c) {
//		logger.info("***Seu saldo de " + c.saldo + " vai ser alterado***");
//	}

//	@Around(value = "set (* Conta.saldo) && target(c) && args(valor,..)")
//	public void around04(ProceedingJoinPoint joinPoint, Conta c, double valor) throws Throwable {
//		logger.info("Alterando seu saldo de:" + c.saldo + " para:" + valor);
//		joinPoint.proceed();
//	}

//	@After(value = "set (* Conta.saldo) && target(c))")
//	public void point04(JoinPoint joinPoint, Conta c) {
//		logger.info("O saldo alterado para:" + c.saldo);
//	}

	// Fim do 04 Join Point

	// 05 Join Point
	//  Error creating bean with name 'aulaAop2Application': 
	//Pointcut expression ' set (* Conta.saldo) && within(Conta)' contains unsupported pointcut primitive 'set'
//	@Before(value = " set (* Conta.saldo) && within(Conta) && target(c))")
//	public void before05(JoinPoint joinPoint) {
//		logger.info("O saldo vai alterado dentro de Conta");
//	}
//
//	@Around(value = " set (* Conta.saldo) && within(Conta) && target(c))")
//	public void around05(JoinPoint joinPoint) {
//		logger.info("Alterando saldo...");
//	}
//
//	@After(value = " set (* Conta.saldo) && within(Conta) && target(c))")
//	public void point05(JoinPoint joinPoint, Conta c) {
//		logger.info(O saldo foi alterado dentro de Conta"+c.getNumero());
//	}

	// Fim do 05 Join Point

	// 06 Join Point
	//Error Pointcut expression 'set(* Conta.saldo) && this(c)' contains unsupported pointcut primitive 'set'

//	@Before(value = "set(* Conta.saldo) && this(c)")
//	public void before06(JoinPoint joinPoint, Conta c) {
//		logger.info("ACHOU ANTES!!");
//	}
//
//	@Around(value = "set(* Conta.saldo) && this(c)")
//	public void around06(JoinPoint joinPoint, Conta c) {
//		logger.info("ACHOU AROUND!!");
//	}
//
//	@After(value = "set(* Conta.saldo) && this(c)")
//	public void point06(JoinPoint joinPoint, Conta c) {
//		logger.info("ACHOU AFTER!!");
//	}
//
	// Fim do 06 Join Point

	// 07 Join Point
	@Before(value = "execution(* Conta.transferir(Conta, double)) && within(Conta))")
	public void before07(JoinPoint joinPoint) {
		logger.info("Executaremos a transferencia");
	}

	@Around(value = "execution(* Conta.transferir(Conta, double)) && within(Conta) && target(c1) && args(c2, valor))")
	public void around07(ProceedingJoinPoint joinPoint, Conta c1, Conta c2, double valor ) throws Throwable {
		logger.info("Transferencia do valor "+valor+" interceptada de:"+c1.getNumero()+"para"+c2.getNumero());
		Object[] args = joinPoint.getArgs();
		joinPoint.proceed(args);
	}

	@After(value = "execution(* Conta.transferir(Conta, double)) && within(Conta) && args(c2, valor))")
	public void point07(JoinPoint joinPoint, Conta c2, double valor) {
		logger.info("Transferencia executada:"+valor);
	}
//
//	// Fim do 07 Join Point

	// 08 Join Point
	// withincode não é suportado
//	@Before(value = "execution(* Conta.creditar(..)) && withincode(* Conta.transferir(..))")
//	public void before08(JoinPoint joinPoint) {
//		logger.info("ACHOU ANTES!!");
//	}
//
//	@Around(value = "execution(* Conta.creditar(..)) && within(* Conta.transferir(..))")
//	public void around08(JoinPoint joinPoint) {
//		logger.info("ACHOU AROUND!!");
//	}
//
//	@After(value = "execution(* Conta.creditar(..)) && withincode(* Conta.transferir(..))")
//	public void point08(JoinPoint joinPoint) {
//		logger.info("ACHOU AFTER!!");
//	}

	// Fim do 08 Join Point
//
//	// 09 Join Point
//	@Before(value = "handler(* *(*))")
//	public void before09(JoinPoint joinPoint) {
//		logger.info("Exception");
//	}

//	// Fim do 09 Join Point
//	
	// 10 Join Point
//	@Before(value = "get(double Conta.saldo) && withincode(* ContaCorrente.creditar(..))")
//	public void before10(JoinPoint joinPoint) {
//		logger.info("ACHOU ANTES!!");
//	}
//
//	@Around(value = "get(double Conta.saldo) && withincode(* ContaCorrente.creditar(..))")
//	public void around10(JoinPoint joinPoint) {
//		logger.info("ACHOU AROUND!!");
//	}
//
//	@After(value = "get(double Conta.saldo) && withincode(* ContaCorrente.creditar(..))")
//	public void point10(JoinPoint joinPoint) {
//		logger.info("ACHOU AFTER!!");
//	}

	// Fim do 10 Join Point
	
}
