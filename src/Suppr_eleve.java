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
	
	//Attributs Identifiant et Mot de passe
	private static Text textNom;
	private static Text textPrenom;

	
	public static void main(String[] args) {
		//Affichage de la page de connexion
		Display display = Display.getDefault();
		Shell Supprimer = new Shell();
		Supprimer.setSize(600, 400); //Taille de la fenêtre
		Supprimer.setText("Supprimer un \u00E9l\u00E8ve"); //Titre de la fenêtre
		
		//Label identifiant
		Label lblNom = new Label(Supprimer, SWT.NONE);
		lblNom.setBounds(176, 95, 45, 25);
		lblNom.setText("Nom :");
		
		//Label Mot de passe
		Label lblPrenom = new Label(Supprimer, SWT.NONE);
		lblPrenom.setBounds(157, 152, 64, 25);
		lblPrenom.setText("Pr\u00E9nom :");
		
		//Texte identifiant
		textNom = new Text(Supprimer, SWT.BORDER);
		textNom.setBounds(247, 92, 131, 31);
		
		//Texte mot de passe
		textPrenom = new Text(Supprimer, SWT.BORDER);
		textPrenom.setBounds(247, 149, 131, 31);
		
		//Bouton de validation
		Button btnCo = new Button(Supprimer, SWT.NONE);
		btnCo.setBounds(122, 226, 113, 35);
		btnCo.setText("Supprimer");
		
		//Label au cas où l'identifiant ou le mot de passe sont incorrects
		Label lblErreur = new Label(Supprimer, SWT.NONE);
		lblErreur.setAlignment(SWT.CENTER);
		lblErreur.setBounds(122, 33, 301, 20);
		lblErreur.setText("");
		
		Button btnAnnuler = new Button(Supprimer, SWT.NONE);
		btnAnnuler.setText("Annuler");
		btnAnnuler.setBounds(310, 226, 113, 35);
		
		btnCo.addSelectionListener(new SelectionAdapter() {
			 
			   @Override
			   public void widgetSelected(SelectionEvent arg0) {
				   
				   //Connexion à la bdd
				   	String url="jdbc:mysql://localhost/cantine?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
			        String user="root";
			        String password="";
			        try {
			             Connection cnx = DriverManager.getConnection(url, user, password);
			             Statement stm = cnx.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			             //On cherche si le compte existe grâce à l'identifiant et le mot de passe
			             ResultSet rs = stm.executeQuery("select * from compte where identifiant ='"+textNom.getText()+"' and mdp ='"+textPrenom.getText()+"'");

			             if(rs.next()){ //Si il existe
			                 Supprimer.close(); //On ferme la fenêtre de connexion
			                 Accueil.main(args); //On ouvre la page d'accueil
			                 
			             }
			             else { //Sinon
			                 lblErreur.setText("Identifiant ou mot de passe incorrect"); //On affiche le texte
			             }
			             
			         } catch (SQLException e) { //S'il y a un problème lors de la connexion à la bdd
			             System.out.println("Une erreur est survenue lors de la connexion à la base de données"); //On affiche le texte
			             e.printStackTrace();
			         
			};  
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
