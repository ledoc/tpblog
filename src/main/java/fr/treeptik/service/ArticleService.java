package fr.treeptik.service;

import java.util.Date;
import java.util.List;

import fr.treeptik.exception.ServiceException;
import fr.treeptik.model.Article;

public interface ArticleService {
	Article save(Article article) throws ServiceException;

	void remove(Article article) throws ServiceException;
	
	Article findArticle(Integer id) throws ServiceException;

	List<Article> findAll() throws ServiceException;

	List<Article> findArticlesInTimePeriod(Date dateDebut, Date dateFin) throws ServiceException;

	Article findArticleByName(String articleName) throws ServiceException;

}
