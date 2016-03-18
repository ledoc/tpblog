package fr.treeptik.utils;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.simple.ParameterizedRowMapper;

import fr.treeptik.model.Categorie;

public class CategorieParameterizedRowMapper implements
		ParameterizedRowMapper<Categorie> {
	public Categorie mapRow(ResultSet rs, int rowNum) throws SQLException {
		Categorie categorie = new Categorie();
		categorie.setId(rs.getInt("id"));
		categorie.setName(rs.getString("name"));
		categorie.setUrl(rs.getString("url"));

		return categorie;
	}
}
