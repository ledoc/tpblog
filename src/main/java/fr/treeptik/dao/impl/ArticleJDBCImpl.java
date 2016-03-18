package fr.treeptik.dao.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import fr.treeptik.dao.ArticleDAO;
import fr.treeptik.exception.DAOException;
import fr.treeptik.model.Article;
import fr.treeptik.utils.ArticleParameterizedRowMapper;
import fr.treeptik.utils.DateUtil;

@Repository
public class ArticleJDBCImpl extends JdbcDaoSupport implements ArticleDAO {

	@Autowired
	private DataSource dataSource;
	
	@PostConstruct
	private void init(){
		setDataSource(dataSource);
	}


	// 7 paramètres
	@Override
	public Article save(Article article) throws DAOException {
		
		Object[] param = new Object[] { article.getTitre(), article.getChapeau(),
				article.getContenu(), DateUtil.toSqlDate(article.getCreationDate()),
				article.getEnLigne(), article.getCategorie().getId(),
				article.getAuteur().getId() };
			
		String sql = "INSERT INTO Article (`titre`, `chapeau`, `contenu`, `creationDate`, `enLigne`, `categorie_id`, `auteur_id`) VALUES (?,?,?,?,?,?,?)";
		getJdbcTemplate().update(
				sql,
				param);

		return article;
	}

	@Override
	public void remove(Article article) throws DAOException {
		String sql = "DELETE FROM Article WHERE titre=?";
		getJdbcTemplate().update(sql, new Object[] { article.getTitre()});

	}

	@Override
	public List<Article> findAll() throws DAOException {
		String sql = "SELECT * FROM Article ";
		List<Article> list = getJdbcTemplate().query(sql,
				new ArticleParameterizedRowMapper());

		return list;
	}


	@Override
	public Article findArticleByName(String articleName) {
		String sql = "SELECT * FROM Article WHERE titre=?";
		List<Article> list = getJdbcTemplate().query(sql,
				new Object[] { articleName },
				new ArticleParameterizedRowMapper());
		Article article = list.get(0);
		
		

		return article;
	}




	@Override
	public Article findArticle(Integer id) throws DAOException {
		Article article = new Article();
//		try {
			String sql = "SELECT * FROM Article WHERE id=?";
			List<Article> list = getJdbcTemplate().query(sql,
					new Object[] { id }, new ArticleParameterizedRowMapper());
			 article = list.get(0);
			
			

		return article;
	}

	@Override
	public List<Article> findArticlesInTimePeriod(Date dateDebut, Date dateFin)
			throws DAOException {

		String sql = "SELECT * FROM Article WHERE creationDate BETWEEN ? AND ?";
		List<Article> list = getJdbcTemplate().query(sql,
				new Object[] { dateDebut, dateFin }, new ArticleParameterizedRowMapper());

		return list;
	}

}
