package fr.treeptik.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.treeptik.dao.ArticleDAO;
import fr.treeptik.exception.DAOException;
import fr.treeptik.exception.ServiceException;
import fr.treeptik.model.Article;
import fr.treeptik.service.ArticleService;

@Service
public class ArticleServiceImpl implements ArticleService {
	
	@Autowired
	private ArticleDAO articleDAO;


	@Override
	public Article save(Article article) throws ServiceException {
		try {
			article = articleDAO.save(article);
		} catch (DAOException e) {
			throw new ServiceException(e.getMessage(), e.getCause());
		}
		return article;

	}

	@Override
	public void remove(Article article) throws ServiceException {
		try {
			articleDAO.remove(article);
		} catch (DAOException e) {
			throw new ServiceException(e.getMessage(), e.getCause());
		}

	}

	@Override
	public List<Article> findAll() throws ServiceException {
		List<Article> list = new ArrayList<>();
		try {
			list = articleDAO.findAll();
		} catch (DAOException e) {
			throw new ServiceException(e.getMessage(), e.getCause());
		}
		return list;
	}

	@Override
	public Article findArticle(Integer id) throws ServiceException {
		Article article = new Article();
		try {
			article = articleDAO.findArticle(id);
		} catch (DAOException e) {
			throw new ServiceException(e.getMessage(), e.getCause());
		}
		return article;
	}

	@Override
	public List<Article> findArticlesInTimePeriod(Date dateDebut, Date dateFin) throws ServiceException {
		List<Article> list = new ArrayList<>();
		try {
			list = articleDAO.findArticlesInTimePeriod(dateDebut, dateFin);
		} catch (DAOException e) {
			throw new ServiceException(e.getMessage(), e.getCause());
		}
			return list;
	}

	@Override
	public Article findArticleByName(String articleName) throws ServiceException {
		Article article = new Article();
		try {
			article = articleDAO.findArticleByName(articleName);
		} catch (DAOException e) {
			throw new ServiceException(e.getMessage(), e.getCause());
		}
		return article;
	}
		

}
