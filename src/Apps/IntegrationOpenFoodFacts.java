package Apps;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import util.Alimentation;

public class IntegrationOpenFoodFacts {

	public static void main(String[] args) throws IOException {

		Path pathOrigine = Paths.get("C:/ProjetJava/open-food-facts.csv");
		List<String> lines = Files.readAllLines(pathOrigine, StandardCharsets.UTF_8);
		lines.remove(0);

		for (int i = 0; i < lines.size(); i++) {

			String[] infoProduit = lines.get(i).split("\\|", -1);
			String categorie = infoProduit[0];
			Alimentation alimentationBdd = new Alimentation();
			alimentationBdd.insertCategorie(categorie);
			
		}
		// TODO Auto-generated method stub
		
		// alimentationBdd.insertCategorie();

	}

}
