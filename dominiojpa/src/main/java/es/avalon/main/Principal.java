package es.avalon.main;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.hibernate.Transaction;

import es.avalon.dominiojpa.Libro;

public class Principal {

	public static void main(String[] args) {
		EntityManagerFactory factoria = Persistence.createEntityManagerFactory("UnidadBiblioteca");
		EntityManager em = factoria.createEntityManager();
		Libro libro = new Libro("120", "java", "yo", 20, "programacion");
		EntityTransaction t = em.getTransaction();
		t.begin();
		em.persist(libro);
		t.commit();

	}

}
