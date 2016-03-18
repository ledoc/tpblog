package fr.treeptik.test;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import fr.treeptik.dao.impl.ArticleJDBCImpl;
import fr.treeptik.dao.impl.CommentaireXMLImpl;
import fr.treeptik.exception.DAOException;
import fr.treeptik.model.Commentaire;
import fr.treeptik.utils.JDBCUtil;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:test/applicationContextTest.xml" })
public class TestCommentaireXMLImpl {

	@Autowired
	private CommentaireXMLImpl commentaireXMLImpl;
	@Autowired
	private ArticleJDBCImpl articleJDBCImpl;

	@Autowired
	private JDBCUtil jdbcUtil;

	// Permet d'executer le code avant chaque test
	@Before
	public void init() throws SQLException {
		System.out.println("NEW TEST");
		jdbcUtil.getConnection().rollback();
	}

	@Test
	public void testSaveOK() {
		try {
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

			Commentaire commentaire = new Commentaire(6, articleJDBCImpl.findArticle(2), "terre",
					"terre@terre.tr", "Ajout commentaire", dateFormat.parse("25/12/2012"), true);
			Commentaire commentairesSave = commentaireXMLImpl.save(commentaire);

			Assert.assertNotNull(commentairesSave);
			Assert.assertNotNull(commentairesSave.getId());

		} catch (DAOException | ParseException e) {
			e.printStackTrace();
			Assert.fail(e.getMessage());
		}
	}

	@Test
	public void testRemove() {
		try {
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

			Commentaire commentaire = new Commentaire(6, articleJDBCImpl.findArticle(2), "terre",
					"terre@terre.tr", "Ajout commentaire", dateFormat.parse("25/12/2012"), true);
			commentaireXMLImpl.save(commentaire);

			commentaireXMLImpl.remove(commentaire);

		} catch (DAOException | ParseException e) {
			e.printStackTrace();
			Assert.fail(e.getMessage());
		}
	}

	@Test
	public void testFindAll() {

		try {
			List<Commentaire> list = commentaireXMLImpl.findAll();
			Assert.assertNotNull(list);
			Assert.assertTrue(list.size() > 0);

			System.out.println("//////////////////");
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			System.out.println("NB Commentaires : " + list.size());
			for (Commentaire commentaire : list) {

				System.out.println("id : " + commentaire.getId());
				System.out.println("art_id : " + commentaire.getArticle().getId());
				System.out.println("Nom: " + commentaire.getNom());
				System.out.println("Email: " + commentaire.getEmail());
				System.out.println("Texte: " + commentaire.getTexte());
				System.out.println("date: " + dateFormat.format(commentaire.getCreationDate()));
				System.out.println("Validation: " + commentaire.getValidation());

				System.out.println("");
			}

		} catch (DAOException e) {
			Assert.fail(e.getMessage());
		}
	}

}
