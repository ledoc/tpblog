package fr.treeptik.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.treeptik.dao.AuteurDAO;
import fr.treeptik.exception.DAOException;
import fr.treeptik.exception.ServiceException;
import fr.treeptik.model.Auteur;
import fr.treeptik.service.AuteurService;

@Service
public class AuteurServiceImpl implements AuteurService {
	@Autowired
	private AuteurDAO auteurDAO;


	@Override
	public Auteur save(Auteur auteur) throws ServiceException {
		try {
			auteur = auteurDAO.save(auteur);
		} catch (DAOException  e) {
			throw new ServiceException(e.getMessage(), e.getCause());
		}
		return auteur;
	}

	@Override
	public void remove(Auteur auteur) throws ServiceException {
		try {
			auteurDAO.remove(auteur);
		} catch (DAOException e) {
			throw new ServiceException(e.getMessage(), e.getCause());
		}

	}

	@Override
	public List<Auteur> findAll() throws ServiceException {
		List<Auteur> list = new ArrayList<>();
		try {
			list = auteurDAO.findAll();
		} catch (DAOException e) {
			throw new ServiceException(e.getMessage(), e.getCause());
		}
		return list;
	}

	@Override
	public Auteur findAuteur(Integer id) throws ServiceException {
		Auteur auteur = new Auteur();
		try {
			auteur = auteurDAO.findAuteur(id);
		} catch (DAOException e) {
			throw new ServiceException(e.getMessage(), e.getCause());
		}
		return auteur;
	}

	@Override
	public Map<Auteur, Long> findNombreArticlesEnLigne()
			throws ServiceException {
		Map<Auteur, Long> map = new HashMap<>();
		try {
		map = auteurDAO.findNombreArticlesEnLigne();
		} catch (DAOException e) {
			throw new ServiceException(e.getMessage(), e.getCause());
		}
		return map;
	}

	@Override
	public Map<Auteur, Integer> findArticlesMaxCommente()
			throws ServiceException {
		Map<Auteur, Integer> map = new HashMap<>();
		try {		
			map = auteurDAO.findArticlesMaxCommente();
		} catch (DAOException e) {
			throw new ServiceException(e.getMessage(), e.getCause());
		}
			return map;
		}


	@Override
	public Auteur findAuteurByName(String auteurName) throws ServiceException {
		Auteur auteur = new Auteur();
		try {
			auteur = auteurDAO.findAuteurByName(auteurName);
		} catch (DAOException e) {
			throw new ServiceException(e.getMessage(), e.getCause());
		}	
			return auteur;
	}

}
