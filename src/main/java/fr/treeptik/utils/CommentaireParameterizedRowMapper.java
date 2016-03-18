package fr.treeptik.utils;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;

import fr.treeptik.exception.ServiceException;
import fr.treeptik.model.Article;
import fr.treeptik.model.Commentaire;
import fr.treeptik.service.ArticleService;
import fr.treeptik.service.AuteurService;

public class CommentaireParameterizedRowMapper implements
		ParameterizedRowMapper<Commentaire> {
	public Commentaire mapRow(ResultSet rs, int rowNum) throws SQLException {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
				"applicationContext.xml");
		ArticleService articleService = context.getBean(ArticleService.class);
		Commentaire commentaire = new Commentaire();
		commentaire.setId(rs.getInt("id"));
		commentaire.setNom(rs.getString("nom"));
		commentaire.setEmail(rs.getString("email"));
		commentaire.setTexte(rs.getString("texte"));
		commentaire.setCreationDate(DateUtil.toUtilDate(rs.getDate("creationDate")));
		commentaire.setValidation(rs.getBoolean("validation"));
		try {
			commentaire.setArticle((Article) articleService.findArticle(rs.getInt("article_id")));
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		return commentaire;
	}
}
