import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Button;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Label;

public class cantine_prof {

	protected Shell shlCantineprof;
	private Table table;
	private int prix_mensuel;

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
		Button btnAjout = new Button(shlCantineprof, SWT.NONE);
		btnAjout.setBounds(50, 576, 204, 35);
		btnAjout.setText("Ajouter un professeur");
		
		//Bouton pour modifier un élève
		Button btnModif = new Button(shlCantineprof, SWT.NONE);
		btnModif.setBounds(470, 576, 204, 35);
		btnModif.setText("Modifier un professeur");
		
		//Bouton pour supprimer un élève
		Button btnSuppr = new Button(shlCantineprof, SWT.NONE);
		btnSuppr.setBounds(260, 576, 204, 35);
		btnSuppr.setText("Supprimer un professeur");
		
		//Affichage du tableau
		table = new Table(shlCantineprof, SWT.BORDER | SWT.FULL_SELECTION);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		table.setBounds(50, 100, 750, 440);
		
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
		tblclmnNewColumn.setText("R\u00E9gime alimentaire");
		tblclmnNewColumn.setWidth(190);
		
		TableColumn tblclmnPrixParMois = new TableColumn(table, SWT.NONE);
		tblclmnPrixParMois.setWidth(100);
		tblclmnPrixParMois.setText("Prix par mois");
		
		//Bouton pour trier par nom
		Button btnTrinom = new Button(shlCantineprof, SWT.NONE);
		btnTrinom.setBounds(67, 43, 122, 35);
		btnTrinom.setText("Trier par nom");
		
		//Label pour afficher le prix total du mois à payer
		Label lblTotal = new Label(shlCantineprof, SWT.NONE);
		lblTotal.setBounds(693, 583, 103, 28);
		lblTotal.setText("Total du mois :");
		
		Button btnRetour = new Button(shlCantineprof, SWT.NONE);
		btnRetour.setText("Retour");
		btnRetour.setBounds(284, 43, 148, 35);
		
		Label lblTotalMois = new Label(shlCantineprof, SWT.NONE);
		lblTotalMois.setBounds(802, 583, 103, 28);
		
		//Connexion à la bdd
			String url="jdbc:mysql://localhost/cantine?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
		    String user="root";
		    String password="";
		    try {
		         Connection cnx = DriverManager.getConnection(url, user, password);
		         Statement stm = cnx.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
		         ResultSet result = stm.executeQuery("SELECT * FROM compte WHERE role = 'prof'");
		         
		         
		             
		         while(result.next()) {
		        	 
			         if(result.getString(6).equals("non")) {
			        	 prix_mensuel = 0;
			         }
			         
			         else {
			        	 final String separateur = ",";
				         String jours = result.getString(7);
				         
				         String nbjours[] = jours.split(separateur);
			        	 prix_mensuel = nbjours.length * 6 * 4;
			         }
			         
		            	//On remplit une ligne du tableau
		            	TableItem tableItem1 = new TableItem(table, SWT.NONE);
		            	tableItem1.setText(0, result.getString(2));
		            	tableItem1.setText(1, result.getString(3));
		            	tableItem1.setText(2, result.getString(6));
		            	tableItem1.setText(3, result.getString(7));
		            	tableItem1.setText(4, result.getString(8));
		            	tableItem1.setText(5, String.valueOf(prix_mensuel));
		            	
		            }
		             
		         ResultSet prix = stm.executeQuery("SELECT SUM(prix_mensuel) FROM compte WHERE role = 'prof'");
		         prix.next();
		         lblTotalMois.setText(prix.getString(1)+" €");


		     } catch (SQLException e) { //Si il y a une erreur de connexion à la bdd
		    	 System.out.println("Une erreur est survenue lors de la connexion à la base de données"); //On affiche le  message
		         e.printStackTrace();
		         
		     };
		
		     btnAjout.addSelectionListener(new SelectionAdapter() { //Quand on appui sur le bouton retour
				 
				   @Override
				   public void widgetSelected(SelectionEvent arg0) { 
					   shlCantineprof.close(); //On ferme la fenetre
					   Ajout_prof.main(null); //On ouvre la fenetre pour ajouter un professeur
				   }
			}); 
	         
	         btnModif.addSelectionListener(new SelectionAdapter() { //Quand on appui sur le bouton retour
				 
				   @Override
				   public void widgetSelected(SelectionEvent arg0) { 
					   shlCantineprof.close(); //On ferme la fenetre
					   modif_prof.main(null); //On ouvre la fenetre pour modifier un professeur
				   }
			}); 
	         
	         btnSuppr.addSelectionListener(new SelectionAdapter() { //Quand on appui sur le bouton retour
				 
				   @Override
				   public void widgetSelected(SelectionEvent arg0) { 
					   shlCantineprof.close(); //On ferme la fenetre
					   Suppr_prof.main(null); //On ouvre la fenetre pour supprimer un professeur
				   }
			}); 
	         
	         btnRetour.addSelectionListener(new SelectionAdapter() { //Quand on appui sur le bouton retour
				 
				   @Override
				   public void widgetSelected(SelectionEvent arg0) { 
					   shlCantineprof.close(); //On ferme la fenetre
					   Accueil.main(null); //On ouvre la fenetre accueil
				   }
			}); 
	         
	         btnTrinom.addSelectionListener(new SelectionAdapter() { //Quand on appui sur le bouton Trier par nom
				 
				   @Override
				   public void widgetSelected(SelectionEvent arg0) { 
					   table.removeAll();
					   try {
				            Connection cnx = DriverManager.getConnection(url, user, password);
				            Statement stm = cnx.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
				            //On affiche les eleves dans l'ordre aplphabétique
				            ResultSet result = stm.executeQuery("SELECT * FROM compte WHERE role = 'prof' order by nom asc");
		
				            while(result.next()) {
				            	//On remplit les ligne du tableau
				            	TableItem tableItem_1 = new TableItem(table, SWT.NONE);
				            	tableItem_1.setText(0, result.getString(2));//Nom
				            	tableItem_1.setText(1, result.getString(3));//Prénom
				            	tableItem_1.setText(3, result.getString(6));//Demi-pensionnaire
				            	tableItem_1.setText(4, result.getString(7));//Jours
				            	tableItem_1.setText(5, result.getString(8));//Régime alimentaire
				            }

				         } catch (SQLException e) { //Si il y a une erreur de connexion à la bdd
				             System.out.println("Une erreur est survenue lors de la connexion à la base de données"); //On affiche le  message
				             e.printStackTrace();
				         
				         }; 
				   }
			}); 
	         
	         btnRetour.addSelectionListener(new SelectionAdapter() { //Quand on appui sur le bouton vider
				 
				   @Override
				   public void widgetSelected(SelectionEvent arg0) { //On vide toutes les zones de texte
					   shlCantineprof.close(); //On ferme la page
					   Accueil.main(null); //On ouvre la page accueil
				   }
			});

	}
}
