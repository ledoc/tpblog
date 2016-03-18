package fr.treeptik.service;

import java.util.List;
import java.util.Map;

import fr.treeptik.exception.ServiceException;
import fr.treeptik.model.Categorie;

public interface CategorieService {
	Categorie save(Categorie categorie) throws ServiceException;

	void remove(Categorie categorie) throws ServiceException;

	List<Categorie> findAll() throws ServiceException;

	Categorie findCategorie(Integer id) throws ServiceException;
	
	Map<Categorie, Integer> findMaxArticles() throws ServiceException;

	Map<Categorie,Integer> findNombreArticles() throws ServiceException;

	Categorie findCategorieByName(String categorieName)  throws ServiceException;

	List<Categorie> findNotArticle()  throws ServiceException;

}
