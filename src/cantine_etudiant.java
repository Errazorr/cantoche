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

public class cantine_etudiant {

	protected Shell shlCantinetudiant;
	private Table table;
	private int prix_mensuel;

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
		table.setBounds(50, 100, 767, 440);
		
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
		tblclmnRégime.setText("R\u00E9gime alimentaire");
		tblclmnRégime.setWidth(180);
		
		//Colonne prix mensuel
		TableColumn tblclmnPrixMensuel = new TableColumn(table, SWT.NONE);
		tblclmnPrixMensuel.setWidth(100);
		tblclmnPrixMensuel.setText("Prix mensuel");
		
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
		lblTotal.setBounds(693, 583, 103, 28);
		lblTotal.setText("Total du mois :");
		
		Button btnRetour = new Button(shlCantinetudiant, SWT.NONE);
		btnRetour.setText("Retour");
		btnRetour.setBounds(505, 43, 148, 35);
		
		Label lblTotalMois = new Label(shlCantinetudiant, SWT.NONE);
		lblTotalMois.setBounds(802, 583, 70, 20);
		
		//Connexion à la bdd
		String url="jdbc:mysql://localhost/cantine?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
        String user="root";
        String password="";
        try {
            Connection cnx = DriverManager.getConnection(url, user, password);
            Statement stm = cnx.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet result = stm.executeQuery("SELECT * FROM compte WHERE role = 'eleve'");
            
            
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
            	TableItem tableItem_1 = new TableItem(table, SWT.NONE);
            	tableItem_1.setText(0, result.getString(2));//Nom
            	tableItem_1.setText(1, result.getString(3));//Prénom
            	tableItem_1.setText(2, result.getString(5));//Classe
            	tableItem_1.setText(3, result.getString(6));//Demi-pensionnaire
            	tableItem_1.setText(4, result.getString(7));//Jours
            	tableItem_1.setText(5, result.getString(8));//Régime alimentaire
            	tableItem_1.setText(6, String.valueOf(prix_mensuel));
            	
            }
            
            ResultSet prix = stm.executeQuery("SELECT SUM(prix_mensuel) FROM compte WHERE role = 'eleve'");
            prix.next();
            lblTotalMois.setText(prix.getString(1)+" €");

         } catch (SQLException e) { //Si il y a une erreur de connexion à la bdd
             System.out.println("Une erreur est survenue lors de la connexion à la base de données"); //On affiche le  message
             e.printStackTrace();
         
         }; 
         
         btnAjout.addSelectionListener(new SelectionAdapter() { //Quand on appui sur le bouton ajouter
			 
			   @Override
			   public void widgetSelected(SelectionEvent arg0) { 
				   shlCantinetudiant.close(); //On ferme la fenetre
				   Ajout_eleve.main(null); //On ouvre la fenetre pour ajouter un eleve
			   }
		}); 
         
         btnModif.addSelectionListener(new SelectionAdapter() { //Quand on appui sur le bouton modifier
			 
			   @Override
			   public void widgetSelected(SelectionEvent arg0) { 
				   shlCantinetudiant.close(); //On ferme la fenetre
				   modif_eleve.main(null); //On ouvre la fenetre pour modifier un eleve
			   }
		}); 
         
         btnSuppr.addSelectionListener(new SelectionAdapter() { //Quand on appui sur le bouton retour
			 
			   @Override
			   public void widgetSelected(SelectionEvent arg0) { 
				   shlCantinetudiant.close(); //On ferme la fenetre
				   Suppr_eleve.main(null); //On ouvre la fenetre pour asupprimer un eleve
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
			            ResultSet result = stm.executeQuery("SELECT * FROM compte WHERE role = 'eleve' order by nom asc");
			            
			            while(result.next()) {
			            	//On remplit les ligne du tableau
			            	TableItem tableItem_1 = new TableItem(table, SWT.NONE);
			            	tableItem_1.setText(0, result.getString(2));//Nom
			            	tableItem_1.setText(1, result.getString(3));//Prénom
			            	tableItem_1.setText(2, result.getString(5));//Classe
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
         
         btnTriclasse.addSelectionListener(new SelectionAdapter() { //Quand on appui sur le bouton Trier par nom
			 
			   @Override
			   public void widgetSelected(SelectionEvent arg0) { 
				   table.removeAll();
				   try {
			            Connection cnx = DriverManager.getConnection(url, user, password);
			            Statement stm = cnx.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			            //On affiche les eleves dans l'ordre aplphabétique
			            ResultSet result = stm.executeQuery("SELECT * FROM compte WHERE role = 'eleve' order by classe");
			            
			            while(result.next()) {
			            	//On remplit les ligne du tableau
			            	TableItem tableItem_1 = new TableItem(table, SWT.NONE);
			            	tableItem_1.setText(0, result.getString(2));//Nom
			            	tableItem_1.setText(1, result.getString(3));//Prénom
			            	tableItem_1.setText(2, result.getString(5));//Classe
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
				   shlCantinetudiant.close(); //On ferme la page
				   Accueil.main(null); //On ouvre la page accueil
			   }
		});
	}
}
