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
 * COBOL Einstellungsseite
 * @author Sͬeͥbͭaͭsͤtͬian
 */
public class COBOLPreferencePage
	extends FieldEditorPreferencePage
	implements IWorkbenchPreferencePage {

	public COBOLPreferencePage() {
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
		  StringFieldEditor cobolCompilerNameStringFieldEditor = new StringFieldEditor(COBOLPreferenceConstants.CODE_PREF_COBOL_COMPILER_EXECUTABLE, "COBOL &compiler name", getFieldEditorParent());
		  cobolCompilerNameStringFieldEditor.setEmptyStringAllowed(false);
		  addField(
		  	cobolCompilerNameStringFieldEditor);
		}
	  {
	    StringFieldEditor cobolCompilerParameterStringFieldEditor = new StringFieldEditor(COBOLPreferenceConstants.CODE_PREF_COBOL_COMPILER_PARAMETERS, "COBOL compiler &parameter", -1, StringFieldEditor.VALIDATE_ON_KEY_STROKE, getFieldEditorParent());
	    cobolCompilerParameterStringFieldEditor.setEmptyStringAllowed(false);
	    addField(cobolCompilerParameterStringFieldEditor);
	  }
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ui.IWorkbenchPreferencePage#init(org.eclipse.ui.IWorkbench)
	 */
	public void init(IWorkbench workbench) {
	}
	
}