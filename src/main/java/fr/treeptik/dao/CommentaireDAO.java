package fr.treeptik.dao;

import java.util.List;

import fr.treeptik.exception.DAOException;
import fr.treeptik.model.Commentaire;

public interface CommentaireDAO extends GenericDAO {
	Commentaire save(Commentaire commentaire) throws DAOException;

	void remove(Commentaire commentaire) throws DAOException;

	List<Commentaire> findAll() throws DAOException;

	Commentaire findCommentaire(Integer id) throws DAOException;
}
