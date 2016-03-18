package fr.treeptik.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.treeptik.dao.CommentaireDAO;
import fr.treeptik.exception.DAOException;
import fr.treeptik.exception.ServiceException;
import fr.treeptik.model.Commentaire;
import fr.treeptik.service.CommentaireService;

@Service
public class CommentaireServiceImpl implements CommentaireService {
	
	@Autowired
	private CommentaireDAO commentaireDAO;


	@Override
	public Commentaire save(Commentaire commentaire) throws ServiceException {
		try {
			commentaire = commentaireDAO.save(commentaire);
		} catch (DAOException e) {
			throw new ServiceException(e.getMessage(), e.getCause());
		}
		return commentaire;
	}

	@Override
	public void remove(Commentaire commentaire) throws ServiceException {
		try {
			commentaireDAO.remove(commentaire);
		} catch (DAOException e) {
			throw new ServiceException(e.getMessage(), e.getCause());
		}
	}

	@Override
	public List<Commentaire> findAll() throws ServiceException {
		System.out.println(commentaireDAO.getClass());
		List<Commentaire> list = new ArrayList<>();
		try {
			list = commentaireDAO.findAll();
		} catch (DAOException e) {
			throw new ServiceException(e.getMessage(), e.getCause());
		}
		return list;
	}

	@Override
	public Commentaire findCommentaires(Integer id) throws ServiceException {
		Commentaire commentaire = new Commentaire();
		try {
			commentaire = commentaireDAO.findCommentaire(id);
		} catch (DAOException e) {
			throw new ServiceException(e.getMessage(), e.getCause());
		}
		return commentaire;
	}

}
