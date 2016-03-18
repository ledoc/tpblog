package fr.treeptik.service;

import java.util.List;
import java.util.Map;

import fr.treeptik.exception.ServiceException;
import fr.treeptik.model.Auteur;

public interface AuteurService {
	Auteur save(Auteur auteur) throws ServiceException;

	void remove(Auteur auteur) throws ServiceException;

	List<Auteur> findAll() throws ServiceException;

	Auteur findAuteur(Integer id) throws ServiceException;

	Map<Auteur, Long> findNombreArticlesEnLigne() throws ServiceException;

	Map<Auteur, Integer> findArticlesMaxCommente() throws ServiceException;

	Auteur findAuteurByName(String auteurName) throws ServiceException;
}
