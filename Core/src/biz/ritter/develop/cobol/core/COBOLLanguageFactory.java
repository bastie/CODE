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

/**
 * Klassenfabrik Factory zur Erstellung einer {@link COBOLLanguage} Instanz.
 * @author Sͬeͥbͭaͭsͤtͬian
 * @pattern ClassFactory
 */
public class COBOLLanguageFactory {
  
  private static COBOLLanguage defaultInstance;
  public synchronized static COBOLLanguage getDefaultCOBOLLanguageImpl () {
    if (null == defaultInstance) {
      defaultInstance = new COBOL85LanguageImpl();
    }
    return defaultInstance;
  }
  
  public static COBOLDialectDescriptor [] getSupportedDialects () {
    return new COBOLDialectDescriptor [] {COBOL85LanguageImpl.createCOBOL85LanguageImpl().getDialectInformations()};
  }
}
