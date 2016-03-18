package fr.treeptik.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import fr.treeptik.dao.AuteurDAO;
import fr.treeptik.exception.DAOException;
import fr.treeptik.model.Auteur;
import fr.treeptik.utils.AuteurParameterizedRowMapper;

@Repository
public class AuteurJDBCImpl extends JdbcDaoSupport implements AuteurDAO {
	
	@Autowired
	private DataSource dataSource;
	
	@PostConstruct
	private void init(){
		setDataSource(dataSource);
	}
	
	@Override
	public Auteur save(Auteur auteur) throws DAOException {
			String sql = "INSERT INTO Auteur "
					+ " (nom , prenom ,url , email) " + "VALUES (?,?,?,?)";
			getJdbcTemplate().update(sql,
					new Object[] {auteur.getNom(),auteur.getPrenom(), auteur.getUrl(), auteur.getEmail() });

		return auteur;
	}

	@Override
	public void remove(Auteur auteur) throws DAOException {
		String sql = "DELETE FROM Auteur WHERE nom=?";
		getJdbcTemplate().update(sql, new Object[] { auteur.getNom() });
		getJdbcTemplate().update(sql,
				new Object[] {auteur.getNom()});
	}

	@Override
	public List<Auteur> findAll() throws DAOException {
			String sql = "SELECT * FROM Auteur ";
			List<Auteur> list = getJdbcTemplate().query(sql,
					new AuteurParameterizedRowMapper());


		return list;
	}

	@Override
	public Auteur findAuteur(Integer id) throws DAOException {
		String sql = "SELECT * FROM Auteur WHERE id=?";
		List<Auteur> list = getJdbcTemplate().query(sql,
				new Object[] { id }, new AuteurParameterizedRowMapper());
		Auteur auteur = list.get(0);

		return auteur;
	}

	@Override
	public Map<Auteur, Long> findNombreArticlesEnLigne() throws DAOException {
		
		
		
		Map<Auteur, Long> map = new HashMap<>();
		String sql = "SELECT aut.id, aut.email, "
							+ " aut.nom,aut.prenom, aut.url, "
							+ " art.enLigne, COUNT(art.categorie_id) AS 'nbArticle' "
							+ " FROM Article art RIGHT JOIN Auteur aut "
							+ " ON aut.id=art.auteur_id GROUP BY aut.nom, "
							+ " art.enLigne HAVING art.enLigne IN "
							+ " (SELECT ar.enLigne FROM Article ar LEFT JOIN "
							+ " Auteur au ON au.id=ar.auteur_id "
							+ " HAVING ar.enLigne <> 0); ";

		List<Map<String, Object>> list = getJdbcTemplate().queryForList(sql);
		for (Map<String, Object> map2 : list) {
			Auteur auteur = new Auteur();
			auteur.setId((Integer) map2.get("id"));
			auteur.setNom((String) map2.get("nom"));
			auteur.setUrl((String) map2.get("url"));
			auteur.setEmail((String) map2.get("email"));
			map.put(auteur, (Long) map2.get("nbArticle"));
		}

		return map;
	}

	@Override
	public Map<Auteur, Integer> findArticlesMaxCommente() throws DAOException {
					String sql = "SELECT *, COUNT(com.article_id) AS 'nbcommentaire' "
							+ " FROM Commentaire com JOIN Article art "
							+ " ON com.article_id=art.id JOIN Auteur aut "
							+ " ON art.auteur_id=aut.id GROUP BY aut.nom,art.id "
							+ " HAVING COUNT(com.article_id) = (SELECT MAX(t.nbcommentaires) "
							+ " FROM (SELECT com.article_id,art.auteur_id, COUNT(com.article_id) "
							+ " AS 'nbcommentaire' FROM Commentaire com  "
							+ " LEFT JOIN Article art ON com.article_id=art.id "
							+ " LEFT JOIN Auteur aut ON art.auteur_id=aut.id "
							+ " GROUP BY aut.nom,art.id)t); ";
					Map<Auteur, Integer> map = new HashMap<>();
					List<Map<String, Object>> list = getJdbcTemplate().queryForList(sql);
					for (Map<String, Object> map2 : list) {
						Auteur auteur = new Auteur();
						auteur.setId((Integer) map2.get("id"));
						auteur.setNom((String) map2.get("nom"));
						auteur.setUrl((String) map2.get("url"));
						auteur.setEmail((String) map2.get("email"));
						map.put(auteur, (Integer) map2.get("nbArticle"));
					}

		return map;
	}

	@Override
	public Auteur findAuteurByName(String auteurName) throws DAOException {
		String sql = "SELECT * FROM Auteur WHERE nom=?";
		List<Auteur> list = getJdbcTemplate().query(sql,
				new Object[] { auteurName },
				new AuteurParameterizedRowMapper());
		Auteur auteur = list.get(0);
		return auteur;
	}
	
}
