# SpringAOP

## Description

Spring AOP project for AOP college classes. AOP(Aspect Oriented Programming) is a programming paradigm that tries to improve modularity by allowing cross-cutting issues to be separated. It accomplishes this by adding new functionality to existing code without changing the code itself.

## Dependencies

- Spring AOP
- AspectJ

## Frameworks

- Spring Framework

## Code Snippets

### 1. Advices

 It is a part of code used as 'advice' for an execution from another of code;

 - 1.1 Before: executed before the joinPoint
```java
//Executing this advice before executes Conta.creditar method with double argument, and getting valor value.
@Before(value = "execution(void Conta.creditar(double)) && this(Conta) && args(valor,..))")
public void before01(JoinPoint joinPoint, double valor) {
    logger.info("Creditaremos " + valor + " em conta");
}
```
- 1.2 After: executed after the joinPoint
```java
//Executing this advice after executes Conta.creditar method with double argument, and getting valor value and object.
@After(value = "execution(void Conta.creditar(double)) && this(Conta) && args(valor,..) && target(c))")
	public void point01(JoinPoint joinPoint,  double valor, Conta c) {
		logger.info("Creditamos " + valor + ". Seu saldo atualizado é " + c.saldo);
	}
```

- 1.3 Around:  when the joinPoint are executed
```java
//Executing this advice when executes Conta.creditar method with double argument, and getting valor value and object.
@Around(value = "execution(void Conta.creditar(double)) && this(Conta) && args(valor,..) && target(c))")
	public void around01(ProceedingJoinPoint joinPoint, double valor, Conta c) throws Throwable {
		logger.info("Estamos a creditar o valor de " + valor);
		joinPoint.proceed();
	}

```

- 1.3 Before:  when any exception triggered
```java
//Executing advice for any exception
@Before(value = "handler(* *(*))")
public void before09(JoinPoint joinPoint) {
    logger.info("Exception");
}
```


## Install, Build and Running

### 1. Requiriments
 
 - [Maven](https://maven.apache.org/install.html) 
 - [JDK](https://www.oracle.com/br/java/technologies/javase/jdk11-archive-downloads.html)

### 2. Building

2.1. To build the project run this command inside the project folder. This command will build the Java executable.

`mvn compile` 

Inside the project folder yet! To download and install all maven dependencies, run this command  bellow

`mvn install`

### 4. Running

To run after build, type this command in the terminal inside the project folder.

`java -jar AulaAOP-2-0.0.1-SNAPSHOT.jar`