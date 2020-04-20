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
	
	//Attributs Identifiant et Mot de passe
	private static Text textId;
	private static Text textMdp;

	
	public static void main(String[] args) {
		//Affichage de la page de connexion
		Display display = Display.getDefault();
		Shell fenetre_co = new Shell();
		fenetre_co.setSize(600, 400); //Taille de la fenêtre
		fenetre_co.setText("Connexion"); //Titre de la fenêtre
		
		//Label identifiant
		Label lblId = new Label(fenetre_co, SWT.NONE);
		lblId.setBounds(122, 95, 96, 25);
		lblId.setText("Identifiant :");
		
		//Label Mot de passe
		Label lblMdp = new Label(fenetre_co, SWT.NONE);
		lblMdp.setBounds(122, 152, 123, 25);
		lblMdp.setText("Mot de passe :");
		
		//Texte identifiant
		textId = new Text(fenetre_co, SWT.BORDER);
		textId.setBounds(292, 92, 131, 31);
		
		//Texte mot de passe
		textMdp = new Text(fenetre_co, SWT.BORDER);
		textMdp.setBounds(292, 149, 131, 31);
		
		//Bouton de validation
		Button btnCo = new Button(fenetre_co, SWT.NONE);
		btnCo.setBounds(122, 226, 113, 35);
		btnCo.setText("Se connecter");
		
		//Label au cas où l'identifiant ou le mot de passe sont incorrects
		Label lblErreur = new Label(fenetre_co, SWT.NONE);
		lblErreur.setAlignment(SWT.CENTER);
		lblErreur.setBounds(122, 33, 301, 20);
		lblErreur.setText("");
		
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
			             ResultSet rs = stm.executeQuery("select * from compte where identifiant ='"+textId.getText()+"' and mdp ='"+textMdp.getText()+"'");

			             if(rs.next()){ //Si il existe
			                 fenetre_co.close(); //On ferme la fenêtre de connexion
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
		fenetre_co.open();
		fenetre_co.layout();
		while (!fenetre_co.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
		
		

	}
}
