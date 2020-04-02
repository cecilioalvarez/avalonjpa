package es.avalon.main;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import es.avalon.dominiojpa.Libro;

public class Principal4Actualizar {

	public static void main(String[] args) {


		EntityManagerFactory factoria=Persistence.createEntityManagerFactory("UnidadBiblioteca");
		EntityManager em=factoria.createEntityManager();
		EntityTransaction t=em.getTransaction();
		t.begin();
		
		Libro libro=em.find(Libro.class,"120");
		libro.setAutor("juanito");
		em.merge(libro);
		t.commit();
	
	}

}
