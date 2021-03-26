package br.ifpe.lpoa;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.aspectj.lang.JoinPoint;

@Aspect
@Component
public class ContaAspect {
	
	/*
	 * 1 - Criar um pointcut para interceptar a execução do método creditar de Conta
	 * 2 - Criar um pointcut para interceptar a execução do método creditar de Conta Corrente 
	 * 3 - Criar um pointcut para interceptar a chamada do método creditar de Conta e de Conta Corrente 
	 * 4 - Criar um pointcut para interceptar toda vez que o saldo for alterado 
	 * 5 - Criar um pointcut para interceptar toda vez que o saldo for alterado dentro de Conta 
	 * 6 - Criar um pointcut para interceptar toda vez que o saldo for alterado dentro de um objeto de Conta 
	 * 7 - Criar um pointcut para interceptar toda chamada após a execução do método transferir de Conta, dentro de Conta 
	 * 8 - Criar um pointcut para interceptar a chamada do  método creditar de Conta dentro do código do método transferir 
	 * 9 - Criar um pointcut para interceptar qualquer excpetion levantada dentro da classe Conta
	 * 10 - Criar um pointcut para interceptar toda vez que o saldo for acessado dentro do método creditar da Conta Corrente
	 * 
	 */
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Before(value ="execution(* debitar(..)) && this(Conta)")
	public void before(JoinPoint joinPoint) {
		logger.info("ACHOU!!");
	}

}
