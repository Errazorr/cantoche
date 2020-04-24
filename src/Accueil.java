import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import org.eclipse.swt.widgets.Button;

public class Accueil {

	
	public static void main(String[] args) {
		//Affichage de la page d'accueil
		Display display = Display.getDefault();
		Shell Accueil = new Shell();
		Accueil.setSize(695, 436); //Taille de la fenêtre
		Accueil.setText("Accueil"); //Titre de la fenêtre
		
		//Bouton pour ajouter un élève
		Button btnAjout = new Button(Accueil, SWT.NONE);
		btnAjout.setText("Ajouter un \u00E9tudiant");
		btnAjout.setBounds(44, 61, 204, 35);
		
		//Bouton pour supprimer un élève
		Button btnSuppr = new Button(Accueil, SWT.NONE);
		btnSuppr.setText("Supprimer un \u00E9tudiant");
		btnSuppr.setBounds(44, 135, 204, 35);
		
		//Bouton pour modifier un élève
		Button btnModif = new Button(Accueil, SWT.NONE);
		btnModif.setText("Modifier un \u00E9tudiant");
		btnModif.setBounds(44, 207, 204, 35);
		
		//Bouton pour afficher la liste des élèves
		Button btnAfficherLesetudiants = new Button(Accueil, SWT.NONE);
		btnAfficherLesetudiants.setText("Afficher les \u00E9tudiants");
		btnAfficherLesetudiants.setBounds(44, 277, 204, 35);
		
		//Bouton pour ajouter un professeur
		Button btnAjouterUnProf = new Button(Accueil, SWT.NONE);
		btnAjouterUnProf.setText("Ajouter un prof");
		btnAjouterUnProf.setBounds(429, 61, 204, 35);
		
		//Bouton pour supprimer un professeur
		Button btnSupprimerUnProf = new Button(Accueil, SWT.NONE);
		btnSupprimerUnProf.setText("Supprimer un prof");
		btnSupprimerUnProf.setBounds(429, 135, 204, 35);
		
		//Bouton pour modifier un professeur
		Button btnModifierUnProf = new Button(Accueil, SWT.NONE);
		btnModifierUnProf.setText("Modifier un prof");
		btnModifierUnProf.setBounds(429, 207, 204, 35);
		
		//Bouton pour afficher la liste des professeurs
		Button btnAfficherLesProfs = new Button(Accueil, SWT.NONE);
		btnAfficherLesProfs.setText("Afficher les profs");
		btnAfficherLesProfs.setBounds(429, 277, 204, 35);
		
		btnAjout.addSelectionListener(new SelectionAdapter() {
			 
			   @Override
			   public void widgetSelected(SelectionEvent arg0) {
				   Accueil.close(); //On ferme la page d'accueil
				   Ajout_eleve.main(args); //On ouvre la page pour ajouter un élève
				   
			   }
		});
		
		btnAjouterUnProf.addSelectionListener(new SelectionAdapter() {
			 
			   @Override
			   public void widgetSelected(SelectionEvent arg0) {
				   Accueil.close(); //On ferme la page d'accueil
				   Ajout_prof.main(args); //On ouvre la page pour ajouter un professeur
				   
			   }
		});
		
		btnAfficherLesetudiants.addSelectionListener(new SelectionAdapter() {
			 
			   @Override
			   public void widgetSelected(SelectionEvent arg0) {
				   Accueil.close(); //On ferme la page d'accueil
				   cantine_etudiant.main(args); //On ouvre la page pour afficher les élèves
				   
			   }
		});
		
		btnAfficherLesProfs.addSelectionListener(new SelectionAdapter() {
			 
			   @Override
			   public void widgetSelected(SelectionEvent arg0) {
				   Accueil.close(); //On ferme la page d'accueil
				   cantine_prof.main(args); //On ouvre la page pour afficher les professeurs
				   
			   }
		});
		
		btnSuppr.addSelectionListener(new SelectionAdapter() {
			 
			   @Override
			   public void widgetSelected(SelectionEvent arg0) {
				   Accueil.close(); //On ferme la page d'accueil
				   Suppr_eleve.main(args); //On ouvre la page pour supprimer les professeurs
				   
			   }
		});
		
		btnSupprimerUnProf.addSelectionListener(new SelectionAdapter() {
			 
			   @Override
			   public void widgetSelected(SelectionEvent arg0) {
				   Accueil.close(); //On ferme la page d'accueil
				   Suppr_prof.main(args); //On ouvre la page pour supprimer les professeurs
				   
			   }
		});
		
		btnModif.addSelectionListener(new SelectionAdapter() {
			 
			   @Override
			   public void widgetSelected(SelectionEvent arg0) {
				   Accueil.close(); //On ferme la page d'accueil
				   modif_eleve.main(args); //On ouvre la page pour modifier les professeurs
				   
			   }
		});
		
		btnModifierUnProf.addSelectionListener(new SelectionAdapter() {
			 
			   @Override
			   public void widgetSelected(SelectionEvent arg0) {
				   Accueil.close(); //On ferme la page d'accueil
				   modif_prof.main(args); //On ouvre la page pour modifier les professeurs
				   
			   }
		});

		//Affichage de la fenêtre
		Accueil.setVisible(true);
		Accueil.open();
		Accueil.layout();
		while (!Accueil.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
		
		

	}
}
