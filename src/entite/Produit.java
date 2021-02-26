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
	
	@ManyToMany
	@JoinTable(name = "produit_ingredient",

			joinColumns = @JoinColumn(name = "id_produit", referencedColumnName = "id"), 
			inverseJoinColumns = @JoinColumn(name = "id_ingredient", referencedColumnName = "id"))
	
	private List<Ingredient> ingredients = new ArrayList<>();
	
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
	
	@ManyToMany
	@JoinTable(name = "produit_allergene",

			joinColumns = @JoinColumn(name = "id_produit", referencedColumnName = "id"), 
			inverseJoinColumns = @JoinColumn(name = "id_allergene", referencedColumnName = "id"))
	
	private List<Allergene> allergenes = new ArrayList<>();
	
	@ManyToMany
	@JoinTable(name = "produit_additif",

			joinColumns = @JoinColumn(name = "id_produit", referencedColumnName = "id"), 
			inverseJoinColumns = @JoinColumn(name = "id_additif", referencedColumnName = "id"))
	
	private List<Additif> additifs = new ArrayList<>();
	
	@ManyToOne
	@JoinColumn(name="id_marque")
	private Marque marque;
	
	@ManyToOne
	@JoinColumn(name="id_categorie")
	private Categorie categorie;

	
	
	public Produit() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getNutritionGradeFr() {
		return nutritionGradeFr;
	}

	public void setNutritionGradeFr(String nutritionGradeFr) {
		this.nutritionGradeFr = nutritionGradeFr;
	}

	public List<Ingredient> getIngredients() {
		return ingredients;
	}

	public void setIngredients(List<Ingredient> ingredients) {
		this.ingredients = ingredients;
	}

	public short getEnergie() {
		return energie;
	}

	public void setEnergie(short energie) {
		this.energie = energie;
	}

	public float getGraisse() {
		return graisse;
	}

	public void setGraisse(float graisse) {
		this.graisse = graisse;
	}

	public float getSucre() {
		return sucre;
	}

	public void setSucre(float sucre) {
		this.sucre = sucre;
	}

	public float getFibre() {
		return fibre;
	}

	public void setFibre(float fibre) {
		this.fibre = fibre;
	}

	public float getProteines() {
		return proteines;
	}

	public void setProteines(float proteines) {
		this.proteines = proteines;
	}

	public float getSel() {
		return sel;
	}

	public void setSel(float sel) {
		this.sel = sel;
	}

	public List<Allergene> getAllergenes() {
		return allergenes;
	}

	public void setAllergenes(List<Allergene> allergenes) {
		this.allergenes = allergenes;
	}

	public List<Additif> getAdditifs() {
		return additifs;
	}

	public void setAdditifs(List<Additif> additifs) {
		this.additifs = additifs;
	}

	public Marque getMarque() {
		return marque;
	}

	public void setMarque(Marque marque) {
		this.marque = marque;
	}

	public Categorie getCategorie() {
		return categorie;
	}

	public void setCategorie(Categorie categorie) {
		this.categorie = categorie;
	}
	
	
	

}
