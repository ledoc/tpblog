package fr.treeptik.dao;

import java.util.Date;
import java.util.List;

import fr.treeptik.exception.DAOException;
import fr.treeptik.model.Article;
import fr.treeptik.model.Commentaire;

public interface ArticleDAO  extends GenericDAO {
	Article save(Article article) throws DAOException;

	void remove(Article article) throws DAOException;

	List<Article> findAll() throws DAOException;

	Article findArticle(Integer id) throws DAOException;

	Article findArticleByName(String articleName)  throws DAOException;

	List<Article> findArticlesInTimePeriod(Date dateDebut, Date dateFin)
			throws DAOException;


}
