package fr.treeptik.dao.impl;

import java.util.List;
import java.util.Map;

import fr.treeptik.dao.CategorieDAO;
import fr.treeptik.exception.DAOException;
import fr.treeptik.model.Categorie;

public class CategorieXMLImpl implements CategorieDAO {

	@Override
	public Categorie save(Categorie categorie) throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void remove(Categorie categorie) throws DAOException {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Categorie> findAll() throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public List<Categorie> findNotArticle() throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public Map<Categorie, Integer> findNombreArticles() throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Categorie findCategorie(Integer id) throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<Categorie, Integer> findMaxArticles() throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Categorie findCategorieByName(String categorieName) {
		// TODO Auto-generated method stub
		return null;
	}

}
