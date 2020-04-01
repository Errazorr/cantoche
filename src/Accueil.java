import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.SWT;

public class Accueil {

	protected Shell shlAccueil;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			Accueil window = new Accueil();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
		shlAccueil.open();
		shlAccueil.layout();
		while (!shlAccueil.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shlAccueil = new Shell();
		shlAccueil.setSize(777, 445);
		shlAccueil.setText("Accueil");
		
		Button btnAjout = new Button(shlAccueil, SWT.NONE);
		btnAjout.setBounds(53, 61, 204, 35);
		btnAjout.setText("Ajouter un \u00E9tudiant");
		
		Button btnSuppr = new Button(shlAccueil, SWT.NONE);
		btnSuppr.setBounds(53, 135, 204, 35);
		btnSuppr.setText("Supprimer un \u00E9tudiant");
		
		Button btnModif = new Button(shlAccueil, SWT.NONE);
		btnModif.setBounds(53, 207, 204, 35);
		btnModif.setText("Modifier un \u00E9tudiant");
		
		Button btnAfficherLestudiants = new Button(shlAccueil, SWT.NONE);
		btnAfficherLestudiants.setText("Afficher les \u00E9tudiants");
		btnAfficherLestudiants.setBounds(53, 277, 204, 35);
		
		Button btnAjouterUnProf = new Button(shlAccueil, SWT.NONE);
		btnAjouterUnProf.setText("Ajouter un prof");
		btnAjouterUnProf.setBounds(438, 61, 204, 35);
		
		Button btnSupprimerUnProf = new Button(shlAccueil, SWT.NONE);
		btnSupprimerUnProf.setText("Supprimer un prof");
		btnSupprimerUnProf.setBounds(438, 135, 204, 35);
		
		Button btnModifierUnProf = new Button(shlAccueil, SWT.NONE);
		btnModifierUnProf.setText("Modifier un prof");
		btnModifierUnProf.setBounds(438, 207, 204, 35);
		
		Button btnAfficherLesProfs = new Button(shlAccueil, SWT.NONE);
		btnAfficherLesProfs.setText("Afficher les profs");
		btnAfficherLesProfs.setBounds(438, 277, 204, 35);

	}
}
