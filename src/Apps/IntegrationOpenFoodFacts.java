package Apps;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import dao.AdditifDao;
import dao.AllergeneDao;
import dao.CategorieDao;
import dao.IngredientDao;
import dao.MarqueDao;
import dao.ProduitDao;
import entite.Categorie;
import entite.Marque;
import entite.Produit;
import util.FloatUtils;

public class IntegrationOpenFoodFacts {

	public static void main(String[] args) throws IOException {
		/*
		 * EntityManagerFactory emf =
		 * Persistence.createEntityManagerFactory("open-food-facts"); EntityManager em =
		 * emf.createEntityManager(); EntityTransaction transaction =
		 * em.getTransaction();
		 */

		// On lit le fichier csv d'open food facts
		Path pathOrigine = Paths.get("C:/ProjetJava/open-food-facts.csv");
		List<String> lines = Files.readAllLines(pathOrigine, StandardCharsets.UTF_8);
		// On enlève la premiere ligne car elle représente le nom des colonnes et non
		// leurs valeurs
		lines.remove(0);

		CategorieDao categorieInsert = new CategorieDao();
		MarqueDao marqueInsert = new MarqueDao();
		ProduitDao produitInsert = new ProduitDao();
		IngredientDao ingredientInsert = new IngredientDao();
		AllergeneDao allergeneInsert = new AllergeneDao();
		AdditifDao additifInsert = new AdditifDao();
		FloatUtils floatParser = new FloatUtils();

		// On parcourt la liste récupérer
		for (int i = 0; i < lines.size(); i++) {

			// On split afin de séparer toute les colonnes
			String[] infoProduit = lines.get(i).split("\\|", -1);
			String categorie = infoProduit[0];
			String marque = infoProduit[1];
			String nomProduit = infoProduit[2];
			String nutriGrade = infoProduit[3];
			List<String> ingredientList = Arrays.asList(infoProduit[4].replace("_", "").split("[,;-]", -1));

//			float energie100g = 0;
//			float graisse100g = 0;
//			float sucre100g = 0;
//			float fibre100g = 0;
//			float proteine100g = 0;
//			float sel100g = 0;
			float energie100g = floatParser.FloatParse(infoProduit[5]);
			float graisse100g = floatParser.FloatParse(infoProduit[6]);
			float sucre100g = floatParser.FloatParse(infoProduit[7]);
			float fibre100g = floatParser.FloatParse(infoProduit[8]);
			float proteine100g = floatParser.FloatParse(infoProduit[9]);
			float sel100g = floatParser.FloatParse(infoProduit[10]);

			List<String> allergeneList = Arrays.asList(infoProduit[28].replace("_", "").split("[,;-]", -1));
			List<String> additifList = Arrays.asList(infoProduit[29].replace("_", "").split("[,;-]", -1));
//			if (!infoProduit[5].isEmpty()) {
//				energie100g = Float.parseFloat(infoProduit[5]);
//			}
//			System.out.println(energie100g);
//			if (!infoProduit[6].isEmpty()) {
//				graisse100g = Float.parseFloat(infoProduit[6]);
//				System.out.println(graisse100g);
//
//			}
//			if (!infoProduit[7].isEmpty()) {
//				sucre100g = Float.parseFloat(infoProduit[7]);
//			}
//			if (!infoProduit[8].isEmpty()) {
//				fibre100g = Float.parseFloat(infoProduit[8]);
//			}
//			if (!infoProduit[9].isEmpty()) {
//				proteine100g = Float.parseFloat(infoProduit[9]);
//			}
//			if (!infoProduit[10].isEmpty()) {
//				sel100g = Float.parseFloat(infoProduit[10]);
//			}

			Categorie categorieObj = categorieInsert.insertCategorie(categorie);
			Marque marqueObj = marqueInsert.insertMarque(marque);
			Produit produitObj = produitInsert.insertProduit(nomProduit, nutriGrade, energie100g, graisse100g,
					sucre100g, fibre100g, proteine100g, sel100g, marqueObj, categorieObj);
			for (String ingr : ingredientList) {

				ingredientInsert.insertIngredient(ingr, produitObj);
			}

			for (String all : allergeneList) {
				allergeneInsert.insertAllergene(all, produitObj);
			}

			for (String add : additifList) {
				additifInsert.insertAdditif(add, produitObj);
			}
		}
	}
}
