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
package biz.ritter.develop.cobol.core;

import org.eclipse.core.runtime.IPath;

/**
 * Die Klasse kapselt (zukünftig) Standardinformationen für COBOL
 * 
 * @author Sͬeͥbͭaͭsͤtͬian
 * @version 0.1
 */
public class COBOLCore {
  
  /**
   * Variable für den Ablageort der Copystrecken.
   */
  public static final String COBCPY = "COBCPY";
  
  public static void setCOBCPYVariable(final String variableName,
      final IPath path) throws COBOLModelException {
    throw new COBOLModelException(
        new UnsupportedOperationException(
            String.format("🧐 %s not yet implemented😭!", new Exception().getStackTrace()[0].getMethodName())));
  }
  
  public static void removeCOBCPYVariable(final String variableName)
      throws COBOLModelException {
    new UnsupportedOperationException(
        String.format("🧐 %s not yet implemented😭!", new Exception().getStackTrace()[0].getMethodName()));
  }
  
}
