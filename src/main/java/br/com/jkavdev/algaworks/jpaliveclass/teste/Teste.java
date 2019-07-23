package br.com.jkavdev.algaworks.jpaliveclass.teste;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Teste {
	
	public static void main(String[] args) {
		
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("live-class");
		EntityManager manager = factory.createEntityManager();
		
		System.out.println("Funcionou!");
		
		manager.close();
		factory.close();
		
	}

}
