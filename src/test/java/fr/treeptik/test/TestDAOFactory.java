package fr.treeptik.test;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import fr.treeptik.dao.CommentaireDAO;
import fr.treeptik.dao.impl.CommentaireJDBCImpl;
import fr.treeptik.dao.impl.CommentaireXMLImpl;
import fr.treeptik.utils.JDBCUtil;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:test/applicationContextTest.xml" })
public class TestDAOFactory {
	@Autowired
	private CommentaireDAO commentaireDAO;

	@Autowired
	private JDBCUtil jdbcUtil;

	@Test
	public void testGetCommentairesDAO() {

		if (commentaireDAO instanceof CommentaireXMLImpl) {
			System.out.println("XML");
			Assert.assertTrue(true);
		} else if (commentaireDAO instanceof CommentaireJDBCImpl) {
			System.out.println("JDBC");
			Assert.assertTrue(true);
		} else {
			Assert.assertTrue(false);
		}
	}
}
