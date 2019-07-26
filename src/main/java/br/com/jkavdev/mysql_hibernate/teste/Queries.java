package br.com.jkavdev.mysql_hibernate.teste;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Tuple;

import br.com.jkavdev.mysql_hibernate.modelos.Continent;
import br.com.jkavdev.mysql_hibernate.modelos.Country;
import br.com.jkavdev.mysql_hibernate.modelos.CountryLanguage;
import br.com.jkavdev.mysql_hibernate.modelos.dtos.CountryInfo;

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
		 
		 List<CountryInfo> allCountries = manager.createQuery("select "
		 		+ "	new br.com.jkavdev.mysql_hibernate.modelos.dtos.CountryInfo(c.code, c.name, lan.id.language, ci.name) "
		 		+ " from Country c "
		 		+ "		left join c.languages lan "
		 		+ "		left join City ci on c = ci.country", CountryInfo.class)
		 	.getResultList();
		 allCountries.forEach(System.out::println);
		 allCountries = new ArrayList<>(new HashSet<>(allCountries));
		 allCountries.sort(Comparator.comparing(CountryInfo::getName));
		 allCountries.forEach(System.out::println);
		
		 //01:58:09,742 ERROR [org.hibernate.hql.internal.ast.ErrorTracker] -  could not resolve property: countryCode of: br.com.jkavdev.mysql_hibernate.modelos.City
//		 01:58:09,743 ERROR [org.hibernate.hql.internal.ast.ErrorTracker] -  could not resolve property: countryCode of: br.com.jkavdev.mysql_hibernate.modelos.City
//		 could not resolve property: countryCode of: br.com.jkavdev.mysql_hibernate.modelos.City
//			at org.hibernate.hql.internal.ast.HqlSqlWalker.handleWithFragment(HqlSqlWalker.java:521)
//			at org.hibernate.hql.internal.ast.HqlSqlWalker.createEntityJoin(HqlSqlWalker.java:487)
//			at org.hibernate.hql.internal.ast.HqlSqlWalker.createFromJoinElement(HqlSqlWalker.java:387)
//			at org.hibernate.hql.internal.antlr.HqlSqlBaseWalker.joinElement(HqlSqlBaseWalker.java:3920)
//			at org.hibernate.hql.internal.antlr.HqlSqlBaseWalker.fromElement(HqlSqlBaseWalker.java:3706)
//			at org.hibernate.hql.internal.antlr.HqlSqlBaseWalker.fromElementList(HqlSqlBaseWalker.java:3584)
//			at org.hibernate.hql.internal.antlr.HqlSqlBaseWalker.fromClause(HqlSqlBaseWalker.java:720)
//			at org.hibernate.hql.internal.antlr.HqlSqlBaseWalker.query(HqlSqlBaseWalker.java:576)
//			at org.hibernate.hql.internal.antlr.HqlSqlBaseWalker.selectStatement(HqlSqlBaseWalker.java:313)
//			at org.hibernate.hql.internal.antlr.HqlSqlBaseWalker.statement(HqlSqlBaseWalker.java:261)
//			at org.hibernate.hql.internal.ast.QueryTranslatorImpl.analyze(QueryTranslatorImpl.java:272)
//			at org.hibernate.hql.internal.ast.QueryTranslatorImpl.doCompile(QueryTranslatorImpl.java:192)
//			at org.hibernate.hql.internal.ast.QueryTranslatorImpl.compile(QueryTranslatorImpl.java:144)
//			at org.hibernate.engine.query.spi.HQLQueryPlan.<init>(HQLQueryPlan.java:113)
//			at org.hibernate.engine.query.spi.HQLQueryPlan.<init>(HQLQueryPlan.java:73)
//			at org.hibernate.engine.query.spi.QueryPlanCache.getHQLQueryPlan(QueryPlanCache.java:158)
//			at org.hibernate.internal.AbstractSharedSessionContract.getQueryPlan(AbstractSharedSessionContract.java:611)
//			at org.hibernate.internal.AbstractSharedSessionContract.createQuery(AbstractSharedSessionContract.java:720)
//			at org.hibernate.internal.AbstractSharedSessionContract.createQuery(AbstractSharedSessionContract.java:745)
//			at org.hibernate.internal.AbstractSessionImpl.createQuery(AbstractSessionImpl.java:23)
//			at br.com.jkavdev.mysql_hibernate.teste.Queries.main(Queries.java:88)
//		01:58:09,749 ERROR [org.hibernate.hql.internal.ast.ErrorTracker] -  Unable to locate appropriate constructor on class [br.com.jkavdev.mysql_hibernate.modelos.dtos.CountryInfo]. Expected arguments are: java.lang.String, java.lang.String, java.lang.String, java.lang.String
//		[cause=org.hibernate.PropertyNotFoundException: no appropriate constructor in class: br.com.jkavdev.mysql_hibernate.modelos.dtos.CountryInfo]
//		01:58:09,749 ERROR [org.hibernate.hql.internal.ast.ErrorTracker] -  Unable to locate appropriate constructor on class [br.com.jkavdev.mysql_hibernate.modelos.dtos.CountryInfo]. Expected arguments are: java.lang.String, java.lang.String, java.lang.String, java.lang.String
//		[cause=org.hibernate.PropertyNotFoundException: no appropriate constructor in class: br.com.jkavdev.mysql_hibernate.modelos.dtos.CountryInfo]
//		 Unable to locate appropriate constructor on class [br.com.jkavdev.mysql_hibernate.modelos.dtos.CountryInfo]. Expected arguments are: java.lang.String, java.lang.String, java.lang.String, java.lang.String
//		[cause=org.hibernate.PropertyNotFoundException: no appropriate constructor in class: br.com.jkavdev.mysql_hibernate.modelos.dtos.CountryInfo]
//			at org.hibernate.hql.internal.ast.tree.ConstructorNode.resolveConstructor(ConstructorNode.java:182)
//			at org.hibernate.hql.internal.ast.tree.ConstructorNode.prepare(ConstructorNode.java:144)
//			at org.hibernate.hql.internal.ast.HqlSqlWalker.processConstructor(HqlSqlWalker.java:1245)
//			at org.hibernate.hql.internal.antlr.HqlSqlBaseWalker.selectExpr(HqlSqlBaseWalker.java:2366)
//			at org.hibernate.hql.internal.antlr.HqlSqlBaseWalker.selectExprList(HqlSqlBaseWalker.java:2232)
//			at org.hibernate.hql.internal.antlr.HqlSqlBaseWalker.selectClause(HqlSqlBaseWalker.java:1503)
//			at org.hibernate.hql.internal.antlr.HqlSqlBaseWalker.query(HqlSqlBaseWalker.java:585)
//			at org.hibernate.hql.internal.antlr.HqlSqlBaseWalker.selectStatement(HqlSqlBaseWalker.java:313)
//			at org.hibernate.hql.internal.antlr.HqlSqlBaseWalker.statement(HqlSqlBaseWalker.java:261)
//			at org.hibernate.hql.internal.ast.QueryTranslatorImpl.analyze(QueryTranslatorImpl.java:272)
//			at org.hibernate.hql.internal.ast.QueryTranslatorImpl.doCompile(QueryTranslatorImpl.java:192)
//			at org.hibernate.hql.internal.ast.QueryTranslatorImpl.compile(QueryTranslatorImpl.java:144)
//			at org.hibernate.engine.query.spi.HQLQueryPlan.<init>(HQLQueryPlan.java:113)
//			at org.hibernate.engine.query.spi.HQLQueryPlan.<init>(HQLQueryPlan.java:73)
//			at org.hibernate.engine.query.spi.QueryPlanCache.getHQLQueryPlan(QueryPlanCache.java:158)
//			at org.hibernate.internal.AbstractSharedSessionContract.getQueryPlan(AbstractSharedSessionContract.java:611)
//			at org.hibernate.internal.AbstractSharedSessionContract.createQuery(AbstractSharedSessionContract.java:720)
//			at org.hibernate.internal.AbstractSharedSessionContract.createQuery(AbstractSharedSessionContract.java:745)
//			at org.hibernate.internal.AbstractSessionImpl.createQuery(AbstractSessionImpl.java:23)
//			at br.com.jkavdev.mysql_hibernate.teste.Queries.main(Queries.java:88)
//		Cause:
//		org.hibernate.PropertyNotFoundException: no appropriate constructor in class: br.com.jkavdev.mysql_hibernate.modelos.dtos.CountryInfo
//			at org.hibernate.internal.util.ReflectHelper.getConstructor(ReflectHelper.java:346)
//			at org.hibernate.hql.internal.ast.tree.ConstructorNode.resolveConstructor(ConstructorNode.java:174)
//			at org.hibernate.hql.internal.ast.tree.ConstructorNode.prepare(ConstructorNode.java:144)
//			at org.hibernate.hql.internal.ast.HqlSqlWalker.processConstructor(HqlSqlWalker.java:1245)
//			at org.hibernate.hql.internal.antlr.HqlSqlBaseWalker.selectExpr(HqlSqlBaseWalker.java:2366)
//			at org.hibernate.hql.internal.antlr.HqlSqlBaseWalker.selectExprList(HqlSqlBaseWalker.java:2232)
//			at org.hibernate.hql.internal.antlr.HqlSqlBaseWalker.selectClause(HqlSqlBaseWalker.java:1503)
//			at org.hibernate.hql.internal.antlr.HqlSqlBaseWalker.query(HqlSqlBaseWalker.java:585)
//			at org.hibernate.hql.internal.antlr.HqlSqlBaseWalker.selectStatement(HqlSqlBaseWalker.java:313)
//			at org.hibernate.hql.internal.antlr.HqlSqlBaseWalker.statement(HqlSqlBaseWalker.java:261)
//			at org.hibernate.hql.internal.ast.QueryTranslatorImpl.analyze(QueryTranslatorImpl.java:272)
//			at org.hibernate.hql.internal.ast.QueryTranslatorImpl.doCompile(QueryTranslatorImpl.java:192)
//			at org.hibernate.hql.internal.ast.QueryTranslatorImpl.compile(QueryTranslatorImpl.java:144)
//			at org.hibernate.engine.query.spi.HQLQueryPlan.<init>(HQLQueryPlan.java:113)
//			at org.hibernate.engine.query.spi.HQLQueryPlan.<init>(HQLQueryPlan.java:73)
//			at org.hibernate.engine.query.spi.QueryPlanCache.getHQLQueryPlan(QueryPlanCache.java:158)
//			at org.hibernate.internal.AbstractSharedSessionContract.getQueryPlan(AbstractSharedSessionContract.java:611)
//			at org.hibernate.internal.AbstractSharedSessionContract.createQuery(AbstractSharedSessionContract.java:720)
//			at org.hibernate.internal.AbstractSharedSessionContract.createQuery(AbstractSharedSessionContract.java:745)
//			at org.hibernate.internal.AbstractSessionImpl.createQuery(AbstractSessionImpl.java:23)
//			at br.com.jkavdev.mysql_hibernate.teste.Queries.main(Queries.java:88)

		
		manager.close();
		factory.close();
		
	}

}
