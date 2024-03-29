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

	public static void main(String[] args) throws Exception {
		ApplicationContext applicationContext = SpringApplication.run(AulaAop2Application.class, args);

		Conta ctx = applicationContext.getBean(Conta.class);
		Conta ctx2 = applicationContext.getBean(Conta.class);

		ContaCorrente cctx = applicationContext.getBean(ContaCorrente.class);
		ContaCorrente cctx2 = applicationContext.getBean(ContaCorrente.class);

		Conta c = new Conta("123", 100.0);
		ContaCorrente cc= new ContaCorrente("123", 100.0);
//		
//		for(String name : applicationContext.getBeanDefinitionNames()) {
//			System.out.println(name);
//		}
		
		

		try {
			ctx.creditar(100);
			ctx.debitar(10);
			ctx2.creditar(200);
			ctx2.transferir(ctx, 0);
			
			cctx.creditar(100);
			cctx.debitar(10);
			cctx2.creditar(200);
			cctx2.transferir(ctx, 0);
			
//			cctx.debitar(1000);
			
		} catch (BeansException e) {
			e.printStackTrace();
		}
	}

}
