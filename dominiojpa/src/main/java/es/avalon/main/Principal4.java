package es.avalon.main;



import javax.persistence.*;
import es.avalon.dominiojpa.*;
public class Principal4 {

	public static void main(String[] args) {
		
		EntityManagerFactory factoria= Persistence.createEntityManagerFactory("UnidadBiblioteca");
		EntityManager em= factoria.createEntityManager();
		EntityTransaction t= em.getTransaction();
		t.begin();
		Libro libro= em.find(Libro.class, "120");
	libro.setAutor("juanito");
		em.merge(libro);
		t.commit();
		
	
	
	}

}
