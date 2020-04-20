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

public class Suppr_prof {
	
	//Attributs nom et prenom
	private static Text Nom;
	private static Text Prenom;

	
	public static void main(String[] args) {
		//Affichage de la page de connexion
		Display display = Display.getDefault();
		Shell Supprimer = new Shell();
		Supprimer.setSize(600, 400); //Taille de la fenêtre
		Supprimer.setText("Supprimer un professeur"); //Titre de la fenêtre
		
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
		Button btnCo = new Button(Supprimer, SWT.NONE);
		btnCo.setBounds(122, 226, 113, 35);
		btnCo.setText("Supprimer");
		
		//Label d'erreur
		Label lblErreur = new Label(Supprimer, SWT.NONE);
		lblErreur.setAlignment(SWT.CENTER);
		lblErreur.setBounds(122, 33, 301, 20);
		lblErreur.setText("");
		
		//Bouton d'annulation
		Button btnAnnuler = new Button(Supprimer, SWT.NONE);
		btnAnnuler.setText("Annuler");
		btnAnnuler.setBounds(310, 226, 113, 35);
		
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
