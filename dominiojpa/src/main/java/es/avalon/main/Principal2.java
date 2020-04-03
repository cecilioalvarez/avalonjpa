package es.avalon.main;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import javax.persistence.Persistence;

import es.avalon.dominiojpa.Libro;

public class Principal2 {

	public static void main(String[] args) {
		EntityManagerFactory factoria=Persistence.createEntityManagerFactory("UnidadBiblioteca");
		EntityManager em=factoria.createEntityManager();
		Libro libro=em.find(Libro.class, "120");
	
		System.out.println(libro.getIsbn());
		System.out.println(libro.getAutor());
		System.out.println(libro.getTitulo());

	}

}
