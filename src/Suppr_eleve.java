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

public class Suppr_eleve {
	
	//Attributs nom et prenom
	private static Text Nom;
	private static Text Prenom;

	
	public static void main(String[] args) {
		//Affichage de la page de connexion
		Display display = Display.getDefault();
		Shell Supprimer = new Shell();
		Supprimer.setSize(600, 400); //Taille de la fenêtre
		Supprimer.setText("Supprimer un \u00E9l\u00E8ve"); //Titre de la fenêtre
		
		//Label nom
		Label lblNom = new Label(Supprimer, SWT.NONE);
		lblNom.setBounds(176, 95, 45, 25);
		lblNom.setText("Nom :");
		
		//Label prenom
		Label lblPrenom = new Label(Supprimer, SWT.NONE);
		lblPrenom.setBounds(157, 152, 64, 25);
		lblPrenom.setText("Pr\u00E9nom :");
		
		//Texte nom
		Nom = new Text(Supprimer, SWT.BORDER);
		Nom.setBounds(247, 92, 131, 31);
		
		//Texte prenom
		Prenom = new Text(Supprimer, SWT.BORDER);
		Prenom.setBounds(247, 149, 131, 31);
		
		//Bouton de validation
		Button btnSupp = new Button(Supprimer, SWT.NONE);
		btnSupp.setBounds(122, 226, 113, 35);
		btnSupp.setText("Supprimer");
		
		//Label d'erreur
		Label lblErreur = new Label(Supprimer, SWT.NONE);
		lblErreur.setAlignment(SWT.CENTER);
		lblErreur.setBounds(122, 33, 301, 20);
		lblErreur.setText("");
		
		//Bouton d'annulation
		Button btnAnnuler = new Button(Supprimer, SWT.NONE);
		btnAnnuler.setText("Annuler");
		btnAnnuler.setBounds(310, 226, 113, 35);
		
		btnSupp.addSelectionListener(new SelectionAdapter() { //Quand on appui sur le bouton supprimer
			 
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

			             if(rs.next()){ //Si il existe
			            	 Statement stmt = cnx.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			            	 stmt.executeUpdate("delete from compte where nom ='"+Nom.getText()+"' and prenom ='"+Prenom.getText()+"' and role='eleve'");
			            	 lblErreur.setText("Eleve supprimé"); //On affiche le texte
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
				   Supprimer.close(); //On ferme la page
				   Accueil.main(args); //On ouvre la page accueil
			   }
		});

		
		//Affichage de la fenêtre
		Supprimer.open();
		Supprimer.layout();
		while (!Supprimer.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
		
		

	}
}
