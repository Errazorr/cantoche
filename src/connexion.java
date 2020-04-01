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

public class connexion {
	private static Text textId;
	private static Text textMdp;

	
	public static void main(String[] args) {
		Display display = Display.getDefault();
		Shell fenetre_co = new Shell();
		fenetre_co.setSize(600, 400);
		fenetre_co.setText("Connexion");
		
		Label lblId = new Label(fenetre_co, SWT.NONE);
		lblId.setBounds(122, 95, 96, 25);
		lblId.setText("Identifiant :");
		
		Label lblMdp = new Label(fenetre_co, SWT.NONE);
		lblMdp.setBounds(122, 152, 123, 25);
		lblMdp.setText("Mot de passe :");
		
		textId = new Text(fenetre_co, SWT.BORDER);
		textId.setBounds(292, 92, 131, 31);
		
		textMdp = new Text(fenetre_co, SWT.BORDER);
		textMdp.setBounds(292, 149, 131, 31);
		
		Button btnCo = new Button(fenetre_co, SWT.NONE);
		btnCo.setBounds(122, 226, 113, 35);
		btnCo.setText("Se connecter");
		
		Label lblErreur = new Label(fenetre_co, SWT.NONE);
		lblErreur.setAlignment(SWT.CENTER);
		lblErreur.setBounds(122, 33, 301, 20);
		lblErreur.setText("");
		
		btnCo.addSelectionListener(new SelectionAdapter() {
			 
			   @Override
			   public void widgetSelected(SelectionEvent arg0) {
				   
				   String url="jdbc:mysql://localhost/cantine?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
			        String user="root";
			        String password="";
			        try {
			             Connection cnx = DriverManager.getConnection(url, user, password);
			             Statement stm = cnx.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			             ResultSet rs = stm.executeQuery("select * from compte where identifiant ='"+textId.getText()+"' and mdp ='"+textMdp.getText()+"'");

			             if(rs.next()){
			                 Accueil fen2 = new Accueil();
			                 fenetre_co.close();
			                 fen2.open();
			             }
			             else {
			                 lblErreur.setText("Identifiant ou mot de passe incorrect");
			             }
			         } catch (SQLException e) {
			             System.out.println("Une erreur est survenue lors de la connexion à la base de données");
			             e.printStackTrace();
			         
			};  
			   }
		});

		fenetre_co.open();
		fenetre_co.layout();
		while (!fenetre_co.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
		
		

	}
}
