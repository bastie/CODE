/**
 * Copyright 2010 Sebastian Ritter
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

import org.eclipse.jface.text.TextAttribute;
import org.eclipse.jface.text.rules.*;
import org.eclipse.swt.graphics.Color;

/** 
 * Scannen nach Kommentare
 * @author Sͬeͥbͭaͭsͤtͬian
 */
class CommentScanner extends RuleBasedScanner {

  public static final String EOL_COMMENT_START = "*>";

  CommentScanner() {
    IToken commentToken = createCommentToken();
    IRule[] rules = new IRule[] {
      new EndOfLineRule( EOL_COMMENT_START, commentToken )
    };
    setRules( rules );
  }


  private IToken createCommentToken() {
    Color color = ColorManager.getColor( ColorManager.COMMENT );
    IToken defaultToken = new Token( new TextAttribute( color ) );
    return defaultToken;
  }
}