import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import org.eclipse.swt.widgets.Button;

public class connexion {
	private static Text textId;
	private static Text textMdp;

	/**
	 * Launch the application.
	 * @param args
	 */
	
	public static void main(String[] args) {
		Display display = Display.getDefault();
		Shell shlAzJunior = new Shell();
		shlAzJunior.setSize(600, 400);
		shlAzJunior.setText("Connexion");
		
		Label lblId = new Label(shlAzJunior, SWT.NONE);
		lblId.setBounds(122, 95, 96, 25);
		lblId.setText("Identifiant :");
		
		Label lblMdp = new Label(shlAzJunior, SWT.NONE);
		lblMdp.setBounds(122, 152, 123, 25);
		lblMdp.setText("Mot de passe :");
		
		textId = new Text(shlAzJunior, SWT.BORDER);
		textId.setBounds(292, 92, 131, 31);
		
		textMdp = new Text(shlAzJunior, SWT.BORDER);
		textMdp.setBounds(292, 149, 131, 31);
		
		Button btnCo = new Button(shlAzJunior, SWT.NONE);
		btnCo.setBounds(122, 226, 113, 35);
		btnCo.setText("Se connecter");
		
		btnCo.addSelectionListener(new SelectionAdapter() {
			 
			   @Override
			   public void widgetSelected(SelectionEvent arg0) {
				   
				   Accueil fen2 = new Accueil();
				   shlAzJunior.close();
				   fen2.open();  
			   }
		});

		shlAzJunior.open();
		shlAzJunior.layout();
		while (!shlAzJunior.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
		
		

	}
}
