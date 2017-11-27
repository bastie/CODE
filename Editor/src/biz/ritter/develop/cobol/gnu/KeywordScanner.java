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
package biz.ritter.develop.cobol.gnu;

import biz.ritter.develop.cobol.editor.COBOLWordDetector;
import biz.ritter.develop.cobol.editor.ColorManager;
import biz.ritter.develop.cobol.editor.WhitespaceDetector;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

import org.eclipse.jface.text.TextAttribute;
import org.eclipse.jface.text.rules.*;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;

/** 
 * Identifiziert die COBOL Schlüsselwörter, Mnemonic, Intrinsic Funktionionen,... und definiert
 * das Formats zur Anzeige.
 * @author Sͬeͥbͭaͭsͤtͬian
 */
public class KeywordScanner extends RuleBasedScanner {

  static String [] KEYWORDS = { };
  static String [] DEPRECATED = { "goto", "author", "alter", "date-written"};
  static String [] UNSUPPORTED = {};
  static String [] MNEMONIC = {};
  static String [] INTRINSIC_FUNCTIONS = {};
  
  public KeywordScanner() {
    try {
      loadReservedKeywords();
      loadMnemonicNames();
      loadInstrinsicFunctions();
      createRules();
    }
    catch (Exception e) { //FIXME - CoreException
      throw new RuntimeException(e);
    }
  }

  /**
   * Create formatting rules
   */
  private void createRules() {
    IToken defaultToken = createDefaultToken();
    IToken keywordToken = createKeywordToken();
    IToken deprecatedToken = createDeprecatedToken();
    IToken unsupportedToken = createUnsupportedToken();
    IToken intrinsicToken = createIntrinsicToken(); 
    IToken mnemonicToken = createMnemonicToken(); 

    WordRule keywordRule = new WordRule  (new COBOLWordDetector(), defaultToken , true);
   
    for( String keyword : MNEMONIC ) {
      keywordRule.addWord( keyword, mnemonicToken );
    }
   
    for( String keyword : INTRINSIC_FUNCTIONS ) {
      keywordRule.addWord( keyword, intrinsicToken );
    }
   
    for( String keyword : KEYWORDS ) {
      keywordRule.addWord( keyword, keywordToken );
    }
   
    for( String keyword : DEPRECATED ) {
      keywordRule.addWord( keyword, deprecatedToken);
    }
    
    for( String keyword : UNSUPPORTED ) {
      keywordRule.addWord( keyword, unsupportedToken);
    }
    
    IRule[] rules = new IRule[] {
      keywordRule,
      // generic whitespace rule.
      new WhitespaceRule( new WhitespaceDetector() )
    };
    
    setRules( rules );
  }

  /**
   * A keyword token
   * @return
   */
  private IToken createKeywordToken() {
    Color color = ColorManager.getColor( ColorManager.KEYWORD );
    TextAttribute ta = new TextAttribute( color, null, SWT.BOLD );
    return new Token( ta );
  }

  /**
   * A mnemonic token
   * @return
   */
  private IToken createMnemonicToken() {
    Color color = ColorManager.getColor( ColorManager.MNEMONIC );
    TextAttribute ta = new TextAttribute( color, null, SWT.BOLD );
    return new Token( ta );
  }

  /**
   * An intrinsic function token
   * @return
   */
  private IToken createIntrinsicToken() {
    Color color = ColorManager.getColor( ColorManager.INTRINSIC );
    TextAttribute ta = new TextAttribute( color, null, SWT.BOLD );
    return new Token( ta );
  }

  /**
   * Token for deprecated COBOL words
   * @return
   */
  private IToken createDeprecatedToken() {
    Color color = ColorManager.getColor( ColorManager.KEYWORD );
    TextAttribute ta = new TextAttribute( color, null, TextAttribute.STRIKETHROUGH);
    return new Token( ta );
  }

  /**
   * Token for unsupported COBOL words on actual dialect.
   * @return
   */
  private IToken createUnsupportedToken() {
    Color color = ColorManager.getColor( ColorManager.UNSUPPORTED );
    TextAttribute ta = new TextAttribute( color, null, SWT.ITALIC + TextAttribute.STRIKETHROUGH);
    return new Token( ta );
  }

  /**
   * Default token
   * @return
   */
  private IToken createDefaultToken() {
    Color color = ColorManager.getColor( ColorManager.DEFAULT );
    IToken defaultToken = new Token( new TextAttribute( color ) );
    setDefaultReturnToken( defaultToken );
    return defaultToken;
  }
 
  
  /**
   * Load defined keywords for syntax highlightning. Not 
   * keywords functions are seperated.
   * @throws IOException
   */
  private static void loadReservedKeywords () throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(KeywordScanner.class.getResourceAsStream("gnu-reserved-1.1.0")));
    ArrayList<String> keywords = new ArrayList<String> ();
    ArrayList<String> unsupported = new ArrayList<String>();
    String nextLine = null;
    while (null != (nextLine = br.readLine())) {
      // if keyword line
      if (nextLine.trim().length() > 0 && nextLine.toUpperCase().equals(nextLine)) {
        StringTokenizer st = new StringTokenizer(nextLine," \t", false);
        final String word = st.nextToken();
        final boolean isUnsupported = "(N)".equals(st.nextToken());
        if (isUnsupported) {
          unsupported.add(word);
        }
        else {
          keywords.add(word);
        }
      }
    }
    String [] keywordArray = new String [keywords.size()];
    keywordArray = keywords.toArray(keywordArray);
    
    for (String keyword : UNSUPPORTED) {
      unsupported.add(keyword);
    }
    String [] unsupporedArray = new String [unsupported.size()];
    unsupporedArray = unsupported.toArray(unsupporedArray);
    
    KEYWORDS = keywordArray;
    UNSUPPORTED = unsupporedArray;
  }

  /**
   * Load defined intrinsic functions for syntax highlightning. Not 
   * supported intrinsic functions are seperated.
   * @throws IOException
   */
  private static void loadInstrinsicFunctions () throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(KeywordScanner.class.getResourceAsStream("gnu-intrinsic-1.1.0")));
    ArrayList<String> intrinsic = new ArrayList<String> ();
    ArrayList<String> unsupported = new ArrayList<String>();
    String nextLine = null;
    while (null != (nextLine = br.readLine())) {
      // if keyword line
      if (nextLine.trim().length() > 0 && nextLine.toUpperCase().equals(nextLine)) {
        StringTokenizer st = new StringTokenizer(nextLine," \t", false);
        final String word = st.nextToken();
        final boolean isUnsupported = "(N)".equals(st.nextToken());
        if (isUnsupported) {
          unsupported.add(word);
        }
        else {
          intrinsic.add(word);
        }
      }
    }
    String [] intrinsicArray = new String [intrinsic.size()];
    intrinsicArray = intrinsic.toArray(intrinsicArray);
    
    for (String keyword : UNSUPPORTED) {
      unsupported.add(keyword);
    }
    String [] unsupporedArray = new String [unsupported.size()];
    unsupporedArray = unsupported.toArray(unsupporedArray);

    INTRINSIC_FUNCTIONS = intrinsicArray;
    UNSUPPORTED = unsupporedArray;
  }

  /**
   * Load defined mnemonics for syntax highlightning. 
   * @throws IOException
   */
  private static void loadMnemonicNames () throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(KeywordScanner.class.getResourceAsStream("gnu-intrinsic-1.1.0")));
    ArrayList<String> mnemonic = new ArrayList<String> ();
    String nextLine = null;
    while (null != (nextLine = br.readLine())) {
      // if keyword line
      if (nextLine.trim().length() > 0 && nextLine.toUpperCase().equals(nextLine)) {
        StringTokenizer st = new StringTokenizer(nextLine," \t", false);
        final String word = st.nextToken();
        mnemonic.add(word);
      }
    }
    String [] mnemonicArray = new String [mnemonic.size()];
    mnemonicArray = mnemonic.toArray(mnemonicArray);
    KeywordScanner.MNEMONIC = mnemonicArray;
  }
}