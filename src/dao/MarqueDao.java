package dao;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import entite.Marque;

public class MarqueDao extends AbstractDao {

	private EntityManager em = emf.createEntityManager();

	private EntityTransaction transaction = em.getTransaction();

	public void insertMarque(String marque) throws IOException {

		TypedQuery<Marque> query = em.createQuery("SELECT m FROM Marque m WHERE m.marque = ?1", Marque.class);
		query.setParameter(1, marque);
		List<Marque> marqueList = query.getResultList();

		if (marqueList.isEmpty()) {
			transaction.begin();
			Marque marqueInsert = new Marque(marque);
			em.persist(marqueInsert);
			transaction.commit();
		} else {
			System.err.println("Cette marque existe déja");
		}

	}
}
