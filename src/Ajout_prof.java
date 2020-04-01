import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
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

public class Ajout_prof {
	private static Text textNom;
	private static Text textPrenom;
	private static Text textDP;
	private static Text textJours;
	private static Text textRegime;

	
	public static void main(String[] args) {
		Display display = Display.getDefault();
		Shell Ajout_prof = new Shell();
		Ajout_prof.setSize(860, 449);
		Ajout_prof.setText("Ajouter un professeur");
		
		Label lblNom = new Label(Ajout_prof, SWT.NONE);
		lblNom.setBounds(223, 59, 40, 25);
		lblNom.setText("Nom :");
		
		Label lblPrenom = new Label(Ajout_prof, SWT.NONE);
		lblPrenom.setBounds(205, 121, 58, 25);
		lblPrenom.setText("Pr\u00E9nom :");
		
		textNom = new Text(Ajout_prof, SWT.BORDER);
		textNom.setBounds(292, 56, 131, 31);
		
		textPrenom = new Text(Ajout_prof, SWT.BORDER);
		textPrenom.setBounds(292, 118, 131, 31);
		
		Button btnAjouter = new Button(Ajout_prof, SWT.NONE);
		btnAjouter.setBounds(540, 114, 113, 35);
		btnAjouter.setText("Ajouter");
		
		Label lblDemipensionnaire = new Label(Ajout_prof, SWT.NONE);
		lblDemipensionnaire.setText("Demi-pensionnaire :");
		lblDemipensionnaire.setBounds(122, 182, 141, 25);
		
		Label lblQuelsJours = new Label(Ajout_prof, SWT.NONE);
		lblQuelsJours.setText("Quels jours :");
		lblQuelsJours.setBounds(178, 245, 85, 25);
		
		Label lblRgimeAlimentaire = new Label(Ajout_prof, SWT.NONE);
		lblRgimeAlimentaire.setText("R\u00E9gime alimentaire :");
		lblRgimeAlimentaire.setBounds(122, 316, 141, 25);
		
		textDP = new Text(Ajout_prof, SWT.BORDER);
		textDP.setBounds(292, 176, 131, 31);
		
		textJours = new Text(Ajout_prof, SWT.BORDER);
		textJours.setBounds(292, 242, 131, 31);
		
		textRegime = new Text(Ajout_prof, SWT.BORDER);
		textRegime.setBounds(292, 310, 131, 31);
		
		Button btnVider = new Button(Ajout_prof, SWT.NONE);
		btnVider.setText("Vider");
		btnVider.setBounds(540, 235, 113, 35);
		
		Label lblErreur = new Label(Ajout_prof, SWT.NONE);
		lblErreur.setBounds(540, 321, 256, 20);
		
		btnAjouter.addSelectionListener(new SelectionAdapter() {
			 
			   @Override
			   public void widgetSelected(SelectionEvent arg0) {
				   
				   String url="jdbc:mysql://localhost/cantine?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
			        String user="root";
			        String password="";
			        try {
			             Connection cnx = DriverManager.getConnection(url, user, password);
			             Statement stm = cnx.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			             ResultSet rs = stm.executeQuery("select * from compte where nom ='"+textNom.getText()+"' and prenom ='"+textPrenom.getText()+"'");

			             if(rs.next()){
			            	 lblErreur.setText("Professeur déjà existant");
			             }
			             else {
			            	 PreparedStatement stmt = cnx.prepareStatement("INSERT INTO compte(nom, prenom, DP, jours, regime, role) VALUES (?, ?, ?, ?, ?, 'prof')");
			            	 stmt.setString(1, textNom.getText());
			            	 stmt.setString(2, textPrenom.getText());
			            	 stmt.setString(3, textDP.getText());
			            	 stmt.setString(4, textJours.getText());
			            	 stmt.setString(5, textRegime.getText());

			            	 stmt.executeUpdate();
			            	 lblErreur.setText("Professeur ajouté");

			             }
			         } catch (SQLException e) {
			             System.out.println("Une erreur est survenue lors de la connexion à la base de données");
			             e.printStackTrace();
			         
			};  
			   }
		});
		
		btnVider.addSelectionListener(new SelectionAdapter() {
			 
			   @Override
			   public void widgetSelected(SelectionEvent arg0) {
				   	textNom.setText("");
	            	textPrenom.setText("");
	            	textDP.setText("");
	            	textJours.setText("");
	            	textRegime.setText("");
			   }
		});

		Ajout_prof.open();
		Ajout_prof.layout();
		while (!Ajout_prof.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
		
		

	}
}
