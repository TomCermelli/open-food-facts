package dao;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import entite.Categorie;

public class CategorieDao extends AbstractDao {

	private EntityManager em = emf.createEntityManager();

	private EntityTransaction transaction = em.getTransaction();

	public Categorie insertCategorie(String categorie) throws IOException {
		Categorie categorieInsert = new Categorie(null);

		TypedQuery<Categorie> query = em.createQuery("SELECT c FROM Categorie c WHERE c.categorie = ?1",
				Categorie.class);
		query.setParameter(1, categorie);
		List<Categorie> categorieList = query.getResultList();

		if (categorieList.isEmpty()) {
			transaction.begin();
			categorieInsert = new Categorie(categorie);
			em.persist(categorieInsert);
			transaction.commit();
		} else {
			categorieInsert = categorieList.get(0);
			System.err.println("Cette categorie existe déja");
		}

		return categorieInsert;
	}

}
