package fr.treeptik.test;

import java.sql.SQLException;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import fr.treeptik.dao.impl.CommentaireJDBCImpl;
import fr.treeptik.exception.DAOException;
import fr.treeptik.model.Commentaire;
import fr.treeptik.utils.JDBCUtil;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:test/applicationContextTest.xml" })
public class TestCommentaireJDBCImpl {
	@Autowired
	private CommentaireJDBCImpl commentaireJDBCImpl;

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
			commentaireJDBCImpl.save(null);
		} catch (DAOException e) {
			Assert.fail();
		}

	}

	@Test
	public void testSaveOK() {
		try {
			Commentaire commentaire = commentaireJDBCImpl.findCommentaire(1);
			Commentaire commentairesSave = commentaireJDBCImpl.save(commentaire);

			Assert.assertNotNull(commentairesSave);
			Assert.assertNotNull(commentairesSave.getId());

		} catch (DAOException e) {
			e.printStackTrace();
			Assert.fail(e.getMessage());
		}
	}

	@Test
	public void testRemove() {
		try {
			Commentaire commentaire = commentaireJDBCImpl.findCommentaire(3);
			commentaireJDBCImpl.remove(commentaire);

		} catch (DAOException e) {
			Assert.fail(e.getMessage());
		}
	}

	@Test
	public void testRemoveNotExist() {
		try {
			Commentaire commentaire = commentaireJDBCImpl.findCommentaire(2);
			commentaireJDBCImpl.remove(commentaire);
			// Pour tester la suppression d'un commentaire qui n'existe pas
			commentaireJDBCImpl.remove(commentaire);

		} catch (DAOException e) {
			System.out.println(e.getMessage());
			Assert.assertTrue(e.getMessage().contains("Commentaires inconnu id"));
		}
	}

	@Test
	public void testFindAll() {
		try {
			List<Commentaire> list = commentaireJDBCImpl.findAll();
			Assert.assertNotNull(list);
			Assert.assertTrue(list.size() > 0);

		} catch (DAOException e) {
			Assert.fail(e.getMessage());
		}
	}

	@Test
	public void testFindCommentaires() {
		try {
			Commentaire commentaire = commentaireJDBCImpl.findCommentaire(1);
			Assert.assertEquals(1l, commentaire.getId().longValue());

		} catch (DAOException e) {
			Assert.fail(e.getMessage());
		}
	}
}
