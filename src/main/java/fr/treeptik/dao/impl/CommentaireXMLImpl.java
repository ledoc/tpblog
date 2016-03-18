package fr.treeptik.dao.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import fr.treeptik.dao.CommentaireDAO;
import fr.treeptik.exception.DAOException;
import fr.treeptik.model.Commentaire;
import fr.treeptik.xml.ListCommentaires;

@Repository
public class CommentaireXMLImpl implements CommentaireDAO {

	@Autowired
	private ArticleJDBCImpl articleJDBCImpl;

	@Override
	public Commentaire save(Commentaire commentaire) throws DAOException {
		try {
			JAXBContext jaxbcontext = JAXBContext.newInstance("fr.treeptik.springexercice.xml");
			Unmarshaller unmarshaller = jaxbcontext.createUnmarshaller();
			ListCommentaires listCommentaires = (ListCommentaires) unmarshaller.unmarshal(new File(
					"commentaires.xml"));
			fr.treeptik.xml.Commentaires commentairesXML = new fr.treeptik.xml.Commentaires();

			commentairesXML.setArtId(commentaire.getArticle().getId());

			// Etape pour ajouter Date dans XMLGregorianCalendar
			GregorianCalendar gregorianCalendar = new GregorianCalendar();
			gregorianCalendar.setTime(commentaire.getCreationDate());
			DatatypeFactory datatypeFactory = DatatypeFactory.newInstance();
			XMLGregorianCalendar xmlGregorianCalendar = datatypeFactory
					.newXMLGregorianCalendar(gregorianCalendar);
			commentairesXML.setComDate(xmlGregorianCalendar);

			commentairesXML.setComEmail(commentaire.getEmail());
			commentairesXML.setComId(commentaire.getId());
			commentairesXML.setComNom(commentaire.getNom());
			commentairesXML.setComTexte(commentaire.getTexte());
			commentairesXML.setComValidation(commentaire.getValidation());

			listCommentaires.getCommentaires().add(commentairesXML);

			Marshaller marshaller = jaxbcontext.createMarshaller();
			marshaller.marshal(listCommentaires, new File("commentaires.xml"));

		} catch (JAXBException | DatatypeConfigurationException e) {
			throw new DAOException(e.getMessage(), e.getCause());
		}
		return commentaire;
	}

	@Override
	public void remove(Commentaire commentaire) throws DAOException {
		JAXBContext jaxbcontext;
		try {
			jaxbcontext = JAXBContext.newInstance("fr.treeptik.springexercice.xml");
			Unmarshaller unmarshaller = jaxbcontext.createUnmarshaller();
			ListCommentaires listCommentaires = (ListCommentaires) unmarshaller.unmarshal(new File(
					"commentaires.xml"));
			
			ArrayList<fr.treeptik.xml.Commentaires> tempList = new ArrayList<>();
			tempList.addAll(listCommentaires.getCommentaires());
			
			for (fr.treeptik.xml.Commentaires commentaires : tempList) {
				if(commentaire.getId().equals(commentaires.getComId())){
					listCommentaires.getCommentaires().remove(commentaires);
				}								
			}
			
			Marshaller marshaller = jaxbcontext.createMarshaller();
			marshaller.marshal(listCommentaires, new File("commentaires.xml"));
			
		} catch (JAXBException e) {
			throw new DAOException(e.getMessage(), e.getCause());
		}


	}

	@Override
	public List<Commentaire> findAll() throws DAOException {
		JAXBContext jaxbcontext;
		List<Commentaire> list = new ArrayList<>();
		try {
			jaxbcontext = JAXBContext.newInstance("fr.treeptik.springexercice.xml");
			Unmarshaller unmarshaller = jaxbcontext.createUnmarshaller();
			ListCommentaires listCommentaires = (ListCommentaires) unmarshaller.unmarshal(new File(
					"commentaires.xml"));

			for (fr.treeptik.xml.Commentaires commentairesXML : listCommentaires
					.getCommentaires()) {

				Commentaire commentaire = new Commentaire();

				commentaire.setArticle(articleJDBCImpl.findArticle(commentairesXML.getArtId()));
				commentaire.setCreationDate(commentairesXML.getComDate().toGregorianCalendar().getTime());
				commentaire.setEmail(commentairesXML.getComEmail());
				commentaire.setId(commentairesXML.getComId());
				commentaire.setNom(commentairesXML.getComNom());
				commentaire.setTexte(commentairesXML.getComTexte());
				commentaire.setValidation(commentairesXML.isComValidation());

				list.add(commentaire);
			}
		} catch (JAXBException e) {
			throw new DAOException(e.getMessage(), e.getCause());
		}

		return list;
	}

	@Override
	public Commentaire findCommentaire(Integer id) throws DAOException {
		throw new UnsupportedOperationException();
	}

}
