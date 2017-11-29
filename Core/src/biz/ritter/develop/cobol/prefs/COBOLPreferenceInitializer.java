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

import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;
import org.eclipse.jface.preference.IPreferenceStore;

/**
 * Klasse die die COBOL Einstellungen mit Defaultwerten initiiert.
 * @author Sͬeͥbͭaͭsͤtͬian
 */
public class COBOLPreferenceInitializer extends AbstractPreferenceInitializer {

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer#initializeDefaultPreferences()
	 */
	public void initializeDefaultPreferences() {
		IPreferenceStore store = CorePlugInActivator.getDefault().getPreferenceStore();
		//FIXME PATH anpassen für andere OS
		store.setDefault(COBOLPreferenceConstants.CODE_PREF_COBOL_COMPILER_PATH, "g:/MinGW/bin");
		store.setDefault(COBOLPreferenceConstants.CODE_PREF_COBOL_COMPILER_EXECUTABLE, "cobc");
		store.setDefault(COBOLPreferenceConstants.CODE_PREF_COBOL_COMPILER_PARAMETERS, "-S");
	}

}
