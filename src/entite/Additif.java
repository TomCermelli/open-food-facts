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
@Table(name="additif")
public class Additif {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id", nullable=false)
	private int id;
	
	@Column(name="additif")
	private String additif;
	
	@ManyToMany
	@JoinTable(name = "produit_additif",

			joinColumns = @JoinColumn(name = "id_additif", referencedColumnName = "id"), 
			inverseJoinColumns = @JoinColumn(name = "id_produit", referencedColumnName = "id"))
	private List<Produit> produits = new ArrayList<>();
	
	
	

	public Additif() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAdditif() {
		return additif;
	}

	public void setAdditif(String additif) {
		this.additif = additif;
	}
	
	

	
}
