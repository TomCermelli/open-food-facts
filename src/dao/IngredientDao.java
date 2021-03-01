package dao;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import entite.Ingredient;
import entite.Produit;



public class IngredientDao extends AbstractDao {
	private EntityManager em = emf.createEntityManager();

	private EntityTransaction transaction = em.getTransaction();

	public Ingredient insertIngredient(String ingredient, Produit produit) throws IOException {
		Ingredient ingredientInsert = new Ingredient(null);

		TypedQuery<Ingredient> query = em.createQuery("SELECT i FROM Ingredient i WHERE i.ingredient = ?1", Ingredient.class);
		query.setParameter(1, ingredient);
		List<Ingredient> ingredientList = query.getResultList();

		if (ingredientList.isEmpty()) {
			transaction.begin();
			ingredientInsert = new Ingredient(ingredient);
			em.persist(ingredientInsert);
			transaction.commit();
		} else {
			ingredientInsert = ingredientList.get(0);
			System.err.println("Cette ingredient existe déja");
		}
		
		produit.getIngredients().add(ingredientInsert);
		return ingredientInsert;

	}
}
