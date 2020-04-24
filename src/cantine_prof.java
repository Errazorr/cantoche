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

public class cantine_prof {

	protected Shell shlCantineprof;
	private Table table;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			cantine_prof window = new cantine_prof();
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
		shlCantineprof.open();
		shlCantineprof.layout();
		while (!shlCantineprof.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shlCantineprof = new Shell();
		shlCantineprof.setSize(956, 737); //Taille de la fenêtre
		shlCantineprof.setText("Cantine professeur"); //Titre de la fenêtre
		
		//Bouton pour ajouter un élève
		Button btnNewButton = new Button(shlCantineprof, SWT.NONE);
		btnNewButton.setBounds(50, 576, 204, 35);
		btnNewButton.setText("Ajouter un professeur");
		
		//Bouton pour modifier un élève
		Button btnNewButton_1 = new Button(shlCantineprof, SWT.NONE);
		btnNewButton_1.setBounds(470, 576, 204, 35);
		btnNewButton_1.setText("Modifier un professeur");
		
		//Bouton pour supprimer un élève
		Button btnNewButton_2 = new Button(shlCantineprof, SWT.NONE);
		btnNewButton_2.setBounds(260, 576, 204, 35);
		btnNewButton_2.setText("Supprimer un professeur");
		
		//Affichage du tableau
		table = new Table(shlCantineprof, SWT.BORDER | SWT.FULL_SELECTION);
		table.setLinesVisible(true);
		table.setHeaderVisible(true);
		table.setBounds(50, 100, 835, 440);
		
		//Colonne nom
		TableColumn tblclmnNom = new TableColumn(table, SWT.NONE);
		tblclmnNom.setWidth(135);
		tblclmnNom.setText("Nom");
		
		//Colonne prenom
		TableColumn tblclmnPrnom = new TableColumn(table, SWT.NONE);
		tblclmnPrnom.setWidth(135);
		tblclmnPrnom.setText("Pr\u00E9nom");
		
		//Colonne cantine
		TableColumn tblclmnCantine = new TableColumn(table, SWT.NONE);
		tblclmnCantine.setWidth(85);
		tblclmnCantine.setText("Cantine");
		
		//Colonne jours
		TableColumn tblclmnJour = new TableColumn(table, SWT.NONE);
		tblclmnJour.setWidth(100);
		tblclmnJour.setText("Jour");
		
		//Colonne régime
		TableColumn tblclmnNewColumn = new TableColumn(table, SWT.NONE);
		tblclmnNewColumn.setText("R\u00E9gime");
		tblclmnNewColumn.setWidth(190);
		
		//Colonne régime alimentaire
		TableColumn tblclmnNewColumn_1 = new TableColumn(table, SWT.NONE);
		tblclmnNewColumn_1.setWidth(182);
		tblclmnNewColumn_1.setText("R\u00E9gime alimentaire");
		
		//Ligne blanche pour laisser un espace
		TableItem tableItem_1 = new TableItem(table, SWT.NONE);
		tableItem_1.setText("");
		
		//Zone pour entrer les données dans le tableau
		TableItem tableItem = new TableItem(table, SWT.NONE);
		tableItem.setText(new String[] {});
		
		//Bouton pour trier par nom
		Button btnTrierParNom = new Button(shlCantineprof, SWT.NONE);
		btnTrierParNom.setBounds(67, 43, 122, 35);
		btnTrierParNom.setText("Trier par nom");
		
		//Label pour afficher le prix total du mois à payer
		Label lblTotalDuMois = new Label(shlCantineprof, SWT.NONE);
		lblTotalDuMois.setBounds(693, 583, 209, 28);
		lblTotalDuMois.setText("Total du mois :");
		
		//Connexion à la bdd
			String url="jdbc:mysql://localhost/cantine?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
		    String user="root";
		    String password="";
		    try {
		         Connection cnx = DriverManager.getConnection(url, user, password);
		         Statement stm = cnx.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
		         stm.executeQuery("SELECT * FROM compte WHERE role = 'eleve'");
		             
		         //On remplit une ligne du tableau
		        TableItem table1 = new TableItem(table, SWT.NONE);
		        table1.setText(0, "Goncalves");
		     	table1.setText(1, "Nathan");
		     	table1.setText(2, "BTS");
		     	table1.setText(3, "Non");
		     	table1.setText(4, "");
		     	table1.setText(5, "");
		     	table1.setText(6, "");
		             

		     } catch (SQLException e) { //Si il y a une erreur de connexion à la bdd
		    	 System.out.println("Une erreur est survenue lors de la connexion à la base de données"); //On affiche le  message
		         e.printStackTrace();
		         
		     };

	}
}
