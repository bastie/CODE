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
package biz.ritter.develop.cobol.prefs;

import biz.ritter.develop.cobol.CorePlugInActivator;

import org.eclipse.jface.preference.*;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.IWorkbenchPreferencePage;
import org.eclipse.ui.IWorkbench;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

/**
 * Diese Klasse sorgt für eine Seite für Einstellungen in CODE:
 * COBOL Build Path
 * 
 * @author Sͬeͥbͭaͭsͤtͬian
 */
public class COBOLBuildPathPreferencePage
	extends PreferencePage
	implements IWorkbenchPreferencePage {
  /* 
   * By subclassing <samp>FieldEditorPreferencePage</samp>, we
   * can use the field support built into JFace that allows
   * us to create a page that is small and knows how to 
   * save, restore and apply itself.
   * <p>
   * This page is used to modify preferences only. They
   * are stored in the preference store that belongs to
   * the main plug-in class. That way, preferences can
   * be accessed directly via the preference store.
   */
  
  private EnvSelectionAdapter buttonSelectionListener = new EnvSelectionAdapter();
  private static final int TODO_NEW_ENV = 1;
  private static final int TODO_EDIT_ENV = 2;
  private static final int TODO_REMOVE_ENV = 4;
  private List envList;
//  private HashMap<String,String> cobEnv = new HashMap<String, String>();
  
	public COBOLBuildPathPreferencePage() {
		super();
		setMessage("COBOL Build Path");
		setTitle("COBOL");
		setPreferenceStore(CorePlugInActivator.getDefault().getPreferenceStore());
		setDescription("Define COBOL build paths and environment.");
	}
	
	
	/**
	 * Creates the field editors. Field editors are abstractions of
	 * the common GUI blocks needed to manipulate various types
	 * of preferences. Each field editor knows how to save and
	 * restore itself.
	 */
	public void createFieldEditors() {
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ui.IWorkbenchPreferencePage#init(org.eclipse.ui.IWorkbench)
	 */
	public void init(IWorkbench workbench) {
	}

  @Override
  protected Control createContents(Composite parent) {
    // TODO Auto-generated method stub
    Composite pane = new Composite(parent,SWT.BORDER_SOLID);
    pane.setLayout(null);
    
    Composite composite_list = new Composite(pane, SWT.NONE);
    composite_list.setBounds(0, 0, 182, 320);
    composite_list.setLayout(null);
    
    envList = new List(composite_list, SWT.BORDER);
    envList.setItems(new String[] {"abc", "def"});
    envList.setBounds(5, 5, 172, 310);
    if (envList.getItemCount()>0) {
      envList.select(0);
    }
    
    Composite composite = new Composite(pane, SWT.NONE);
    composite.setBounds(182, 0, 183, 320);
    composite.setLayout(null);
    
    Button button_NewEnv = new Button(composite, SWT.NONE);
    button_NewEnv.addSelectionListener(this.buttonSelectionListener);
    button_NewEnv.setBounds(5, 5, 55, 25);
    button_NewEnv.setText("&New");
    button_NewEnv.setData("todo", TODO_NEW_ENV);
    
    Button button_EditEnv = new Button(composite, SWT.NONE);
    button_EditEnv.addSelectionListener(this.buttonSelectionListener);
    button_EditEnv.setBounds(5, 35, 55, 25);
    button_EditEnv.setText("&Edit");
    button_EditEnv.setData("todo", TODO_EDIT_ENV);
    
    Button button_RemoveEnv = new Button(composite, SWT.NONE);
    button_RemoveEnv.addSelectionListener(this.buttonSelectionListener);
    button_RemoveEnv.setBounds(5, 65, 55, 25);
    button_RemoveEnv.setText("&Remove");
    button_RemoveEnv.setData("todo", TODO_REMOVE_ENV);

    return pane;
  }
//  
//  private void doEdit () {
//    final String selected = this.envList.getItem(this.envList.getSelectionIndex());
//    
//  }
//  private void doNew () {
//    
//  }
//  
//  private void doRemove () {
//    final String selected = this.envList.getItem(this.envList.getSelectionIndex());
//  }
//  
  private static class EnvSelectionAdapter extends SelectionAdapter {
    @Override
    public void widgetSelected(SelectionEvent e) {
      if (null != e.getSource() && e.getSource() instanceof Button) {
        Object todo = ((Button)e.getSource()).getData("todo");
        if (null == todo) todo = Integer.valueOf(-1);
        switch (todo.hashCode()) {
        case TODO_EDIT_ENV :
          System.out.println("EDIT");
          break;
        case TODO_NEW_ENV :
          System.out.println("NEW");
          break;
        case TODO_REMOVE_ENV :
          System.out.println("REMOVE");
          break;
        default:
        }
      }
      else {
        super.widgetSelected(e);
      }
   }
  }
}