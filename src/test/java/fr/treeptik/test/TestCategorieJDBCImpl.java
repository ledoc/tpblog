package fr.treeptik.test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import fr.treeptik.dao.impl.CategorieJDBCImpl;
import fr.treeptik.exception.DAOException;
import fr.treeptik.model.Categorie;
import fr.treeptik.utils.JDBCUtil;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:test/applicationContextTest.xml" })
public class TestCategorieJDBCImpl {
	
	
	@Autowired
	private CategorieJDBCImpl categorieJDBCImpl;

	@Autowired
	private JDBCUtil jdbcUtil;

	// Permet d'executer le code avant chaque test
	@Before
	public void init() throws SQLException {
		System.out.println("NEW TEST");
		// annule la transaction
		jdbcUtil.getConnection().rollback();
	}

	// Execute un fois le code avant la class
	@BeforeClass
	public static void initClass() throws SQLException {
		System.out.println("START ALL TEST");
	}

	@Test(expected = NullPointerException.class)
	public void testSaveParamNull() {
		try {
			categorieJDBCImpl.save(null);
		} catch (DAOException e) {
			Assert.fail();
		}

	}

	@Test
	public void testSaveOK() {
		try {
			Categorie categorie = categorieJDBCImpl.findCategorie(1);
			Categorie categoriesSave = categorieJDBCImpl.save(categorie);

			Assert.assertNotNull(categoriesSave);
			Assert.assertNotNull(categoriesSave.getId());

		} catch (DAOException e) {
			Assert.fail(e.getMessage());
		}
	}

	@Test
	public void testRemove() {
		try {
			Categorie categorie = categorieJDBCImpl.findCategorie(1);
			categorieJDBCImpl.remove(categorie);

		} catch (DAOException e) {
			System.out.println(e.getMessage());
			Assert.assertTrue(e.getMessage().contains("a foreign key constraint fails"));
		}

	}

	@Test
	public void testFindAll() {
		try {
			List<Categorie> list = categorieJDBCImpl.findAll();
			Assert.assertNotNull(list);
			Assert.assertTrue(list.size() > 0);

		} catch (DAOException e) {
			Assert.fail(e.getMessage());
		}
	}

	@Test
	public void testFindCategories() {
		try {
			Categorie categorie = categorieJDBCImpl.findCategorie(1);
			Assert.assertEquals(1l, categorie.getId().longValue());

		} catch (DAOException e) {
			Assert.fail(e.getMessage());
		}
	}

	@Test
	public void testFindNotArticle() {
		try {
			List<Categorie> list = categorieJDBCImpl.findNotArticle();
			Assert.assertNotNull(list);
			Assert.assertTrue(list.size() > 0);

			for (Categorie categorie : list) {
				System.out.println("Id : " + categorie.getId());
				System.out.println("Name : " + categorie.getName());
				System.out.println("URL : " + categorie.getUrl());
			}

		} catch (DAOException e) {
			Assert.fail(e.getMessage());
		}
	}

//	@Test
//	public void testFindMaxArticles() {
//		try {
//			List<Categorie> list = new ArrayList<>();
//			list = categorieJDBCImpl.findMaxArticles();
//			Assert.assertNotNull(list);
//			Assert.assertNotNull(list.size());
//			for (Categorie categorie : list) {
//				System.out.println("Id : " + categorie.getId());
//				System.out.println("Name : " + categorie.getName());
//				System.out.println("URL : " + categorie.getUrl());
//			}
//		} catch (DAOException e) {
//			Assert.fail(e.getMessage());
//		}
//	}

	@Test
	public void testFindNombreArticles() {
		try {
			Map<Categorie, Integer> map = new HashMap<>();
			map = categorieJDBCImpl.findNombreArticles();
			Assert.assertNotNull(map);
			Set<Categorie> keySet = map.keySet();
			for (Categorie cat : keySet) {
				System.out.print("Id : " + cat.getId());
				System.out.print(" Name : " + cat.getName());
				System.out.print(" url : " + cat.getUrl());
				System.out.println(" nombre d'article : " + map.get(cat));
			}
			System.out.println("");
		} catch (DAOException e) {
			Assert.fail(e.getMessage());
		}
	}

}
