/**
 * Copyright 2010, 2017 Sebastian Ritter
 *
 * Lizenziert unter der EUPL, nur Version 1.1 ("Lizenz");
 * 
 * Sie dürfen dieses Werk ausschließlich gemäß
 * dieser Lizenz nutzen.
 * Eine Kopie der Lizenz finden Sie hier:
 *
 * http://ec.europa.eu/idabc/eupl5
 * 
 * Sofern nicht durch anwendbare Rechtsvorschriften
 * gefordert oder in schriftlicher Form vereinbart, wird
 * die unter der Lizenz verbreitete Software "so wie sie
 * ist", OHNE JEGLICHE GEWÄHRLEISTUNG ODER BEDINGUNGEN -
 * ausdrücklich oder stillschweigend - verbreitet.
 * Die sprachspezifischen Genehmigungen und Beschränkungen
 * unter der Lizenz sind dem Lizenztext zu entnehmen.
 */
package biz.ritter.develop.cobol.wizard;

import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

/**
 * Seite für den Wizard zur Erstellung eines neuen COBOL Projektes.
 * @author Sͬeͥbͭaͭsͤtͬian
 * 
 */
public class NewProjectPage extends WizardPage {
  private Text    txtProjectName;
  private Text    txtLocation;
  private boolean useDefaultLocation = true;
  
  public void setUseDefaultLocation(boolean b) {
    this.useDefaultLocation = b;
  }
  
  public boolean isUseDefaultLocation() {
    return this.useDefaultLocation;
  }
  
  protected NewProjectPage() {
    super("COBOL Projekt Wizard...");
    setTitle("Create a COBOL Project");
    setMessage("Create new COBOL Project");
    setDescription("New COBOL Project Wizard");
  }
  
  /**
   * Liefert den gewählten Projektnamen zurück
   * @return Projektname für unser COBOL Projekt
   */
  public String getProjectName() {
    return this.txtProjectName.getText().trim();
  }
  
  /**
   * Liefert den gewählten Speicherort für das COBOL Projekt zurück.
   * @return Speicherort für unser COBOL Projekt
   */
  public String getLocation() {
    return this.txtLocation.getText().trim();
  }
  
  @Override
  public void createControl(Composite parent) {
    Composite container = new Composite(parent, SWT.NULL);
    setControl(container);
    container.setLayout(new GridLayout(4, false));
    
    Label lblName = new Label(container, SWT.NONE);
    lblName.setText("&Name");
    new Label(container, SWT.NONE);
    
    txtProjectName = new Text(container, SWT.BORDER);
    txtProjectName.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true,
        false, 2, 1));
    
    final Button btnUseDefaultLocation = new Button(container, SWT.CHECK);
    btnUseDefaultLocation.addSelectionListener(new SelectionAdapter() {
      @Override
      public void widgetSelected(SelectionEvent e) {
        NewProjectPage.this.setUseDefaultLocation(btnUseDefaultLocation
            .getSelection());
      }
    });
    btnUseDefaultLocation.setSelection(true);
    btnUseDefaultLocation.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER,
        false, false, 3, 1));
    btnUseDefaultLocation.setText("Use &default location");
    new Label(container, SWT.NONE);
    
    Label lblLocation = new Label(container, SWT.NONE);
    lblLocation.setEnabled(false);
    lblLocation.setText("&Location:");
    new Label(container, SWT.NONE);
    
    txtLocation = new Text(container, SWT.BORDER);
    txtLocation.setEnabled(false);
    txtLocation.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false,
        1, 1));
    
    Button btnBrowseLocation = new Button(container, SWT.NONE);
    btnBrowseLocation.setEnabled(false);
    btnBrowseLocation.setText("Browse...");
    
    Group grpCobol = new Group(container, SWT.NONE);
    grpCobol.setText("COBOL");
    grpCobol.setLayout(new GridLayout(2, true));
    grpCobol.setLayoutData(new GridData(SWT.FILL, SWT.TOP, false, false, 4, 1));
    
    Button btnUseAnExecution = new Button(grpCobol, SWT.RADIO);
    btnUseAnExecution.setSelection(true);
    btnUseAnExecution.setText("Use an execution en&vironment COBOL:");
    
    CCombo combo = new CCombo(grpCobol, SWT.BORDER);
    combo.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
    
    Button btnUseProjectSpecific = new Button(grpCobol, SWT.RADIO);
    btnUseProjectSpecific.setText("U&se a project specific environment COBOL:");
    
    CCombo combo_1 = new CCombo(grpCobol, SWT.BORDER);
    combo_1
        .setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
  }
}
