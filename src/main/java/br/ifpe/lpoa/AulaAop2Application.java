package br.ifpe.lpoa;

import org.springframework.beans.BeansException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy
@ComponentScan
public class AulaAop2Application {

	public static void main(String[] args) {
		ApplicationContext applicationContext = SpringApplication.run(AulaAop2Application.class, args);

		Conta ctx = applicationContext.getBean(Conta.class);
		ContaCorrente cctx = applicationContext.getBean(ContaCorrente.class);
		Conta c = new Conta("123", 100.0);
		ContaCorrente cc= new ContaCorrente("123", 100.0);

		try {
			ctx.creditar(100);
			ctx.debitar(10);
			
			cctx.creditar(100);
			ctx.debitar(10);
			
			c.creditar(100);
			c.debitar(10);
			
			cc.creditar(100);
			cc.debitar(10);
			
			
		} catch (BeansException e) {
			e.printStackTrace();
		}
	}

}
