package util;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.PreparedStatement;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import dao.AbstractDao;
import entite.Categorie;

public class Alimentation extends AbstractDao {
	
	private EntityManager em = emf.createEntityManager();
	
	private EntityTransaction transaction = em.getTransaction();

	public void insertCategorie() throws IOException {
		
		Path pathOrigine = Paths.get("C:/ProjetJava/en.openfoodfacts.org.products.csv");
		List<String> lines = Files.readAllLines(pathOrigine, StandardCharsets.UTF_8);
		lines.remove(0);
		
		boolean test = false;
		
		transaction.begin();
		
		// Stockage de valeur dans recensement afin d'avoir toute les villes du csv
		for (int i = 0; i < lines.size(); i++) {
			String[] infoProduit = lines.get(i).split(";");
			String categorie = infoProduit[0];
			
			for (int i2 = 0; i2 < lines.size(); i2++) {
				if(lines.get(i).equalsIgnoreCase(categorie)) {
					test = false;
				}
			}
			
			if(test) {
				Categorie categories = new Categorie(categorie);
				em.persist(categories);
			}
		}
		
		transaction.commit();

	}
}
