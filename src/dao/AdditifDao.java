package dao;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import entite.Additif;
import entite.Produit;

public class AdditifDao extends AbstractDao {

	private EntityManager em = emf.createEntityManager();

	private EntityTransaction transaction = em.getTransaction();

	public Additif insertAdditif(String additif, Produit produit) throws IOException {
		Additif additifInsert = new Additif(null);

		TypedQuery<Additif> query = em.createQuery("SELECT a FROM Additif a WHERE a.additif = ?1", Additif.class);
		query.setParameter(1, additif);
		List<Additif> additifList = query.getResultList();

		if (additifList.isEmpty()) {
			transaction.begin();
			additifInsert = new Additif(additif);
			em.persist(additifInsert);
			transaction.commit();
		} else {
			additifInsert = additifList.get(0);
			System.err.println("Cette additif existe déja");
		}

		produit.getAdditifs().add(additifInsert);
		return additifInsert;

	}

}
