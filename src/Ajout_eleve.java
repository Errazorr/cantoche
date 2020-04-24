import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import org.eclipse.swt.widgets.Button;

public class Ajout_eleve {
	//Attibuts
	private static Text textNom;
	private static Text textPrenom;
	private static Text textDP;
	private static Text textJours;
	private static Text textRegime;
	private static Text textClasse;
	private static int prix_mensuel;

	
	public static void main(String[] args) {
		//Affichage de la page
		Display display = Display.getDefault();
		Shell Ajout_eleve = new Shell();
		Ajout_eleve.setSize(860, 449);
		Ajout_eleve.setText("Ajouter un \u00E9l\u00E8ve");
		
		//Label nom
		Label lblNom = new Label(Ajout_eleve, SWT.NONE);
		lblNom.setBounds(222, 43, 40, 25);
		lblNom.setText("Nom :");
		
		//Label prénom
		Label lblPrenom = new Label(Ajout_eleve, SWT.NONE);
		lblPrenom.setBounds(205, 102, 58, 25);
		lblPrenom.setText("Pr\u00E9nom :");
		
		//texte nom
		textNom = new Text(Ajout_eleve, SWT.BORDER);
		textNom.setBounds(292, 40, 131, 31);
		
		//texte prenom
		textPrenom = new Text(Ajout_eleve, SWT.BORDER);
		textPrenom.setBounds(292, 99, 131, 31);
		
		//Bouton de validation
		Button btnAjouter = new Button(Ajout_eleve, SWT.NONE);
		btnAjouter.setBounds(540, 114, 113, 35);
		btnAjouter.setText("Ajouter");
		
		//Label demi pensionnaire
		Label lblDemipensionnaire = new Label(Ajout_eleve, SWT.NONE);
		lblDemipensionnaire.setText("Demi-pensionnaire :");
		lblDemipensionnaire.setBounds(122, 224, 141, 25);
		
		//label jours
		Label lblQuelsJours = new Label(Ajout_eleve, SWT.NONE);
		lblQuelsJours.setText("Quels jours (L,Ma,Me,J,V) :");
		lblQuelsJours.setBounds(87, 277, 175, 25);
		
		//label régime alimentaire
		Label lblRgimeAlimentaire = new Label(Ajout_eleve, SWT.NONE);
		lblRgimeAlimentaire.setText("R\u00E9gime alimentaire :");
		lblRgimeAlimentaire.setBounds(122, 342, 141, 25);
		
		//texte demi pensionnaire
		textDP = new Text(Ajout_eleve, SWT.BORDER);
		textDP.setBounds(292, 218, 131, 31);
		
		//texte jours
		textJours = new Text(Ajout_eleve, SWT.BORDER);
		textJours.setBounds(291, 274, 131, 31);
		
		//texte regime
		textRegime = new Text(Ajout_eleve, SWT.BORDER);
		textRegime.setBounds(292, 336, 131, 31);
		
		//Bouton pour vider les cases
		Button btnVider = new Button(Ajout_eleve, SWT.NONE);
		btnVider.setText("Vider");
		btnVider.setBounds(540, 195, 113, 35);
		
		//Label pour afficher un message d'erreur
		Label lblErreur = new Label(Ajout_eleve, SWT.NONE);
		lblErreur.setBounds(540, 342, 256, 20);
		
		//Label classe
		Label lblClasse = new Label(Ajout_eleve, SWT.NONE);
		lblClasse.setText("Classe :");
		lblClasse.setBounds(214, 162, 48, 25);
		
		//texte classe
		textClasse = new Text(Ajout_eleve, SWT.BORDER);
		textClasse.setBounds(292, 159, 131, 31);
		
		//Bouton pour revenir sur la page d'accueil
		Button btnRetour = new Button(Ajout_eleve, SWT.NONE);
		btnRetour.setText("Retour");
		btnRetour.setBounds(540, 270, 113, 35);
		
		btnAjouter.addSelectionListener(new SelectionAdapter() {
			 
			   @Override
			   public void widgetSelected(SelectionEvent arg0) {
				   //Connexion à la bdd
				   String url="jdbc:mysql://localhost/cantine?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
			        String user="root";
			        String password="";
			        try {
			             Connection cnx = DriverManager.getConnection(url, user, password);
			             Statement stm = cnx.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			             //On cherche si l'élève existe déjà
			             ResultSet rs = stm.executeQuery("select * from compte where nom ='"+textNom.getText()+"' and prenom ='"+textPrenom.getText()+"'");

			             if(rs.next()){ //Si il existe déjà
			            	 lblErreur.setText("Elève déjà existant"); //On affiche le message d'erreur
			             }
			             else { //Sinon
			            	 final String separateur = ",";
					         String jours = textJours.getText();
					         
					         String nbjours[] = jours.split(separateur);
				        	 prix_mensuel = nbjours.length * 6 * 4;
			            	 
			            	//On enregistre les données qu'on a entré dans la bdd
			            	 PreparedStatement stmt = cnx.prepareStatement("INSERT INTO compte(nom, prenom, classe, DP, jours, regime, role, prix_mensuel) VALUES (?, ?, ?, ?, ?, ?, 'eleve', ?)");
			            	 stmt.setString(1, textNom.getText());
			            	 stmt.setString(2, textPrenom.getText());
			            	 stmt.setString(3, textClasse.getText());
			            	 stmt.setString(4, textDP.getText());
			            	 stmt.setString(5, textJours.getText());
			            	 stmt.setString(6, textRegime.getText());
			            	 stmt.setString(7, String.valueOf(prix_mensuel));

			            	 stmt.executeUpdate();
			            	 lblErreur.setText("Elève ajouté"); //On affiche le texte

			             }
			         } catch (SQLException e) { //S'il y a un problème lors de la connexion à la bdd
			             System.out.println("Une erreur est survenue lors de la connexion à la base de données"); //On affiche le texte
			             e.printStackTrace();
			         
			};  
			   }
		});
		
		btnVider.addSelectionListener(new SelectionAdapter() { //Quand on appui sur le bouton vider
			 
			   @Override
			   public void widgetSelected(SelectionEvent arg0) { //On vide toutes les zones de texte
				   	textNom.setText("");
	            	textPrenom.setText("");
	            	textClasse.setText("");
	            	textDP.setText("");
	            	textJours.setText("");
	            	textRegime.setText("");
			   }
		});
		
		btnRetour.addSelectionListener(new SelectionAdapter() { //Quand on appui sur le bouton retour
			 
			   @Override
			   public void widgetSelected(SelectionEvent arg0) {
				   Ajout_eleve.close(); //On ferme la page 
				   Accueil.main(args); //On ouvre la page accueil
			   }
		});

		//Affichage  de la page
		Ajout_eleve.open();
		Ajout_eleve.layout();
		while (!Ajout_eleve.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
		
		

	}
}
