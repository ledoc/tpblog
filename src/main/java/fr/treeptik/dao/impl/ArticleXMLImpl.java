package fr.treeptik.dao.impl;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

import fr.treeptik.dao.ArticleDAO;
import fr.treeptik.exception.DAOException;
import fr.treeptik.model.Article;

@Repository
public class ArticleXMLImpl implements ArticleDAO {

	@Override
	public Article save(Article article) throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void remove(Article article) throws DAOException {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Article> findAll() throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Article findArticle(Integer id) throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Article findArticleByName(String articleName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Article> findArticlesInTimePeriod(Date dateDebut, Date dateFin)
			throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}


}
