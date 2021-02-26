package entite;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="produit")
public class Produit {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id", nullable=false)
	private int id;
	
	@Column(name="nom")
	private String nom;
	
	@Column(name="nutrition_grade_fr")
	private String nutritionGradeFr;
	
	private String ingredient;
	
	@Column(name="energie_100g")
	private short energie;
	
	@Column(name="graisse_100g")
	private float graisse;
	
	@Column(name="sucre_100g")
	private float sucre;
	
	@Column(name="fibre_100g")
	private float fibre;
	
	@Column(name="proteines_100g")
	private float proteines;
	
	@Column(name="sel_100g")
	private float sel;
	
	@ManyToOne
	@JoinColumn(name="id_marque")
	private Marque marque;
	
	@ManyToOne
	@JoinColumn(name="id_categorie")
	private Categorie categorie;
	

}
