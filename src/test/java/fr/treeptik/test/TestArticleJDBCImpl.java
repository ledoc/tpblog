package fr.treeptik.test;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import fr.treeptik.dao.impl.ArticleJDBCImpl;
import fr.treeptik.exception.DAOException;
import fr.treeptik.model.Article;
import fr.treeptik.utils.JDBCUtil;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:test/applicationContextTest.xml" })
public class TestArticleJDBCImpl {

	@Autowired
	private ArticleJDBCImpl articleJDBCImpl;

	@Autowired
	private JDBCUtil jdbcUtil;

	// Permet d'executer le code avant chaque test
	@Before
	public void init() throws SQLException {
		System.out.println("NEW TEST");
		// annule la transaction
		jdbcUtil.getConnection().rollback();
	}

	@Test(expected = NullPointerException.class)
	public void testSaveParamNull() {
		try {
			articleJDBCImpl.save(null);
		} catch (DAOException e) {
			Assert.fail();
		}

	}

	@Test
	public void testSaveOK() {
		try {
			Article article = articleJDBCImpl.findArticle(1);
			Article articlesSave = articleJDBCImpl.save(article);

			Assert.assertNotNull(articlesSave);
			Assert.assertNotNull(articlesSave.getId());

		} catch (DAOException e) {
			Assert.fail(e.getMessage());
		}
	}

	@Test
	public void testRemove() {
		try {
			Article article = articleJDBCImpl.findArticle(1);
			articleJDBCImpl.remove(article);

		} catch (DAOException e) {
			Assert.assertTrue(e.getMessage().contains("a foreign key constraint fails"));
		}
	}

	@Test
	public void testFindAll() {
		try {
			List<Article> list = articleJDBCImpl.findAll();
			Assert.assertNotNull(list);
			Assert.assertTrue(list.size() > 0);

		} catch (DAOException e) {
			Assert.fail(e.getMessage());
		}
	}

	@Test
	public void testFindArticles() {
		try {
			Article article = articleJDBCImpl.findArticle(1);
			Assert.assertNotNull(article);
			Assert.assertEquals(1l, article.getId().longValue());

		} catch (DAOException e) {
			Assert.fail(e.getMessage());
		}
	}

	@Test
	public void testFindArticlesNotExists() {
		try {
			articleJDBCImpl.findArticle(10000000);

		} catch (DAOException e) {
			Assert.assertTrue(e.getMessage().contains("Illegal operation on empty result set"));
		}
	}

	@Test(expected = NullPointerException.class)
	public void testFindArticlesNull() {
		try {
			articleJDBCImpl.findArticle(null);

		} catch (DAOException e) {
			Assert.fail(e.getMessage());
		}
	}

	@Test
	public void testFindArticlesEntreDate() {
		List<Article> list = new ArrayList<>();
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Date dateDebut;
		Date dateFin;
		try {
			dateDebut = dateFormat.parse("01/01/2013");
			dateFin = dateFormat.parse("22/07/2013");
			list = articleJDBCImpl.findArticlesInTimePeriod(dateDebut, dateFin);

			Assert.assertNotNull(list);
			Assert.assertTrue(list.size() > 0);
			System.out.println(list.size());
			for (Article article : list) {
				System.out.println("Id : " + article.getId());
				System.out.println("Chapeau : " + article.getChapeau());
				System.out.println("Contenu : " + article.getContenu());
				System.out.println("Titre : " + article.getTitre());
				System.out.println("En ligne : " + article.getEnLigne());
				System.out.println("En ligne : " + dateFormat.format(article.getCreationDate()));
				System.out.println("");
			}

		} catch (ParseException | DAOException e) {
			Assert.fail(e.getMessage());
		}

	}

}