package Apps;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

import dao.CategorieDao;
import dao.IngredientDao;
import dao.MarqueDao;
import dao.ProduitDao;


public class IntegrationOpenFoodFacts {

	public static void main(String[] args) throws IOException {
		
		
		// On lit le fichier csv d'open food facts
		Path pathOrigine = Paths.get("C:/ProjetJava/open-food-facts.csv");
		List<String> lines = Files.readAllLines(pathOrigine, StandardCharsets.UTF_8);
		// On enlève la premiere ligne car elle représente le nom des colonnes et non leurs valeurs
		lines.remove(0);

		// On parcourt la liste récupérer 
		for (int i = 0; i < lines.size(); i++) {
			
			// On split afin de séparer toute les colonnes 
			String[] infoProduit = lines.get(i).split("\\|", -1);
			String categorie = infoProduit[0];
			String marque = infoProduit[1];
			// Pas fait
			String nomProduit = infoProduit[2];
			char nutriGrade =  infoProduit[3].charAt(0);
			List<String> ingredients = Arrays.asList(infoProduit[4].replace("_", "").split("[,;-]", -1));
					for(String ingr : ingredients) {
						System.out.println(ingr);
					}
			/*double energie100g = Double.parseDouble(infoProduit[5]);
			double graisse100g = Double.parseDouble(infoProduit[6]);
			double sucre100g = Double.parseDouble(infoProduit[7]);
			double fibre100g = Double.parseDouble(infoProduit[8]);
			double proteine100g = Double.parseDouble(infoProduit[9]);
			double sel100g = Double.parseDouble(infoProduit[10]);*/
			
			CategorieDao categorieInsert = new CategorieDao();
			MarqueDao marqueInsert = new MarqueDao();
			ProduitDao produitInsert = new ProduitDao();
			IngredientDao ingredientInsert = new IngredientDao();
			
			//categorieInsert.insertCategorie(categorie);
			//marqueInsert.insertMarque(marque);
			
		}

	}

}
