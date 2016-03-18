package fr.treeptik.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

public class Article implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer id;
	private String titre;
	private String chapeau;
	private String contenu;
	private Date creationDate;
	private Boolean enLigne;
	private Categorie categorie;
	private Auteur auteur;

	public Article() {
	}

	public Article(Integer id, String titre, String chapeau, String contenu,
			Date date, Boolean enLigne, Categorie categorie, Auteur auteur) {
		super();
		this.id = id;
		this.titre = titre;
		this.chapeau = chapeau;
		this.contenu = contenu;
		this.creationDate = date;
		this.enLigne = enLigne;
		this.categorie = categorie;
		this.auteur = auteur;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Article other = (Article) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}

	public String getChapeau() {
		return chapeau;
	}

	public void setChapeau(String chapeau) {
		this.chapeau = chapeau;
	}

	public String getContenu() {
		return contenu;
	}

	public void setContenu(String contenu) {
		this.contenu = contenu;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date date) {
		this.creationDate = date;
	}

	public Boolean getEnLigne() {
		return enLigne;
	}

	public void setEnLigne(Boolean enLigne) {
		this.enLigne = enLigne;
	}

	public Categorie getCategorie() {
		return categorie;
	}

	public void setCategorie(Categorie categorie) {
		this.categorie = categorie;
	}

	public Auteur getAuteur() {
		return auteur;
	}

	public void setAuteur(Auteur auteur) {
		this.auteur = auteur;
	}

	@Override
	public String toString() {
		return "Article [id=" + id + ", titre=" + titre + ", chapeau="
				+ chapeau + ", contenu=" + contenu + ", creationDate="
				+ creationDate + ", enLigne=" + enLigne + ", categorie="
				+ categorie + ", auteur=" + auteur + "]";
	}

}
