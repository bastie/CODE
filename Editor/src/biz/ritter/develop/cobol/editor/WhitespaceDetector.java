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

import org.eclipse.jface.text.rules.IWhitespaceDetector;

 /**
  *  Identifizieren von Whitespaces
  *  @author Sͬeͥbͭaͭsͤtͬian
  */
public class WhitespaceDetector implements IWhitespaceDetector {

  /*
   * (non-Javadoc)
   * @see org.eclipse.jface.text.rules.IWhitespaceDetector#isWhitespace(char)
   * 
   * Ignored chars: SPACE, LF, HTAB, CR
   */
  public boolean isWhitespace( final char c ) {
    return( '\n' == c || ' ' == c || '\t' == c || '\r'== c);
  }
}