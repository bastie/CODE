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
package biz.ritter.develop.cobol.editor;

import org.eclipse.jface.text.rules.IWordDetector;

/**
 * COBOLWordDetector ermittelt, ob die übergebenen Zeichen zulässig sind,
 * um in COBOL verwendet zu werden.
 * 
 * @author Sͬeͥbͭaͭsͤtͬian
 */
public class COBOLWordDetector implements IWordDetector {
  
  /**
   * COBOL kann die gleichen Zeichen wie Java verwenden mit Ausnahme der Zeichen
   * <code>Minus</code> und <code>Unterstrich</code>.
   * @param character das zu prüfende Zeichen.
   * @return <code>true</code> wenn es ein gültiges Zeichen ist.
   */
  public boolean isWordPart( final char character ) {
    switch (character) {
    case '_' : return false;
    case '-' : return true;
    default  : return Character.isJavaIdentifierPart( character );
    }
  }

  /**
   * COBOL kann die gleichen Zeichen wie Java verwenden mit Ausnahme der Zeichen
   * <code>Minus</code> und <code>Unterstrich</code>.
   * @param character das zu prüfende Zeichen.
   * @return <code>true</code> wenn es ein gültiges Zeichen ist.
   */
  public boolean isWordStart( final char character ) {
    switch (character) {
    case '_' : return false;
    case '-' : return true;
    default  : return Character.isJavaIdentifierStart( character );
    }
  }    
}
