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
@Table(name = "allergene")
public class Allergene {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private int id;

	@Column(name = "allergene")
	private String allergene;

	@ManyToMany
	@JoinTable(name = "produit_allergene",

			joinColumns = @JoinColumn(name = "id_allergene", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "id_produit", referencedColumnName = "id"))

	private List<Produit> produits = new ArrayList<>();

	public Allergene() {
		super();
	}

	public Allergene(String allergene) {
		super();
		this.allergene = allergene;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAllergene() {
		return allergene;
	}

	public void setAllergene(String allergene) {
		this.allergene = allergene;
	}

	public List<Produit> getProduits() {
		return produits;
	}

	public void setProduits(List<Produit> produits) {
		this.produits = produits;
	}

}
