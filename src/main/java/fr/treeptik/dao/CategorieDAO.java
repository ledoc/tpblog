package fr.treeptik.dao;

import java.util.List;
import java.util.Map;

import fr.treeptik.exception.DAOException;
import fr.treeptik.model.Categorie;

public interface CategorieDAO extends GenericDAO {
	Categorie save(Categorie categorie) throws DAOException;

	void remove(Categorie categorie) throws DAOException;

	List<Categorie> findAll() throws DAOException;

	Categorie findCategorie(Integer id) throws DAOException;

	List<Categorie> findNotArticle() throws DAOException;

	Map<Categorie, Integer> findMaxArticles() throws DAOException;

	Map<Categorie,Integer> findNombreArticles() throws DAOException;

	Categorie findCategorieByName(String categorieName) throws DAOException;



}
