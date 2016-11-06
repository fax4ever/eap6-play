package it.redhat.demo.service;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import it.redhat.demo.model.Person;

@Stateless
public class PersonService {
	
	@Inject
	private EntityManager em;
	
	public Person create(Person person) {
		
		em.persist(person);
		return person;
		
	}
	
	public Person update(Person person) {
		
		em.merge(person);
		return person;
		
	}
	
	public void deleteByFiscalCode(String fiscalCode) {
		
		Person person = findByFiscalCode(fiscalCode);
		if (person != null) {
			em.remove(person);
		}
		
	}
	
	public Person findByFiscalCode(String fiscalCode) {
		
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Person> criteria = builder.createQuery(Person.class);
		Root<Person> root = criteria.from(Person.class);
		TypedQuery<Person> query = em.createQuery(criteria
			.select(root)
			.where(builder.equal(root.<String> get("fiscalCode"), fiscalCode))
		);
		
		try {
			return query.getSingleResult();
		} catch (NoResultException ex) {
			return null;
		}
		
	}
	

}
