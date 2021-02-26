package dao;

import javax.persistence.EntityManager;

import entite.Additif;

public class AdditifDao extends AbstractDao{

	private EntityManager em;

	public AdditifDao(EntityManager em) {
		this.em = em;
	}

	public Additif findById(int id) {
		return em.find(Additif.class, id);
	}

	public void insert(Additif additif) {
		em.persist(additif);
	}

	public void update(Additif additif) {
		Additif additifDB = findById(additif.getId());
		additifDB.setAdditif(additif.getAdditif());
	}

	public void delete(Additif additif) {
		Additif additifDB = findById(additif.getId());
		em.refresh(additifDB);
	}

}
