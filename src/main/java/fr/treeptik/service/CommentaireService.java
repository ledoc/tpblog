package fr.treeptik.service;

import java.util.List;

import fr.treeptik.exception.ServiceException;
import fr.treeptik.model.Commentaire;

public interface CommentaireService {
	Commentaire save(Commentaire commentaire) throws ServiceException;

	void remove(Commentaire commentaire) throws ServiceException;

	List<Commentaire> findAll() throws ServiceException;

	Commentaire findCommentaires(Integer id) throws ServiceException;

}
