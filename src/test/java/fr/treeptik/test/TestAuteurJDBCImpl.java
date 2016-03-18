package fr.treeptik.test;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import fr.treeptik.dao.impl.AuteurJDBCImpl;
import fr.treeptik.exception.DAOException;
import fr.treeptik.model.Auteur;
import fr.treeptik.utils.JDBCUtil;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:test/applicationContextTest.xml" })
public class TestAuteurJDBCImpl {

	@Autowired
	private AuteurJDBCImpl auteurJDBCImpl;

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
			auteurJDBCImpl.save(null);
		} catch (DAOException e) {
			Assert.fail();
		}

	}

	@Test
	public void testSaveOK() {
		try {
			Auteur auteur = auteurJDBCImpl.findAuteur(1);
			Auteur auteursSave = auteurJDBCImpl.save(auteur);

			Assert.assertNotNull(auteursSave);
			Assert.assertNotNull(auteursSave.getId());

		} catch (DAOException e) {
			Assert.fail(e.getMessage());
		}
	}

	@Test
	public void testRemove() {
		try {
			Auteur auteur = auteurJDBCImpl.findAuteur(1);
			auteurJDBCImpl.remove(auteur);

		} catch (DAOException e) {
			Assert.assertTrue(e.getMessage().contains("a foreign key constraint fails"));
		}
	}

	@Test
	public void testFindAll() {
		try {
			List<Auteur> list = auteurJDBCImpl.findAll();
			Assert.assertNotNull(list);
			Assert.assertTrue(list.size() > 0);

		} catch (DAOException e) {
			Assert.fail(e.getMessage());
		}
	}

	@Test
	public void testFindAuteurs() {
		try {
			Auteur auteur = auteurJDBCImpl.findAuteur(1);
			Assert.assertNotNull(auteur);
			Assert.assertEquals(1l, auteur.getId().longValue());

		} catch (DAOException e) {
			Assert.fail(e.getMessage());
		}
	}

	@Test
	public void testFindNombreArticlesEnLigne() {
		try {
			Map<Auteur, Long> map = auteurJDBCImpl.findNombreArticlesEnLigne();
			Assert.assertNotNull(map);
			Set<Auteur> keySet = map.keySet();
			System.out.println("Le nombre d'articles en ligne par auteurs");
			for (Auteur auteur : keySet) {
				System.out.println("Id : " + auteur.getId());
				System.out.println("Nom : " + auteur.getNom());
				System.out.println("Prenom : " + auteur.getPrenom());
				System.out.println("URL : " + auteur.getUrl());
				System.out.println("email : " + auteur.getEmail());
				System.out.println("nombre d'article : " + map.get(auteur));
				System.out.println("");
			}

		} catch (DAOException e) {
			Assert.fail(e.getMessage());
		}

	}

//	@Test
//	public void testFindArticlesMaxCommente() {
//		try {
//			List<Auteur> list = auteurJDBCImpl.findArticlesMaxCommente();
//			Assert.assertNotNull(list);
//			Assert.assertTrue(list.size() > 0);
//			System.out.println("Les Auteurs dont les articles sont les plus commentés");
//			for (Auteur auteur : list) {
//				System.out.println("Id : " + auteur.getId());
//				System.out.println("Nom : " + auteur.getNom());
//				System.out.println("Prenom : " + auteur.getPrenom());
//				System.out.println("URL : " + auteur.getUrl());
//				System.out.println("email : " + auteur.getEmail());
//				System.out.println("");
//			}
//
//		} catch (DAOException e) {
//			Assert.fail(e.getMessage());
//		}
//	}

}
