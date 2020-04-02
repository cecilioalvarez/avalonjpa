package es.avalon.main;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.hibernate.Transaction;

import es.avalon.dominiojpa.Libro;

public class Principal4 {

	public static void main(String[] args) {
		EntityManagerFactory factoria = Persistence.createEntityManagerFactory("UnidadBiblioteca");
		EntityManager em = factoria.createEntityManager();
		EntityTransaction t = em.getTransaction();
		t.begin();
		
		Libro libro = em.find(Libro.class, "1");
		
		libro.setAutor("Juanito");
		em.merge(libro);
		t.commit();



	}

}
