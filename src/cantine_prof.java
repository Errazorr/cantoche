import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Button;
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
		shlCantineprof.setSize(956, 737);
		shlCantineprof.setText("Cantine professeur");
		
		Button btnNewButton = new Button(shlCantineprof, SWT.NONE);
		btnNewButton.setBounds(50, 576, 204, 35);
		btnNewButton.setText("Ajouter un \u00E9tudiant");
		
		Button btnNewButton_1 = new Button(shlCantineprof, SWT.NONE);
		btnNewButton_1.setBounds(470, 576, 204, 35);
		btnNewButton_1.setText("Modifier un \u00E9tudiant");
		
		Button btnNewButton_2 = new Button(shlCantineprof, SWT.NONE);
		btnNewButton_2.setBounds(260, 576, 204, 35);
		btnNewButton_2.setText("Supprimer un \u00E9tudiant");
		
		table = new Table(shlCantineprof, SWT.BORDER | SWT.FULL_SELECTION);
		table.setLinesVisible(true);
		table.setHeaderVisible(true);
		table.setBounds(50, 100, 835, 440);
		
		TableColumn tblclmnNom = new TableColumn(table, SWT.NONE);
		tblclmnNom.setWidth(100);
		tblclmnNom.setText("Nom");
		
		TableColumn tblclmnPrnom = new TableColumn(table, SWT.NONE);
		tblclmnPrnom.setWidth(100);
		tblclmnPrnom.setText("Pr\u00E9nom");
		
		TableColumn tblclmnClasse = new TableColumn(table, SWT.NONE);
		tblclmnClasse.setWidth(100);
		tblclmnClasse.setText("Classe");
		
		TableColumn tblclmnCantine = new TableColumn(table, SWT.NONE);
		tblclmnCantine.setWidth(82);
		tblclmnCantine.setText("Cantine");
		
		TableColumn tblclmnJour = new TableColumn(table, SWT.NONE);
		tblclmnJour.setWidth(100);
		tblclmnJour.setText("Jour");
		
		TableColumn tblclmnNewColumn = new TableColumn(table, SWT.NONE);
		tblclmnNewColumn.setText("R\u00E9gime");
		tblclmnNewColumn.setWidth(166);
		
		TableColumn tblclmnNewColumn_1 = new TableColumn(table, SWT.NONE);
		tblclmnNewColumn_1.setWidth(182);
		tblclmnNewColumn_1.setText("R\u00E9gime alimentaire");
		
		TableItem tableItem_1 = new TableItem(table, SWT.NONE);
		
		TableItem tableItem = new TableItem(table, SWT.NONE);
		tableItem.setText(new String[] {"Goncalves", "Nathan", "BTS", "Oui", "L,Ma,J,V", "Demi-Pensionnaire", "Non"});
		
		Button btnTrierParNom = new Button(shlCantineprof, SWT.NONE);
		btnTrierParNom.setBounds(67, 43, 122, 35);
		btnTrierParNom.setText("Trier par nom");
		
		Button btnTrierParClasse = new Button(shlCantineprof, SWT.NONE);
		btnTrierParClasse.setBounds(267, 43, 148, 35);
		btnTrierParClasse.setText("Trier par classe");
		
		Label lblTotalDuMois = new Label(shlCantineprof, SWT.NONE);
		lblTotalDuMois.setBounds(693, 583, 209, 15);
		lblTotalDuMois.setText("Total du mois :");

	}
}
