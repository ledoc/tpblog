package fr.treeptik.dao;

import java.util.List;
import java.util.Map;

import fr.treeptik.exception.DAOException;
import fr.treeptik.model.Auteur;

public interface AuteurDAO  extends GenericDAO {
	Auteur save(Auteur auteur) throws DAOException;

	void remove(Auteur auteur) throws DAOException;

	List<Auteur> findAll() throws DAOException;

	Auteur findAuteur(Integer id) throws DAOException;

	Map<Auteur, Long> findNombreArticlesEnLigne() throws DAOException;

	Map<Auteur, Integer> findArticlesMaxCommente() throws DAOException;

	Auteur findAuteurByName(String auteurName) throws DAOException;

}
