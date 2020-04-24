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

public class Ajout_prof {
	//Attibuts
	private static Text textNom;
	private static Text textPrenom;
	private static Text textDP;
	private static Text textJours;
	private static Text textRegime;
	private static int prix_mensuel;

	
	public static void main(String[] args) {
		//Affichage de la page
		Display display = Display.getDefault();
		Shell Ajout_prof = new Shell();
		Ajout_prof.setSize(860, 449);
		Ajout_prof.setText("Ajouter un professeur");
		
		//Label nom
		Label lblNom = new Label(Ajout_prof, SWT.NONE);
		lblNom.setBounds(223, 59, 40, 25);
		lblNom.setText("Nom :");
		
		//Label prénom
		Label lblPrenom = new Label(Ajout_prof, SWT.NONE);
		lblPrenom.setBounds(205, 121, 58, 25);
		lblPrenom.setText("Pr\u00E9nom :");
		
		//texte nom
		textNom = new Text(Ajout_prof, SWT.BORDER);
		textNom.setBounds(292, 56, 131, 31);
		
		//texte prenom
		textPrenom = new Text(Ajout_prof, SWT.BORDER);
		textPrenom.setBounds(292, 118, 131, 31);
		
		//Bouton de validation
		Button btnAjouter = new Button(Ajout_prof, SWT.NONE);
		btnAjouter.setBounds(540, 114, 113, 35);
		btnAjouter.setText("Ajouter");
		
		//Label demi pensionnaire
		Label lblDemipensionnaire = new Label(Ajout_prof, SWT.NONE);
		lblDemipensionnaire.setText("Demi-pensionnaire :");
		lblDemipensionnaire.setBounds(122, 182, 141, 25);
		
		//label régime alimentaire
		Label lblRgimeAlimentaire = new Label(Ajout_prof, SWT.NONE);
		lblRgimeAlimentaire.setText("R\u00E9gime alimentaire :");
		lblRgimeAlimentaire.setBounds(122, 316, 141, 25);
		
		//texte demi pensionnaire
		textDP = new Text(Ajout_prof, SWT.BORDER);
		textDP.setBounds(292, 176, 131, 31);
		
		//texte jours
		textJours = new Text(Ajout_prof, SWT.BORDER);
		textJours.setBounds(292, 242, 131, 31);
		
		//texte regime
		textRegime = new Text(Ajout_prof, SWT.BORDER);
		textRegime.setBounds(292, 310, 131, 31);
		
		//Bouton pour vider les cases
		Button btnVider = new Button(Ajout_prof, SWT.NONE);
		btnVider.setText("Vider");
		btnVider.setBounds(540, 192, 113, 35);
		
		//Label pour afficher un message d'erreur
		Label lblErreur = new Label(Ajout_prof, SWT.NONE);
		lblErreur.setBounds(540, 321, 256, 20);
		
		//Bouton pour revenir sur la page d'accueil
		Button btnRetour = new Button(Ajout_prof, SWT.NONE);
		btnRetour.setText("Retour");
		btnRetour.setBounds(540, 270, 113, 35);
		
		Label lblQuelsJours = new Label(Ajout_prof, SWT.NONE);
		lblQuelsJours.setText("Quels jours (L,Ma,Me,J,V) :");
		lblQuelsJours.setBounds(88, 242, 175, 25);
		
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
			           //On cherche si le professeur existe déjà
			             ResultSet rs = stm.executeQuery("select * from compte where nom ='"+textNom.getText()+"' and prenom ='"+textPrenom.getText()+"'");

			             if(rs.next()){//Si il existe déjà
			            	 lblErreur.setText("Professeur déjà existant"); //On affiche le message d'erreur
			             }
			             else { //Sinon
			            	 final String separateur = ",";
					         String jours = textJours.getText();
					         
					         String nbjours[] = jours.split(separateur);
				        	 prix_mensuel = nbjours.length * 6 * 4;
			            	//On enregistre les données qu'on a entré dans la bdd
			            	 PreparedStatement stmt = cnx.prepareStatement("INSERT INTO compte(nom, prenom, DP, jours, regime, role, prix_mensuel) VALUES (?, ?, ?, ?, ?, 'prof',?)");
			            	 stmt.setString(1, textNom.getText());
			            	 stmt.setString(2, textPrenom.getText());
			            	 stmt.setString(3, textDP.getText());
			            	 stmt.setString(4, textJours.getText());
			            	 stmt.setString(5, textRegime.getText());
			            	 stmt.setString(7, String.valueOf(prix_mensuel));

			            	 stmt.executeUpdate();
			            	 lblErreur.setText("Professeur ajouté");

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
	            	textDP.setText("");
	            	textJours.setText("");
	            	textRegime.setText("");
			   }
		});
		
		btnRetour.addSelectionListener(new SelectionAdapter() { //Quand on appui sur le bouton retour
			 
			   @Override
			   public void widgetSelected(SelectionEvent arg0) { 
				   Ajout_prof.close(); //On ferme la page
				   Accueil.main(args); //On ouvre la page accueil
			   }
		});


		//Affichage  de la page
		Ajout_prof.open();
		Ajout_prof.layout();
		while (!Ajout_prof.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
		
		

	}
}
