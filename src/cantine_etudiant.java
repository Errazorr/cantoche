import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Button;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Label;

public class cantine_etudiant {

	protected Shell shlCantinetudiant;
	private Table table;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			cantine_etudiant window = new cantine_etudiant();
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
		shlCantinetudiant.open();
		shlCantinetudiant.layout();
		while (!shlCantinetudiant.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shlCantinetudiant = new Shell();
		shlCantinetudiant.setSize(956, 737); //Taille de la fenêtre
		shlCantinetudiant.setText("Cantine \u00E9tudiant"); //Titre de la fenêtre
		
		//Bouton pour ajouter un élève
		Button btnAjout = new Button(shlCantinetudiant, SWT.NONE);
		btnAjout.setBounds(50, 576, 204, 35);
		btnAjout.setText("Ajouter un \u00E9tudiant");
		
		//Bouton pour modifier un élève
		Button btnModif = new Button(shlCantinetudiant, SWT.NONE);
		btnModif.setBounds(470, 576, 204, 35);
		btnModif.setText("Modifier un \u00E9tudiant");
		
		//Bouton pour supprimer un élève
		Button btnSuppr = new Button(shlCantinetudiant, SWT.NONE);
		btnSuppr.setBounds(260, 576, 204, 35);
		btnSuppr.setText("Supprimer un \u00E9tudiant");
		
		//Affichage du tableau
		table = new Table(shlCantinetudiant, SWT.BORDER | SWT.FULL_SELECTION);
		table.setLinesVisible(true);
		table.setHeaderVisible(true);
		table.setBounds(50, 100, 781, 440);
		
		//Colonne nom
		TableColumn tblclmnNom = new TableColumn(table, SWT.CENTER);
		tblclmnNom.setResizable(false);
		tblclmnNom.setWidth(100);
		tblclmnNom.setText("Nom");
		
		//Colonne prenom
		TableColumn tblclmnPrnom = new TableColumn(table, SWT.NONE);
		tblclmnPrnom.setWidth(100);
		tblclmnPrnom.setText("Pr\u00E9nom");
		
		//Colonne classe
		TableColumn tblclmnClasse = new TableColumn(table, SWT.NONE);
		tblclmnClasse.setWidth(100);
		tblclmnClasse.setText("Classe");
		
		//Colonne cantine
		TableColumn tblclmnCantine = new TableColumn(table, SWT.NONE);
		tblclmnCantine.setWidth(82);
		tblclmnCantine.setText("Cantine");
		
		//Colonne jours
		TableColumn tblclmnJour = new TableColumn(table, SWT.NONE);
		tblclmnJour.setWidth(100);
		tblclmnJour.setText("Jour");
		
		//Colonne régime
		TableColumn tblclmnRégime = new TableColumn(table, SWT.NONE);
		tblclmnRégime.setText("R\u00E9gime");
		tblclmnRégime.setWidth(135);
		
		//Colonne régime alimentaire
		TableColumn tblclmnRégimeAlimentaire = new TableColumn(table, SWT.NONE);
		tblclmnRégimeAlimentaire.setWidth(160);
		tblclmnRégimeAlimentaire.setText("R\u00E9gime alimentaire");
		
		//Ligne blanche pour laisser un espace
		TableItem tableItem = new TableItem(table, SWT.NONE);
		tableItem.setText("");
		
		//Bouton pour trier par nom
		Button btnTrinom = new Button(shlCantinetudiant, SWT.NONE);
		btnTrinom.setBounds(67, 43, 122, 35);
		btnTrinom.setText("Trier par nom");
		
		//Bouton pour trier par classe
		Button btnTriclasse = new Button(shlCantinetudiant, SWT.NONE);
		btnTriclasse.setBounds(267, 43, 148, 35);
		btnTriclasse.setText("Trier par classe");
		
		//Label pour afficher le prix total du mois à payer
		Label lblTotal = new Label(shlCantinetudiant, SWT.NONE);
		lblTotal.setBounds(693, 583, 209, 28);
		lblTotal.setText("Total du mois :");
		
		//Connexion à la bdd
		String url="jdbc:mysql://localhost/cantine?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
        String user="root";
        String password="";
        try {
             Connection cnx = DriverManager.getConnection(url, user, password);
             Statement stm = cnx.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
             ResultSet rs = stm.executeQuery("SELECT * FROM compte WHERE role = 'eleve'"); //On récupère les données où le role est "eleve"
             
             //On remplit une ligne du tableau
            TableItem tableItem_1 = new TableItem(table, SWT.NONE);
     		tableItem_1.setText(0, "Goncalves");
     		tableItem_1.setText(1, "Nathan");
     		tableItem_1.setText(2, "BTS");
     		tableItem_1.setText(3, "Non");
     		tableItem_1.setText(4, "");
     		tableItem_1.setText(5, "");
     		tableItem_1.setText(6, "");
             

         } catch (SQLException e) { //Si il y a une erreur de connexion à la bdd
             System.out.println("Une erreur est survenue lors de la connexion à la base de données"); //On affiche le  message
             e.printStackTrace();
         
         }; 

	}
}
