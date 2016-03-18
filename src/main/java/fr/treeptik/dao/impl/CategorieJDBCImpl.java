package fr.treeptik.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import fr.treeptik.dao.CategorieDAO;
import fr.treeptik.exception.DAOException;
import fr.treeptik.model.Categorie;
import fr.treeptik.utils.CategorieParameterizedRowMapper;

@Repository
public class CategorieJDBCImpl extends JdbcDaoSupport implements CategorieDAO {
	
	
	@Autowired
	private DataSource dataSource;
	
	@PostConstruct
	private void init(){
		setDataSource(dataSource);
	}

	@Override
	public Categorie save(Categorie categorie) throws DAOException {
		String sql = "INSERT INTO Categorie (`name`, `url`) VALUES (?,?)";
		getJdbcTemplate().update(sql,
				new Object[] { categorie.getName(), categorie.getUrl() });

		return categorie;
	}

	@Override
	public void remove(Categorie categorie) throws DAOException {
		String sql = "DELETE FROM Categorie WHERE name=?";
		getJdbcTemplate().update(sql, new Object[] { categorie.getName() });

	}

	@Override
	public List<Categorie> findAll() throws DAOException {
		String sql = "SELECT * FROM Categorie ";
		List<Categorie> list = getJdbcTemplate().query(sql,
				new CategorieParameterizedRowMapper());

		return list;
	}

	@Override
	public Categorie findCategorie(Integer id) throws DAOException {
		String sql = "SELECT * FROM Categorie WHERE id=?";
		List<Categorie> list = getJdbcTemplate().query(sql,
				new Object[] { id }, new CategorieParameterizedRowMapper());
		Categorie categorie = list.get(0);

		return categorie;
	}

	@Override
	public Categorie findCategorieByName(String categorieName) throws DAOException {
		String sql = "SELECT * FROM Categorie WHERE name=?";
		List<Categorie> list = getJdbcTemplate().query(sql,
				new Object[] { categorieName },
				new CategorieParameterizedRowMapper());
		Categorie categorie = list.get(0);
		return categorie;
	}

	@Override
	public List<Categorie> findNotArticle() throws DAOException {
		String sql = "SELECT * FROM Categorie cat "
				+ " WHERE cat.id NOT IN (SELECT cat.id "
				+ " FROM Categorie cat INNER JOIN Article art "
				+ " ON art.categorie_id=cat.id)";
		List<Categorie> list = getJdbcTemplate().query(sql,
				new CategorieParameterizedRowMapper());

		return list;

	}

	@Override
	public Map<Categorie, Integer> findMaxArticles() throws DAOException {
		String sql = "SELECT cat.name, art.categorie_id, cat.url, "
				+ " COUNT(art.categorie_id) AS 'nbArticles' FROM Categorie cat "
				+ " LEFT JOIN Article art ON cat.id=art.categorie_id "
				+ " GROUP BY cat.name HAVING COUNT(art.categorie_id) = "
				+ " (SELECT MAX(t.nbeArticles)FROM (SELECT cat.name, "
				+ " art.categorie_id, COUNT(art.categorie_id) AS 'nbeArticles' FROM "
				+ " Categorie cat LEFT JOIN Article art ON "
				+ " cat.id=art.categorie_id GROUP BY cat.name "
				+ " ORDER BY nbeArticles)t)";
		Map<Categorie, Integer> map = new HashMap<>();
		List<Map<String, Object>> list = getJdbcTemplate().queryForList(sql);
		for (Map<String, Object> map2 : list) {
			Categorie categorie = new Categorie();
			categorie.setId((Integer) map2.get("id"));
			categorie.setName((String) map2.get("name"));
			categorie.setUrl((String) map2.get("url"));
			map.put(categorie, (Integer) map2.get("nbArticles"));
		}

		return map;
	}

	@Override
	public Map<Categorie, Integer> findNombreArticles() throws DAOException {
		Map<Categorie, Integer> map = new HashMap<>();
		String sql = "SELECT `cat.name`,`cat.url`, `cat.id`, "
				+ " COUNT(`art.id`) AS 'nbArticles' FROM Categorie cat "
				+ " LEFT JOIN Article art ON cat.id=art.categorie_id "
				+ " GROUP BY cat.name";
		List<Map<String, Object>> list = getJdbcTemplate().queryForList(sql);
		for (Map<String, Object> map2 : list) {
			Categorie categorie = new Categorie();
			categorie.setId((Integer) map2.get("id"));
			categorie.setName((String) map2.get("name"));
			categorie.setUrl((String) map2.get("url"));
			map.put(categorie, (Integer) map2.get("nbArticles"));
		}

		return map;
	}

}
