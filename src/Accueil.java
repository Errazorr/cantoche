import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import org.eclipse.swt.widgets.Button;

public class Accueil {

	
	public static void main(String[] args) {
		Display display = Display.getDefault();
		Shell Accueil = new Shell();
		Accueil.setSize(695, 436);
		Accueil.setText("Accueil");
		
		Button btnAjout = new Button(Accueil, SWT.NONE);
		btnAjout.setText("Ajouter un \u00E9tudiant");
		btnAjout.setBounds(44, 61, 204, 35);
		
		Button btnSuppr = new Button(Accueil, SWT.NONE);
		btnSuppr.setText("Supprimer un \u00E9tudiant");
		btnSuppr.setBounds(44, 135, 204, 35);
		
		Button btnModif = new Button(Accueil, SWT.NONE);
		btnModif.setText("Modifier un \u00E9tudiant");
		btnModif.setBounds(44, 207, 204, 35);
		
		Button btnAfficherLesetudiants = new Button(Accueil, SWT.NONE);
		btnAfficherLesetudiants.setText("Afficher les \u00E9tudiants");
		btnAfficherLesetudiants.setBounds(44, 277, 204, 35);
		
		Button btnAjouterUnProf = new Button(Accueil, SWT.NONE);
		btnAjouterUnProf.setText("Ajouter un prof");
		btnAjouterUnProf.setBounds(429, 61, 204, 35);
		
		Button btnSupprimerUnProf = new Button(Accueil, SWT.NONE);
		btnSupprimerUnProf.setText("Supprimer un prof");
		btnSupprimerUnProf.setBounds(429, 135, 204, 35);
		
		Button btnModifierUnProf = new Button(Accueil, SWT.NONE);
		btnModifierUnProf.setText("Modifier un prof");
		btnModifierUnProf.setBounds(429, 207, 204, 35);
		
		Button btnAfficherLesProfs = new Button(Accueil, SWT.NONE);
		btnAfficherLesProfs.setText("Afficher les profs");
		btnAfficherLesProfs.setBounds(429, 277, 204, 35);
		
		btnAjout.addSelectionListener(new SelectionAdapter() {
			 
			   @Override
			   public void widgetSelected(SelectionEvent arg0) {
				   Accueil.close();
				   Ajout_eleve.main(args);
				   
			   }
		});
		
		btnAjouterUnProf.addSelectionListener(new SelectionAdapter() {
			 
			   @Override
			   public void widgetSelected(SelectionEvent arg0) {
				   Accueil.close();
				   Ajout_prof.main(args);
				   
			   }
		});
		
		btnAfficherLesetudiants.addSelectionListener(new SelectionAdapter() {
			 
			   @Override
			   public void widgetSelected(SelectionEvent arg0) {
				   Accueil.close();
				   cantine_etudiant.main(args);
				   
			   }
		});
		
		btnAfficherLesProfs.addSelectionListener(new SelectionAdapter() {
			 
			   @Override
			   public void widgetSelected(SelectionEvent arg0) {
				   Accueil.close();
				   cantine_prof.main(args);
				   
			   }
		});


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
