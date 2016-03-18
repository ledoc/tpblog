package fr.treeptik.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.treeptik.dao.CategorieDAO;
import fr.treeptik.exception.DAOException;
import fr.treeptik.exception.ServiceException;
import fr.treeptik.model.Categorie;
import fr.treeptik.service.CategorieService;

@Service
public class CategorieServiceImpl implements CategorieService {
	
	@Autowired
	private CategorieDAO categorieDAO;


	@Override
	public Categorie save(Categorie categorie) throws ServiceException {
		try {
			categorie = categorieDAO.save(categorie);
		} catch (DAOException e) {
			throw new ServiceException(e.getMessage(), e.getCause());
		}
		return categorie;
	}

	@Override
	public void remove(Categorie categorie) throws ServiceException {
		try {
			categorieDAO.remove(categorie);
		} catch (DAOException e) {
			throw new ServiceException(e.getMessage(), e.getCause());
		}
	}

	@Override
	public List<Categorie> findAll() throws ServiceException {
		List<Categorie> list = new ArrayList<>();
		try {
			list = categorieDAO.findAll();
		} catch (DAOException e) {
			throw new ServiceException(e.getMessage(), e.getCause());
		}
		return list;
	}

	@Override
	public Categorie findCategorie(Integer id) throws ServiceException {
		Categorie categorie = new Categorie();
		try {
			categorie = categorieDAO.findCategorie(id);
		} catch (DAOException e) {
			throw new ServiceException(e.getMessage(), e.getCause());
		}
		return categorie;
	}

	@Override
	public Map<Categorie, Integer> findMaxArticles() throws ServiceException {
		Map<Categorie, Integer> map = new HashMap<>();
		try {
			map = categorieDAO.findMaxArticles();
	} catch (DAOException e) {
		throw new ServiceException(e.getMessage(), e.getCause());
	}
		return map;
	}

	@Override
	public Map<Categorie, Integer> findNombreArticles() throws ServiceException {
		Map<Categorie, Integer> map = new HashMap<>();
		try {
			map = categorieDAO.findNombreArticles();
	} catch (DAOException e) {
		throw new ServiceException(e.getMessage(), e.getCause());
	}
		return map;
	}

	@Override
	public Categorie findCategorieByName(String categorieName)
			throws ServiceException {
		Categorie categorie = new Categorie();
		try {
			categorie = categorieDAO.findCategorieByName(categorieName);
		} catch (DAOException e) {
			throw new ServiceException(e.getMessage(), e.getCause());
		}
		return categorie;
	}

	@Override
	public List<Categorie> findNotArticle() throws ServiceException {
		List<Categorie> list = new ArrayList<>();
		try {
			list = categorieDAO.findNotArticle();
		} catch (DAOException e) {
			throw new ServiceException(e.getMessage(), e.getCause());
		}
		return list;
	}

}
