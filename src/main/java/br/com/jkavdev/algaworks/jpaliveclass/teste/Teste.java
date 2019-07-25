package br.com.jkavdev.algaworks.jpaliveclass.teste;

import java.util.HashSet;
import java.util.List;

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
		
		Country brazil = new Country("BRA", "Brazil", Continent.SOUTH_AMERICA);
		brazil.addOfficialLanguage("Portugues");
		brazil.addLanguage("Espanhol");
		
		manager.getTransaction().begin();
		
		manager.persist(newOne);
		manager.persist(secondOne);
		manager.persist(thirdOne);
		manager.persist(forthOne);
		manager.persist(FifthOne);
		
		manager.persist(brazil);
		
		manager.getTransaction().commit();
		
		manager.createQuery("from Country c", Country.class).getResultList()
			.forEach(System.out::println);
		
		System.out.println("\n\n");
		
		List<Country> paisesComIdiomas = manager.createQuery("select c from Country c inner join fetch c.languages", Country.class).getResultList();
		HashSet<Country> paisesComIdiomasSet = new HashSet<>(paisesComIdiomas);
		System.out.println(paisesComIdiomas.size());
		System.out.println(paisesComIdiomasSet.size());
		paisesComIdiomas.forEach(c -> System.out.println(c.getName() + " - " + c.getLanguages()));
		paisesComIdiomasSet.forEach(c -> System.out.println(c.getName() + " - " + c.getLanguages()));
		
		
		paisesComIdiomas = manager.createQuery("select distinct c from Country c inner join fetch c.languages", Country.class)
				.setHint("hibernate.query.passDistinctThrough", false)
				.getResultList();
		System.out.println(paisesComIdiomas.size());
		paisesComIdiomas.forEach(c -> System.out.println(c.getName() + " - " + c.getLanguages()));
		
		manager.close();
		factory.close();
		
	}

}
