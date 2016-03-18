package fr.treeptik.dao.impl;

import java.util.List;


import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import fr.treeptik.dao.CommentaireDAO;
import fr.treeptik.exception.DAOException;
import fr.treeptik.model.Commentaire;
import fr.treeptik.utils.CommentaireParameterizedRowMapper;
import fr.treeptik.utils.DateUtil;
@Repository
public class CommentaireJDBCImpl extends JdbcDaoSupport implements
		CommentaireDAO {

	@Autowired
	private DataSource dataSource;

	@PostConstruct
	private void init() {
		setDataSource(dataSource);
	}

	@Override
	public Commentaire save(Commentaire commentaire) throws DAOException {
		String sql = "INSERT INTO Commentaire "
				+ " (article_id , nom , email , texte, creationDate , validation) "
				+ "VALUES (?,?,?,?,?,?)";
		getJdbcTemplate().update(
				sql,
				new Object[] { commentaire.getArticle().getId(),
						commentaire.getNom(), commentaire.getEmail(),
						commentaire.getTexte(),
						DateUtil.toSqlDate(commentaire.getCreationDate()),
						commentaire.getValidation() });

		return commentaire;
	}

	@Override
	public void remove(Commentaire commentaire) throws DAOException {
		String sql = "DELETE FROM Commentaire WHERE nom=?";
		getJdbcTemplate().update(sql, new Object[] { commentaire.getNom() });

	}

	@Override
	public List<Commentaire> findAll() throws DAOException {

		String sql = "SELECT * FROM Commentaire ";
		List<Commentaire> list = getJdbcTemplate().query(sql,
				new CommentaireParameterizedRowMapper());
		return list;
	}

	@Override
	public Commentaire findCommentaire(Integer id) throws DAOException {
		String sql = "SELECT * FROM Commentaire"
//				" com INNER JOIN Article art ON art.id=com.article_id "
//				+ "INNER JOIN Auteur aut ON aut.id=art.auteur_id "
//				+ " INNER JOIN Categorie cat ON cat.id=art.categorie_id WHERE id=?"
				;
		List<Commentaire> list = getJdbcTemplate().query(sql,
				new Object[] { id }, new CommentaireParameterizedRowMapper());
		Commentaire commentaire = list.get(0);

		return commentaire;

	}
}