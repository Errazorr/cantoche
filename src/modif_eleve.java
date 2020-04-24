import java.sql.Connection;
import java.sql.DriverManager;
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

public class modif_eleve {
	
	//Attributs
	private static Text Nom;
	private static Text Prenom;
	private static Text Classe;
	private static Text DP;
	private static Text Jours;
	private static Text Regime;
	private static int prix_mensuel;

	
	public static void main(String[] args) {
		//Affichage de la page de connexion
		Display display = Display.getDefault();
		Shell Modifier = new Shell();
		Modifier.setSize(714, 530); //Taille de la fenêtre
		Modifier.setText("Modifier un \u00E9l\u00E8ve"); //Titre de la fenêtre
		
		//Label nom
		Label lblNom = new Label(Modifier, SWT.NONE);
		lblNom.setBounds(176, 95, 45, 25);
		lblNom.setText("Nom :");
		
		//Label prenom
		Label lblPrenom = new Label(Modifier, SWT.NONE);
		lblPrenom.setBounds(157, 152, 64, 25);
		lblPrenom.setText("Pr\u00E9nom :");
		
		//Texte nom
		Nom = new Text(Modifier, SWT.BORDER);
		Nom.setBounds(247, 92, 131, 31);
		
		//Texte prenom
		Prenom = new Text(Modifier, SWT.BORDER);
		Prenom.setBounds(247, 149, 131, 31);
		
		//Bouton de validation
		Button btnValider = new Button(Modifier, SWT.NONE);
		btnValider.setEnabled(false);
		btnValider.setBounds(487, 203, 113, 35);
		btnValider.setText("Valider");
		
		//Label d'erreur
		Label lblErreur = new Label(Modifier, SWT.NONE);
		lblErreur.setAlignment(SWT.CENTER);
		lblErreur.setBounds(122, 33, 301, 20);
		lblErreur.setText("");
		
		//Bouton d'annulation
		Button btnAnnuler = new Button(Modifier, SWT.NONE);
		btnAnnuler.setText("Annuler");
		btnAnnuler.setBounds(487, 263, 113, 35);
		
		//Texte classe
		Classe = new Text(Modifier, SWT.BORDER);
		Classe.setEnabled(false);
		Classe.setBounds(247, 207, 131, 31);
		
		//Texte dp
		DP = new Text(Modifier, SWT.BORDER);
		DP.setEnabled(false);
		DP.setBounds(247, 264, 131, 31);
		
		//Texte jours
		Jours = new Text(Modifier, SWT.BORDER);
		Jours.setEnabled(false);
		Jours.setBounds(247, 324, 131, 31);
		
		//Texte regime
		Regime = new Text(Modifier, SWT.BORDER);
		Regime.setEnabled(false);
		Regime.setBounds(247, 384, 131, 31);
		
		//Label classe
		Label lblClasse = new Label(Modifier, SWT.NONE);
		lblClasse.setText("Classe :");
		lblClasse.setBounds(166, 213, 55, 25);
		
		//Label dp
		Label lblDP = new Label(Modifier, SWT.NONE);
		lblDP.setText("Demi-pensionnaire :");
		lblDP.setBounds(78, 270, 143, 25);
		
		//Label jours
		Label lblJours = new Label(Modifier, SWT.NONE);
		lblJours.setText("Jours :");
		lblJours.setBounds(157, 330, 64, 25);
		
		//Label regime
		Label lblRgimeAlimentaire = new Label(Modifier, SWT.NONE);
		lblRgimeAlimentaire.setText("R\u00E9gime alimentaire :");
		lblRgimeAlimentaire.setBounds(78, 390, 143, 25);
		
		//Bouton pour modifier (choisir l'élève à modifier)
		Button btnModif = new Button(Modifier, SWT.NONE);
		btnModif.setText("Modifier");
		btnModif.setBounds(487, 142, 113, 35);
		
		btnModif.addSelectionListener(new SelectionAdapter() { //Quand on appui sur le bouton modifier
			 
			   @Override
			   public void widgetSelected(SelectionEvent arg0) {
				   
				   //Connexion à la bdd
				   	String url="jdbc:mysql://localhost/cantine?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
			        String user="root";
			        String password="";
			        try {
			             Connection cnx = DriverManager.getConnection(url, user, password);
			             Statement stm = cnx.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			             //On cherche si le compte existe grâce au nom et au prénom
			             ResultSet rs = stm.executeQuery("select * from compte where nom ='"+Nom.getText()+"' and prenom ='"+Prenom.getText()+"' and role='eleve'");

			             if(rs.next()){ //Si il existe, on rend les cases et le bouton accessibles
			            	 Classe.setEnabled(true);
			            	 DP.setEnabled(true);
			            	 Jours.setEnabled(true);
			            	 Regime.setEnabled(true);
			            	 btnValider.setEnabled(true);
			            	 
			            	 btnValider.addSelectionListener(new SelectionAdapter() { //Quand on appui sur valider
			        			 
			      			   @Override
			      			   public void widgetSelected(SelectionEvent arg0) {
			      				 try {
			      					final String separateur = ",";
							         String jours = Jours.getText();
							         
							         String nbjours[] = jours.split(separateur);
						        	 prix_mensuel = nbjours.length * 6 * 4;
						        	 
			      					 //Connexion à la bdd
						             Connection cnx = DriverManager.getConnection(url, user, password);
						             Statement stmt = cnx.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
						             //On modifie les données de l'élèves avec les données rentrées
			      			   	 	stmt.executeUpdate("update compte set classe  = '"+Classe.getText()+"', DP  = '"+DP.getText()+"', jours  = '"+Jours.getText()+"', regime  = '"+Regime.getText()+"', prix_mensuel = '"+String.valueOf(prix_mensuel)+"' where nom ='"+Nom.getText()+"' and prenom ='"+Prenom.getText()+"' and role='eleve'");
			      			   	 	lblErreur.setText("Eleve modifié"); //On affiche le texte
			      			   	 	btnAnnuler.setText("Retour");//On change le texte du bouton annuler
			      				 }
			      				catch (SQLException e) { //S'il y a un problème lors de la connexion à la bdd
						             System.out.println("Une erreur est survenue lors de la connexion à la base de données"); //On affiche le texte
						             e.printStackTrace();
						         
						};  
			      				   
			      			   }
			      		});
			            	 
			             }
			             else { //Sinon
			                 lblErreur.setText("Eleve introuvable ou inexistant"); //On affiche le texte
			             }
			             
			         } catch (SQLException e) { //S'il y a un problème lors de la connexion à la bdd
			             System.out.println("Une erreur est survenue lors de la connexion à la base de données"); //On affiche le texte
			             e.printStackTrace();
			         
			};  
			   }
		});
		
		btnAnnuler.addSelectionListener(new SelectionAdapter() { //Quand on appui sur le bouton vider
			 
			   @Override
			   public void widgetSelected(SelectionEvent arg0) { //On vide toutes les zones de texte
				   Modifier.close(); //On ferme la page
				   Accueil.main(args); //On ouvre la page accueil
			   }
		});

		
		//Affichage de la fenêtre
		Modifier.open();
		Modifier.layout();
		while (!Modifier.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
		
		
		
	}
}
