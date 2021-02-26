package util;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.PreparedStatement;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import dao.AbstractDao;
import entite.Categorie;
import entite.Marque;


public class Alimentation extends AbstractDao {
	
	private EntityManager em = emf.createEntityManager();
	
	private EntityTransaction transaction = em.getTransaction();

	public void insertCategorie(String categorie) throws IOException {
		
		transaction.begin();
		
		TypedQuery<Categorie> query = em.createQuery("SELECT c FROM Categorie c WHERE c.categorie = ?1", Categorie.class);
		query.setParameter(1, categorie);
		List<Categorie> categorieList = query.getResultList();
		
		if(categorieList.isEmpty()) {	
			Categorie categorieInsert = new Categorie(categorie);
			em.persist(categorieInsert);
			transaction.commit();
		}
		else {
			System.out.println("Il existe déja");
		}
		
		
	}
}
