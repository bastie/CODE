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

import java.util.HashMap;
import java.util.Iterator;

import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.widgets.Display;


/** 
 * Color Manager als Singleton für die Editoren.
 * @author Sͬeͥbͭaͭsͤtͬian
 */
public class ColorManager {

  public final static String COMMENT = "__COMMENT";
  public final static String KEYWORD = "__KEYWORD";
  public final static String UNSUPPORTED = "__UNSUPPORTED";
  public final static String DEFAULT = "__DEFAULT";
  public final static String INTRINSIC = "__INTRINSIC";
  public final static String MNEMONIC = "__MNEMONIC";
  
  private static ColorManager instance;

	private HashMap<RGB,Color> colors;
  private HashMap<String,RGB> rgbs;

  private ColorManager() {
    colors = new HashMap<RGB, Color>();
    rgbs = new HashMap<String, RGB>();
    rgbs.put( COMMENT, new RGB( 128, 0, 0 ) );
    rgbs.put( DEFAULT, new RGB( 0, 0, 0 ) );
    rgbs.put( KEYWORD, new RGB( 0, 0, 128 ) );
    rgbs.put( UNSUPPORTED, new RGB( 128, 30, 30 ) );
    rgbs.put( INTRINSIC, new RGB (30,128, 30));
    rgbs.put( MNEMONIC, new RGB (0,0,128));
  }

  /**
   * Return a ColorManager instace.
   * @return
   * @pattern Singleton
   */
  public static synchronized ColorManager getInstance() {
    if( instance == null ) {
      instance = new ColorManager();
    }
    return instance;
  }

  /** Release resources
   */
	public void dispose() {
		Iterator<Color> iterator = colors.values().iterator();
		while( iterator.hasNext() ) {
		  iterator.next().dispose();
    }
	}

  /** Return Color instance for specific key */
  public static Color getColor( final String key ) {
    return getInstance().getColor( getInstance().findRGB( key ) );
  }  
  
  
  private RGB findRGB( final String key ) {
    RGB result = rgbs.get( key );
    if( result == null ) {
      result = rgbs.get( DEFAULT );
    }
    return result;
  }
  
  private Color getColor( final RGB rgb ) {
    Color color = colors.get( rgb );
    if( color == null ) {
      color = new Color( Display.getCurrent(), rgb );
      colors.put( rgb, color );
    }
    return color;
  }
}