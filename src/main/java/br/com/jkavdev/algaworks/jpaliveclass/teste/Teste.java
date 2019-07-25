package br.com.jkavdev.algaworks.jpaliveclass.teste;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.com.jkavdev.algaworks.jpaliveclass.modelos.Continent;
import br.com.jkavdev.algaworks.jpaliveclass.modelos.Country;

public class Teste {
	
	public static void main(String[] args) {
		
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("live-class");
		EntityManager manager = factory.createEntityManager();
		
		System.out.println("Funcionou!");
		
		Country newOne = new Country("AJK", "Novo Pais", Continent.AFRICA);
		Country secondOne = new Country("AJA", "Novo Pais", Continent.SOUTH_AMERICA);
		Country forthOne = new Country("MIN", "Novo Pais", Continent.SOUTH_AMERICA);
		Country FifthOne = new Country("MIM", "Novo Pais", Continent.SOUTH_AMERICA);
		Country thirdOne = new Country("KLS", "Novo Pais 1", Continent.NORTH_AMERICA);
		
		manager.getTransaction().begin();
		
		manager.persist(newOne);
		manager.persist(secondOne);
		manager.persist(thirdOne);
		manager.persist(forthOne);
		manager.persist(FifthOne);
		
		manager.getTransaction().commit();
		
		manager.createQuery("from Country c", Country.class).getResultList()
			.forEach(System.out::println);
		
		manager.close();
		factory.close();
		
	}

}
