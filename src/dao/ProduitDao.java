package dao;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import entite.Categorie;
import entite.Ingredient;
import entite.Marque;
import entite.Produit;

public class ProduitDao extends AbstractDao {
	private EntityManager em = emf.createEntityManager();

	private EntityTransaction transaction = em.getTransaction();

	public Produit insertProduit(String nom, String nutritionGradeFr, float energie, float graisse, float sucre,
			float fibre, float proteines, float sel, Marque marque, Categorie categorie) throws IOException {

		Produit produitInsert = null;

		TypedQuery<Produit> query = em.createQuery("SELECT p FROM Produit p WHERE p.nom = ?1", Produit.class);
		query.setParameter(1, nom);
		List<Produit> produitList = query.getResultList();

		if (produitList.isEmpty()) {
			transaction.begin();
			produitInsert = new Produit(nom, nutritionGradeFr, energie, graisse, sucre, fibre, proteines, sel, marque,
					categorie);
			em.persist(produitInsert);
			transaction.commit();
		} else {
			produitInsert = produitList.get(0);
			System.err.println("Ce produit existe déja ");
		}

		return produitInsert;
	}

}
