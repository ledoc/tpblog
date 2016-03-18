package fr.treeptik.runtime;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import fr.treeptik.dao.DAOFactory;
import fr.treeptik.exception.DAOException;
import fr.treeptik.exception.ServiceException;
import fr.treeptik.model.Article;
import fr.treeptik.model.Auteur;
import fr.treeptik.model.Categorie;
import fr.treeptik.model.Commentaire;
import fr.treeptik.service.ArticleService;
import fr.treeptik.service.AuteurService;
import fr.treeptik.service.CategorieService;
import fr.treeptik.service.CommentaireService;
import fr.treeptik.utils.DateUtil;
import fr.treeptik.utils.WatchPropertiesService;

public class Controller {

	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext(
				"applicationContext.xml");
		try {
			dynamicChoice(context);
		} catch (DAOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * Choix dynamique de l'action
	 * 
	 * @throws DAOException
	 * @throws SQLException
	 * 
	 */
	public static void dynamicChoice(ApplicationContext context)
			throws DAOException {
		/**
		 * TODO: Change Locale
		 */
		Locale locale = new Locale("fr");
		
		
		String chooseEntity = context.getMessage("entity.choice", null, locale);
		String thanYou = context.getMessage("thank.you", null, locale);
		String keyToLeave = context.getMessage("key.to.leave", null, locale);
		String mistake = context.getMessage("typing.mistake", null, locale);

		DAOFactory.loadProperties();
		Thread thread = new Thread(new WatchPropertiesService());
		thread.start();

		String choix = "";
		String entity = "";

		while (!choix.equalsIgnoreCase("q")) {

			System.out.println(chooseEntity);
			System.out.println("");
			System.out.println("1 - ARTICLE ");
			System.out.println("2 - AUTEUR ");
			System.out.println("3 - CATEGORIE ");
			System.out.println("4 - COMMENTAIRE ");
			System.out.println(keyToLeave);

			@SuppressWarnings("resource")
			Scanner scanner = new Scanner(System.in);
			choix = scanner.nextLine();

			if ("1".equals(choix)) {
				entity = "article";
				chooseAction(locale, context, entity);
			} else if ("2".equals(choix)) {
				entity = "auteur";
				chooseAction(locale, context, entity);

			} else if ("3".equals(choix)) {
				entity = "categorie";
				chooseAction(locale, context, entity);

			} else if ("4".equals(choix)) {
				entity = "commentaire";
				chooseAction(locale, context, entity);

			} else if ("q".equalsIgnoreCase(choix)) {
				System.out.println(thanYou);
			} else {
				System.out.println(mistake);
			}
		}
	}

	public static void chooseAction(Locale locale, ApplicationContext context,
			String entity) {
		ArticleService articleService = context.getBean(ArticleService.class);
		AuteurService auteurService = context.getBean(AuteurService.class);
		CategorieService categorieService = context
				.getBean(CategorieService.class);
		CommentaireService commentaireService = context
				.getBean(CommentaireService.class);


		String keyToLeave = context.getMessage("key.to.leave", null, locale);
		String mistake = context.getMessage("typing.mistake", null, locale);
		String saveEntity = context.getMessage("entity.save", null, locale);
		String deleteEntity = context.getMessage("entity.delete", null, locale);
		String updateEntity = context.getMessage("entity.update", null, locale);
		String listEntity = context.getMessage("entity.list.all", null, locale);
		String specialQuery = context.getMessage("special.query", null, locale);

		String choix = "";

		while (!choix.equalsIgnoreCase("q")) {

			System.out.println("######## MENU ############");
			System.out.println("");
			System.out.println(saveEntity);
			System.out.println(deleteEntity);
			System.out.println(updateEntity);
			System.out.println(listEntity);
			System.out.println(specialQuery);
			System.out.println(keyToLeave);

			@SuppressWarnings("resource")
			Scanner scanner = new Scanner(System.in);
			choix = scanner.nextLine();

			if ("1".equals(choix)) {

				switch (entity) {
				case "article":
					Article article = new Article();
					System.out.println("Saisir le Titre");
					String titre = scanner.nextLine();
					article.setTitre(titre);
					System.out.println("Saisir le Chapeau");
					String chapeau = scanner.nextLine();
					article.setChapeau(chapeau);
					System.out.println("Saisir le Contenu");
					String contenu = scanner.nextLine();
					article.setContenu(contenu);
					System.out
							.println("Saisir la date de création (jj/mm/YYYY");
					String creationDateString = scanner.nextLine();
					Date creationDate = DateUtil.parse(creationDateString);
					article.setCreationDate(creationDate);
					System.out.println("Est il en ligne ?");
					String enLigneString = scanner.nextLine();
					
					if (enLigneString.equals("yes")
							|| enLigneString.equals("y")
							|| enLigneString.equals("oui")
							|| enLigneString.equals("o")) {

						article.setEnLigne(true);

					} else if (enLigneString.equals("no")
							|| enLigneString.equals("n")
							|| enLigneString.equals("non")) {
						article.setEnLigne(false);

					} else {
						System.out
								.println("tapez y/o/yes/oui ou no/n/non pour votre choix SVP !!!!");
					}
					
					System.out.println(article.getEnLigne());
					System.out.println("Saisir le nom de la Catégorie");
					String categorieName = scanner.nextLine();
					try {
						article.setCategorie(categorieService
								.findCategorieByName(categorieName));
					} catch (ServiceException e1) {
						e1.printStackTrace();
					}

					System.out.println("Saisir le nom de l'Auteur");
					String auteurName = scanner.nextLine();
					try {
						article.setAuteur(auteurService
								.findAuteurByName(auteurName));
					} catch (ServiceException e1) {
						e1.printStackTrace();
					}

					try {
						articleService.save(article);
					} catch (ServiceException e) {
						e.printStackTrace();
					}
					try {
						dynamicChoice(context);
					} catch (DAOException e2) {
						e2.printStackTrace();
					}
					break;
				case "auteur":
					Auteur auteur = new Auteur();
					System.out.println("Saisir le Nom");
					String nom = scanner.nextLine();
					auteur.setNom(nom);
					System.out.println("Saisir le Prénom");
					String prenom = scanner.nextLine();
					auteur.setPrenom(prenom);
					System.out.println("Saisir l'Url");
					String url = scanner.nextLine();
					auteur.setUrl(url);
					System.out.println("Saisir l'Email");
					String email = scanner.nextLine();
					auteur.setEmail(email);

					try {
						auteurService.save(auteur);
					} catch (ServiceException e) {
						e.printStackTrace();
					}
					try {
						dynamicChoice(context);
					} catch (DAOException e2) {
						e2.printStackTrace();
					}
					break;

				case "categorie":
					Categorie categorie = new Categorie();
					System.out.println("Saisir le Nom");
					String name = scanner.nextLine();
					categorie.setName(name);
					System.out.println("Saisir l'Url");
					String urlCat = scanner.nextLine();
					categorie.setUrl(urlCat);

					try {
						categorieService.save(categorie);
					} catch (ServiceException e) {
						e.printStackTrace();
					}
					try {
						dynamicChoice(context);
					} catch (DAOException e2) {
						e2.printStackTrace();
					}
					break;

				case "commentaire":
					Commentaire commentaire = new Commentaire();

					System.out.println("Saisir le Nom");
					String nomCom = scanner.nextLine();
					commentaire.setNom(nomCom);
					System.out.println("Saisir l'Email");
					String emailCom = scanner.nextLine();
					commentaire.setEmail(emailCom);
					System.out.println("Saisir le Texte");
					String texte = scanner.nextLine();
					commentaire.setTexte(texte);
					System.out
							.println("Saisir la date de création (jj/mm/YYYY");
					String creationDateStringCom = scanner.nextLine();
					Date creationDateCom = DateUtil
							.parse(creationDateStringCom);
					commentaire.setCreationDate(creationDateCom);
					System.out.println("A t'il été validé");
					String validation = scanner.nextLine();
					if (validation.equals("yes") || validation.equals("y")
							|| validation.equals("oui")
							|| validation.equals("o")) {

						commentaire.setValidation(true);

					} else if (validation.equals("no")
							|| validation.equals("n")
							|| validation.equals("non")) {

						commentaire.setValidation(false);

					} else {
						System.out
								.println("tapez y/o/yes/oui ou no/n/non pour votre choix SVP !!!!");
					}

					System.out.println("Saisir le nom de l'Article associé");
					String articleName = scanner.nextLine();

					try {
						commentaire.setArticle(articleService
								.findArticleByName(articleName));
					} catch (ServiceException e1) {
						e1.printStackTrace();
					}
					try {
						commentaireService.save(commentaire);
					} catch (ServiceException e) {
						e.printStackTrace();
					}
					try {
						dynamicChoice(context);
					} catch (DAOException e2) {
						e2.printStackTrace();
					}
					break;

				default:
					System.out.println(mistake);
					break;
				}

				// Remove()
			} else if ("2".equals(choix)) {
				switch (entity) {
				case "article":
					Article article = new Article();
					System.out
							.println("Saisir le Titre de l'article à supprimer");
					String titre = scanner.nextLine();
					article.setTitre(titre);
					try {
						articleService.remove(article);
					} catch (ServiceException e) {
						e.printStackTrace();
					}
					try {
						dynamicChoice(context);
					} catch (DAOException e2) {
						e2.printStackTrace();
					}
					break;
				case "auteur":
					Auteur auteur = new Auteur();
					System.out.println("Saisir le Nom");
					String nom = scanner.nextLine();
					auteur.setNom(nom);
					try {
						auteurService.remove(auteur);
					} catch (ServiceException e) {
						e.printStackTrace();
					}
					try {
						dynamicChoice(context);
					} catch (DAOException e2) {
						e2.printStackTrace();
					}
					break;

				case "categorie":
					Categorie categorie = new Categorie();
					System.out.println("Saisir le Nom");
					String name = scanner.nextLine();
					categorie.setName(name);
					try {
						categorieService.remove(categorie);
					} catch (ServiceException e) {
						e.printStackTrace();
					}
					try {
						dynamicChoice(context);
					} catch (DAOException e2) {
						e2.printStackTrace();
					}
					break;

				case "commentaire":
					Commentaire commentaire = new Commentaire();
					System.out.println("Saisir le Nom");
					String nomCom = scanner.nextLine();

					commentaire.setNom(nomCom);

					try {
						commentaireService.remove(commentaire);
					} catch (ServiceException e) {
						e.printStackTrace();
					}
					try {
						dynamicChoice(context);
					} catch (DAOException e2) {
						e2.printStackTrace();
					}
					break;

				default:
					try {
						dynamicChoice(context);
					} catch (DAOException e2) {
						e2.printStackTrace();
					}
					break;
				}
			}
			// else if ("3".equals(choix)) {
			//
			// switch (entity) {
			// case "article":
			// Article article = new Article();
			// ArticleDAO articleDAO = (ArticleDAO) DAOFactory
			// .chooseDAOFactory(entity);
			// break;
			// case "auteur":
			// Auteur auteur = new Auteur();
			// AuteurDAO auteurDAO = ((AuteurDAO) DAOFactory
			// .chooseDAOFactory(entity));
			// break;
			//
			// case "categorie":
			// Categorie categorie = new Categorie();
			// CategorieDAO categorieDAO = (CategorieDAO) DAOFactory
			// .chooseDAOFactory(entity);
			// break;
			//
			// case "commentaire":
			//
			// break;
			//
			// default:
			// break;
			// }
			// }
			else if ("4".equals(choix)) {

				switch (entity) {
				case "article":
					List<Article> listAticles = new ArrayList<>();
					try {
						listAticles = articleService.findAll();
						for (Article article : listAticles) {
							printArticle(article);

						}
					} catch (ServiceException e) {
						e.printStackTrace();
					}
					try {
						dynamicChoice(context);
					} catch (DAOException e2) {
						e2.printStackTrace();
					}
					break;
				case "auteur":
					List<Auteur> listAuteurs = new ArrayList<>();
					try {
						listAuteurs = auteurService.findAll();
						for (Auteur auteur : listAuteurs) {
							printAuteur(auteur);
						}
					} catch (ServiceException e) {
						e.printStackTrace();
					}
					try {
						dynamicChoice(context);
					} catch (DAOException e2) {
						e2.printStackTrace();
					}
					break;

				case "categorie":
					List<Categorie> listCategories = new ArrayList<>();
					try {
						listCategories = categorieService.findAll();
						for (Categorie categorie : listCategories) {
							printCategorie(categorie);
						}
					} catch (ServiceException e) {
						e.printStackTrace();
					}
					try {
						dynamicChoice(context);
					} catch (DAOException e2) {
						e2.printStackTrace();
					}
					break;

				case "commentaire":
					List<Commentaire> listCommentaires = new ArrayList<>();
					try {
						listCommentaires = commentaireService.findAll();
						for (Commentaire commentaire : listCommentaires) {
							printCommentaire(commentaire);
						}
					} catch (ServiceException e) {
						e.printStackTrace();
					}
					try {
						dynamicChoice(context);
					} catch (DAOException e2) {
						e2.printStackTrace();
					}

					break;

				default:
					break;

				}

			} else if ("5".equals(choix)) {
				switch (entity) {
				case "article":
					List<Article> listArticles = new ArrayList<>();
					System.out
							.println("Lister les articles parus sur une période de "
									+ "temps - Entrez la Date de début (dd/mm/YYYY) :");
					String dateDebutString = scanner.nextLine();
					Date dateDebut = DateUtil.parse(dateDebutString);
					System.out.println("Entrez la Date de fin (dd/mm/YYYY) :");
					String dateFinString = scanner.nextLine();
					Date dateFin = DateUtil.parse(dateFinString);
					try {
						listArticles = articleService.findArticlesInTimePeriod(
								dateDebut, dateFin);
					} catch (ServiceException e) {
						e.printStackTrace();
					}
					for (Article article : listArticles) {
						printArticle(article);
					}
					try {
						dynamicChoice(context);
					} catch (DAOException e2) {
						e2.printStackTrace();
					}

					break;
				case "auteur":
					String choix1 = null;
					System.out
							.println("1 - Voir le nombre d'articles en ligne par Auteur? ");
					System.out
							.println("2 - Voir les articles les plus commentés par Auteur? ");
					System.out.println("q - Pour quitter");
					choix1 = scanner.nextLine();

					if ("1".equals(choix1)) {
						Map<Auteur, Long> map = null;
						try {
							map = auteurService.findNombreArticlesEnLigne();
						} catch (ServiceException e) {
							e.printStackTrace();
						}
						for (Entry<Auteur, Long> entry : map.entrySet()) {
							System.out.println(entry.getKey().getNom()
									.toUpperCase()
									+ " a "
									+ entry.getValue()
									+ " articles publiés");
						}
					} else if ("2".equals(choix1)) {
						Map<Auteur, Integer> map = null;
						try {
							map = auteurService.findArticlesMaxCommente();
						} catch (ServiceException e) {
							e.printStackTrace();
						}
						for (Entry<Auteur, Integer> entry : map.entrySet()) {
							System.out.println(entry.getKey().getNom()
									.toUpperCase()
									+ " a "
									+ entry.getValue()
									+ " articles publiés");
						}
					} else if ("q".equalsIgnoreCase(choix)) {
						System.out.println("Merci a bientot");
					} else {
						System.out.println("Erreur de saisie");
					}
					try {
						dynamicChoice(context);
					} catch (DAOException e2) {
						e2.printStackTrace();
					}
					break;

				case "categorie":
					choix1 = null;
					// CategorieDAO categorieDAO = (CategorieDAO) DAOFactory
					// .chooseDAOFactory(entity);
					System.out
							.println("1 - Voir les Catégories sans article? ");
					System.out
							.println("2 - Voir la Catégorie avec le plus d'articles? ");
					System.out
							.println("3 - Voir le nombre d'article par Catégorie? ");
					System.out.println("q - Pour quitter");
					choix1 = scanner.nextLine();

					if ("1".equals(choix1)) {
						List<Categorie> listCategories = null;
						try {
							listCategories = categorieService.findNotArticle();
						} catch (ServiceException e) {
							e.printStackTrace();
						}
						for (Categorie categorie : listCategories) {
							printCategorie(categorie);
						}
					} else if ("2".equals(choix1)) {
						/**
						 * TODO
						 */
					} else if ("3".equals(choix1)) {
						Map<Categorie, Integer> map = new HashMap<>();
						try {
							map = categorieService.findNombreArticles();
						} catch (ServiceException e) {
							e.printStackTrace();
						}

						for (Entry<Categorie, Integer> entry : map.entrySet()) {
							System.out.println("La catégorie "
									+ entry.getKey().getName().toUpperCase()
									+ " a " + entry.getValue()
									+ " articles publiés");
						}
					} else if ("q".equalsIgnoreCase(choix)) {
						System.out.println("Merci a bientot");
					} else {
						System.out.println("Erreur de saisie");
					}
					try {
						dynamicChoice(context);
					} catch (DAOException e2) {
						e2.printStackTrace();
					}
					break;

				case "commentaire":
					System.out
							.println("Pas de commandes spéciales pour les Commentaires ");
					try {
						dynamicChoice(context);
					} catch (DAOException e2) {
						e2.printStackTrace();
					}
					break;

				default:
					try {
						dynamicChoice(context);
					} catch (DAOException e2) {
						e2.printStackTrace();
					}
					break;
				}
			}
		}
	}

	private static void printArticle(Article article) {
		String articleAnswer = null;

		System.out.println("Article - Titre : " + article.getTitre());
		System.out.println("\t Chapeau : " + article.getChapeau());
		System.out.println("\t Contenu : " + article.getContenu());
		System.out.println("\t Date de création : "
				+ DateUtil.format(article.getCreationDate()));
		if (article.getEnLigne()) {

			articleAnswer = "Oui";

		}
		if (!article.getEnLigne()) {
			articleAnswer = "Non";
		}
		System.out.println("\t En ligne : " + articleAnswer);

		System.out.println("\t Catégorie associée : "
				+ article.getCategorie().getName());

		System.out.println("\t Auteur : " + article.getAuteur().getNom());
	}

	private static void printAuteur(Auteur auteur) {
		System.out.println("Auteur - Nom : " + auteur.getNom());
		System.out.println("Saisir le Nom");
		System.out.println("\t Prénom : " + auteur.getPrenom());
		System.out.println("\t Url : " + auteur.getUrl());
		System.out.println("\t Email : " + auteur.getUrl());
	}

	private static void printCategorie(Categorie categorie) {
		System.out.println("Categorie - Nom : " + categorie.getName());
		System.out.println("\t Url : " + categorie.getUrl());

	}

	private static void printCommentaire(Commentaire commentaire) {

		String commentaireAnswer = null;
		System.out.println("Commentaire - Nom : " + commentaire.getNom());
		System.out.println("\t Email : " + commentaire.getEmail());
		System.out.println("\t Texte : " + commentaire.getTexte());
		System.out.println("\t Date de création : "
				+ DateUtil.format(commentaire.getCreationDate()));
		if (commentaire.getValidation().equals(true)) {

			commentaireAnswer = "Oui";

		} else if (commentaire.getValidation().equals(false)) {
			commentaireAnswer = "Non";
		}
		System.out.println("\t Validation :" + commentaireAnswer);

		System.out.println("\t Article associé : "
				+ commentaire.getArticle().getTitre());

	}
}
