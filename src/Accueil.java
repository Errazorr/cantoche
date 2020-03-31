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
		shlAccueil.setSize(449, 445);
		shlAccueil.setText("Accueil");
		
		Button btnAjout = new Button(shlAccueil, SWT.NONE);
		btnAjout.setBounds(108, 87, 204, 35);
		btnAjout.setText("Ajouter un \u00E9tudiant");
		
		Button btnSuppr = new Button(shlAccueil, SWT.NONE);
		btnSuppr.setBounds(108, 161, 204, 35);
		btnSuppr.setText("Supprimer un \u00E9tudiant");
		
		Button btnModif = new Button(shlAccueil, SWT.NONE);
		btnModif.setBounds(108, 233, 204, 35);
		btnModif.setText("Modifier un \u00E9tudiant");

	}
}
