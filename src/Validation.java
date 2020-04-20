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
import org.eclipse.wb.swt.SWTResourceManager;

public class Validation{

	
	public static void main(String[] args) {
		//Affichage de la page de connexion
		Display display = Display.getDefault();
		Shell Valider = new Shell();
		Valider.setSize(600, 400); //Taille de la fenêtre
		Valider.setText("ATTENTION");
		
		//Bouton pour annuler
		Button non = new Button(Valider, SWT.NONE);
		non.setBounds(136, 226, 113, 35);
		non.setText("Non");
		
		//Label question
		Label lblErreur = new Label(Valider, SWT.NONE);
		lblErreur.setFont(SWTResourceManager.getFont("Segoe UI", 20, SWT.NORMAL));
		lblErreur.setAlignment(SWT.CENTER);
		lblErreur.setBounds(173, 94, 220, 45);
		lblErreur.setText("Etes vous s\u00FBr?");
		
		//Bouton pour valider
		Button oui = new Button(Valider, SWT.NONE);
		oui.setText("Oui");
		oui.setBounds(328, 226, 113, 35);
		
		//Label de prévention
		Label lblWarning = new Label(Valider, SWT.NONE);
		lblWarning.setAlignment(SWT.CENTER);
		lblWarning.setBounds(173, 168, 220, 20);
		lblWarning.setText("Cette action est irr\u00E9versible");
		
		//Affichage de la fenêtre
		Valider.open();
		Valider.layout();
		while (!Valider.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
		
		

	}
}
