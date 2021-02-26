package entite;

import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="marque")
public class Marque {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id", nullable=false)
	private int id;
	
	@Column(name="marque")
	private String marque;
	
	@OneToMany(mappedBy="marque")	
	private List<Produit> produits;
	

	public Marque() {
		super();
	}

	public Marque(String marque) {
		super();
		this.marque = marque;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getMarque() {
		return marque;
	}

	public void setMarque(String marque) {
		this.marque = marque;
	}

	public List<Produit> getProduit() {
		return produits;
	}

	public void setProduit(List<Produit> produit) {
		this.produits = produit;
	}
	
	

}
