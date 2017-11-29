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
import org.eclipse.ui.IWorkbenchPreferencePage;
import org.eclipse.ui.IWorkbench;


/**
 * Diese Klasse sorgt für eine Seite für Einstellungen in CODE:
 * COBOL Compiler Einstellungen
 * 
 * @author Sͬeͥbͭaͭsͤtͬian
 */
public class COBOLCompilerPreferencePage
	extends FieldEditorPreferencePage
	implements IWorkbenchPreferencePage {

	public COBOLCompilerPreferencePage() {
		super(GRID);
		setMessage("CODE Preferences");
		setTitle("COBOL");
		setPreferenceStore(CorePlugInActivator.getDefault().getPreferenceStore());
		setDescription("COBOL Development Environment preferences pages");
	}
	
	public void createFieldEditors() {
		addField(new DirectoryFieldEditor(COBOLPreferenceConstants.CODE_PREF_COBOL_COMPILER_PATH, 
				"COBOL compiler &directory", getFieldEditorParent()));
		{
		  StringFieldEditor stringFieldEditor = new StringFieldEditor(COBOLPreferenceConstants.CODE_PREF_COBOL_COMPILER_EXECUTABLE, "COBOL &compiler name", getFieldEditorParent());
		  stringFieldEditor.setEmptyStringAllowed(false);
		  addField(
		  	stringFieldEditor);
		}
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ui.IWorkbenchPreferencePage#init(org.eclipse.ui.IWorkbench)
	 */
	public void init(IWorkbench workbench) {
	}
	
}