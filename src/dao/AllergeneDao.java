package dao;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import entite.Allergene;
import entite.Produit;

public class AllergeneDao extends AbstractDao {

	private EntityManager em = emf.createEntityManager();

	private EntityTransaction transaction = em.getTransaction();

	public Allergene insertAllergene(String allergene, Produit produit) throws IOException {
		Allergene allergeneInsert = new Allergene(null);

		TypedQuery<Allergene> query = em.createQuery("SELECT a FROM Allergene a WHERE a.allergene = ?1",
				Allergene.class);
		query.setParameter(1, allergene);
		List<Allergene> allergeneList = query.getResultList();

		if (allergeneList.isEmpty()) {
			transaction.begin();
			allergeneInsert = new Allergene(allergene);
			em.persist(allergeneInsert);
			transaction.commit();
		} else {
			allergeneInsert = allergeneList.get(0);
			System.err.println("Cette allergene existe déja");
		}

		produit.getAllergenes().add(allergeneInsert);
		return allergeneInsert;

	}

}
