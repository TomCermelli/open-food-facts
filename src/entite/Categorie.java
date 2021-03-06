package entite;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "")
public class Categorie {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private int id;

	@Column(name = "categorie", length = 255, unique = true)
	String categorie;

	@OneToMany(mappedBy = "categorie")
	private List<Produit> produits;

	public Categorie() {
		super();
	}

	public Categorie(String categorie) {
		super();
		this.categorie = categorie;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCategorie() {
		return categorie;
	}

	public void setCategorie(String categorie) {
		this.categorie = categorie;
	}

	public List<Produit> getProduit() {
		return produits;
	}

	public void setProduit(List<Produit> produit) {
		this.produits = produit;
	}

}
