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
		// On lit le fichier csv d'open food facts
		Path pathOrigine = Paths.get("C:/ProjetJava/open-food-facts.csv");
		List<String> lines = Files.readAllLines(pathOrigine, StandardCharsets.UTF_8);
		// On enlève la premiere ligne car elle représente le nom des colonnes et non
		// leurs valeurs
		lines.remove(0);

		// Instanciation de tout les DAO + d'une classe du package util
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

			// LES STRINGS
			// On récupère chaque valeur qui nous intérèsse directement dans la colonne
			// souhaité
			String categorie = infoProduit[0];
			String marque = infoProduit[1];
			String nomProduit = infoProduit[2];
			String nutriGrade = infoProduit[3];

			// LES LISTES
			// On split une nouvelle fois l'info pour rentrer en base chaque élement
			List<String> ingredientList = Arrays.asList(infoProduit[4].replace("_", "").split("[,;-]", -1));
			List<String> allergeneList = Arrays.asList(infoProduit[28].replace("_", "").split("[,;-]", -1));
			List<String> additifList = Arrays.asList(infoProduit[29].replace("_", "").split("[,;-]", -1));

			// LES FLOATS
			// FloatParse est une méthode dans le package util ou on retourne une valeur de
			// type float, si la valeur est null on y met la valeur 0
			float energie100g = floatParser.FloatParse(infoProduit[5]);
			float graisse100g = floatParser.FloatParse(infoProduit[6]);
			float sucre100g = floatParser.FloatParse(infoProduit[7]);
			float fibre100g = floatParser.FloatParse(infoProduit[8]);
			float proteine100g = floatParser.FloatParse(infoProduit[9]);
			float sel100g = floatParser.FloatParse(infoProduit[10]);

			// LES INSERTIONS
			// On insert les catégories, on utilise une variable de type Categorie pour la
			// mettre dans le constructeur de la méthode insertProduit
			Categorie categorieObj = categorieInsert.insertCategorie(categorie);
			// On insert les marques comme pour les catégories en utilisant une variable de
			// type Marque pour la mettre dans le constructeur de la méthode insertProduit
			Marque marqueObj = marqueInsert.insertMarque(marque);
			// On crée un objet produit pour pouvoir ensuite mettre des ingrédient avec le
			// produit courant
			Produit produitObj = produitInsert.insertProduit(nomProduit, nutriGrade, energie100g, graisse100g,
					sucre100g, fibre100g, proteine100g, sel100g, marqueObj, categorieObj);

			// l'insertion des ingrédient dans le produit courant
			for (String ingr : ingredientList) {

				ingredientInsert.insertIngredient(ingr, produitObj);
			}
			// l'insertion des allergene dans le produit courant
			for (String all : allergeneList) {
				allergeneInsert.insertAllergene(all, produitObj);
			}
			// l'insertion des additif dans le produit courant
			for (String add : additifList) {
				additifInsert.insertAdditif(add, produitObj);
			}
		}
	}
}
