package fr.treeptik.dao.impl;

import java.util.List;
import java.util.Map;

import fr.treeptik.dao.AuteurDAO;
import fr.treeptik.exception.DAOException;
import fr.treeptik.model.Auteur;

public class AuteurXMLImpl implements AuteurDAO {

	@Override
	public Auteur save(Auteur auteur) throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void remove(Auteur auteur) throws DAOException {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Auteur> findAll() throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<Auteur, Long> findNombreArticlesEnLigne() throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public Auteur findAuteur(Integer id) throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Auteur findAuteurByName(String auteurName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<Auteur, Integer> findArticlesMaxCommente() throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}

}
