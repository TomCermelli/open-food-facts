package dao;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class AbstractDao {
	
	public static EntityManagerFactory emf = Persistence.createEntityManagerFactory("open-food-facts");
	

}
