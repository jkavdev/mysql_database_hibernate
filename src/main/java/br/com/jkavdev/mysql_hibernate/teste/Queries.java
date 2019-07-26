package br.com.jkavdev.mysql_hibernate.teste;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Tuple;

import br.com.jkavdev.mysql_hibernate.modelos.Continent;
import br.com.jkavdev.mysql_hibernate.modelos.Country;
import br.com.jkavdev.mysql_hibernate.modelos.CountryLanguage;

public class Queries {
	
	public static void main(String[] args) {
		
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("mysql-world-pu");
		EntityManager manager = factory.createEntityManager();
		

		//listando os idiomas
//		List<CountryLanguage> languages = manager.createQuery("from CountryLanguage", CountryLanguage.class).getResultList();
		
		//listando a contam de cidades do 'USA'
		Long contagem = manager.createQuery("select count(c) from City c where c.country.code = :name", Long.class)
			.setParameter("name", "USA")
			.getSingleResult();
		System.out.println(contagem);
		
		//listando a populacao e expectativa de vida da argentina
		Object[] arrayObject = manager.createQuery("select c.population, c.lifeExpectancy from Country c where c.code = :name", Object[].class)
				.setParameter("name", "ARG")
				.getSingleResult();
		System.out.println(arrayObject[0] + " - " + arrayObject[1]);
		Tuple tuple = manager.createQuery("select c.population as population, c.lifeExpectancy as lifeExpectancy from Country c where c.code = :name", Tuple.class)
				.setParameter("name", "ARG")
				.getSingleResult();
		System.out.println(tuple.get("population") + " - " + tuple.get("lifeExpectancy"));
		System.out.println(tuple.get(0) + " - " + tuple.get(1));
		
		//listando o pais com a maior expectativa de vida
		Country highest = manager.createQuery("select c from Country c where c.lifeExpectancy is not null order by c.lifeExpectancy desc", Country.class)
				.setMaxResults(1)
				.getSingleResult();
		System.out.println(highest);
		Tuple hiTuple = manager.createQuery("select c.code as countryCode, c.lifeExpectancy as lifeExpectancy from Country c where lifeExpectancy is not null order by c.lifeExpectancy desc", Tuple.class)
				.setMaxResults(1)
				.getSingleResult();
		System.out.println(hiTuple.get("countryCode") + " - " + hiTuple.get("lifeExpectancy"));
		
		//listando a capital da 'Espanha'
		Country espanha = manager.createQuery("select c from Country c left join City ci on c = ci.country and ci.id = c.capital where c.code = :code", Country.class)
				.setParameter("code", "ESP")
				.getSingleResult();
		System.out.println(espanha);
		Tuple espTuple = manager.createQuery("select c.code as countryCode, ci.name as cityName from Country c left join City ci on c = ci.country and ci.id = c.capital where c.code = :code", Tuple.class)
				.setParameter("code", "ESP")
				.getSingleResult();
		System.out.println(espTuple.get("countryCode") + " - " + espTuple.get("cityName"));
		
		//listando os idiomas do continente 'Asia'
		List<CountryLanguage> idiomasAsiaticos = manager.createQuery("select lan from CountryLanguage lan where lan.country.continent = :continent", CountryLanguage.class)
				.setParameter("continent", Continent.ASIA)
				.getResultList();
		System.out.println(idiomasAsiaticos);
		
		List<Country> paisesF = manager.createQuery("from Country where name like :nome", Country.class)
			.setParameter("nome", "F%")
			.setMaxResults(25)
			.getResultList();
		paisesF.forEach(System.out::println);
		
		//listar todos os paises com suas cidades e idiomas
		 List<Country> paises = manager.createQuery("select c from Country c left join fetch c.languages order by c.name", Country.class)
		 	.getResultList();
		 Set<Country> paisesSet = new HashSet<>(paises);
		 paises = new ArrayList<>(paisesSet);
//		 paisesSet.forEach(c -> System.out.println(c.getName() + " - " + c.getLanguageNames()));
		 paises.sort(Comparator.comparing(Country::getName));
		 paises.forEach(c -> System.out.println(c.getName() + " - " + c.getLanguageNames()));
		
		
		manager.close();
		factory.close();
		
	}

}
