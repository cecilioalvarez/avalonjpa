package es.avalon.main;



import javax.persistence.*;
import es.avalon.dominiojpa.*;
public class Principal {

	public static void main(String[] args) {
		
		EntityManagerFactory factoria= Persistence.createEntityManagerFactory("UnidadBiblioteca");
		EntityManager em= factoria.createEntityManager();
		Libro libro= new Libro ("120", "java", "yo", 20, "programacion");
		EntityTransaction t= em.getTransaction();
		t.begin();
		em.persist(libro);
		t.commit();
		
	
	
	}

}
