package fr.treeptik.utils;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;

import fr.treeptik.exception.ServiceException;
import fr.treeptik.model.Article;
import fr.treeptik.model.Auteur;
import fr.treeptik.model.Categorie;
import fr.treeptik.service.AuteurService;
import fr.treeptik.service.CategorieService;

public class ArticleParameterizedRowMapper implements
		ParameterizedRowMapper<Article> {
	public Article mapRow(ResultSet rs, int rowNum) throws SQLException {
		@SuppressWarnings("resource")
		ApplicationContext context = new ClassPathXmlApplicationContext(
				"applicationContext.xml");
		AuteurService auteurService = context.getBean(AuteurService.class);
		CategorieService categorieService = context.getBean(CategorieService.class);
		Article article = new Article();
		article.setId(rs.getInt("id"));
		article.setTitre(rs.getString("titre"));
		article.setChapeau(rs.getString("chapeau"));
		article.setContenu(rs.getString("contenu"));
		article.setCreationDate(DateUtil.toUtilDate(rs.getDate("creationDate")));
		article.setEnLigne(rs.getBoolean("enLigne"));
		try {
			article.setCategorie((Categorie) categorieService.findCategorie(rs.getInt("categorie_id")));
			article.setAuteur((Auteur) auteurService.findAuteur(rs.getInt("auteur_id")));
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		return article;
	}
}
