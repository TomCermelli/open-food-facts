package entite;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name="ingredient")
public class Ingredient {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id", nullable=false)
	private int id;
	
	@Column(name="ingredient")
	private List<String> ingredient;
	
	@ManyToMany
	@JoinTable(name = "produit_ingredient",

			joinColumns = @JoinColumn(name = "id_ingredient", referencedColumnName = "id"), 
			inverseJoinColumns = @JoinColumn(name = "id_produit", referencedColumnName = "id"))
	
	private List<Produit> produits = new ArrayList<>();
	
	

	public Ingredient() {
		super();
	}
	
	
	public Ingredient(List<String> ingredient) {
		super();
		this.ingredient = ingredient;
	}



	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public List<String> getIngredient() {
		return ingredient;
	}

	public void setIngredient(List<String> ingredient) {
		this.ingredient = ingredient;
	}

	public List<Produit> getProduits() {
		return produits;
	}

	public void setProduits(List<Produit> produits) {
		this.produits = produits;
	}
	
	

}
