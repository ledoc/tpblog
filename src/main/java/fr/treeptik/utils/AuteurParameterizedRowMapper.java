package fr.treeptik.utils;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.simple.ParameterizedRowMapper;

import fr.treeptik.model.Auteur;

public class AuteurParameterizedRowMapper implements
		ParameterizedRowMapper<Auteur> {
	public Auteur mapRow(ResultSet rs, int rowNum) throws SQLException {
		Auteur auteur = new Auteur();
		auteur.setId(rs.getInt("id"));
		auteur.setNom(rs.getString("nom"));
		auteur.setPrenom(rs.getString("prenom"));
		auteur.setUrl(rs.getString("url"));
		auteur.setEmail(rs.getString("email"));
		return auteur;
	}
}
