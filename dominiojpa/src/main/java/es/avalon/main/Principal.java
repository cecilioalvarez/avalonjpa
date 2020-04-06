package es.avalon.main;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import es.avalon.dominiojpa.Libro;

public class Principal {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		EntityManagerFactory factoria=Persistence.createEntityManagerFactory("UnidadBiblioteca");
		EntityManager em=factoria.createEntityManager();
		Libro libro=new Libro("120","Java", "Yo", 20,"Programacion");
		EntityTransaction t=em.getTransaction();
		t.begin();
		em.persist(libro);
		t.commit();
	}

}
