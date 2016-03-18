package fr.treeptik.dao;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import fr.treeptik.dao.impl.ArticleJDBCImpl;
import fr.treeptik.dao.impl.ArticleXMLImpl;
import fr.treeptik.dao.impl.AuteurJDBCImpl;
import fr.treeptik.dao.impl.AuteurXMLImpl;
import fr.treeptik.dao.impl.CategorieJDBCImpl;
import fr.treeptik.dao.impl.CategorieXMLImpl;
import fr.treeptik.dao.impl.CommentaireJDBCImpl;
import fr.treeptik.dao.impl.CommentaireXMLImpl;

@Configuration
public class DAOFactory {

	private static String confDao;
	/**
	 * Permet de charger la propriété du choix de DAO
	 */
	public static String loadProperties() {
		Properties properties = new Properties();

		try {
			properties.load(new FileReader("classpath:application.properties"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return confDao = properties.getProperty("conf.dao");

	}
	
	
	@Bean(name = "articleDAO")
	public static ArticleDAO getArticleDAO() {
		loadProperties();
		if(confDao.equals("jdbc"))
		return new ArticleJDBCImpl();
		if(confDao.equals("xml"))
			return new ArticleXMLImpl();
		else
			return null;
	}
	
	@Bean(name = "auteurDAO")
	public static AuteurDAO getAuteurDAO() {
		loadProperties();
		if(confDao.equals("jdbc"))
		return new AuteurJDBCImpl();
		if(confDao.equals("xml"))
			return new AuteurXMLImpl();
		else
			return null;
	}

	@Bean(name = "categorieDAO")
	public static CategorieDAO getCategorieDAO() {
		loadProperties();
		if(confDao.equals("jdbc"))
		return new CategorieJDBCImpl();
		if(confDao.equals("xml"))
			return new CategorieXMLImpl();
		else
			return null;
	}
	
	@Bean(name = "commentaireDAO")
	public static CommentaireDAO getCommentaireDAO() {
		loadProperties();
		if(confDao.equals("jdbc"))
		return new CommentaireJDBCImpl();
		if(confDao.equals("xml"))
			return new CommentaireXMLImpl();
		else
			return null;
	}
	
}
