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
 * COBOLLanguage provides informations of COBOL dialects, like MF COBOL,
 * OpenCOBOL. Implementation can provide dialect or version specific 
 * information. For example OpenCOBOL 1.1 implements some more COBOL
 * features as version 1.0.
 * 
 * @author Sͬeͥbͭaͭsͤtͬian
 */
public interface COBOLLanguage {


  /**
   * Figurative constants are natural speaking constants for
   * defined values like ZERO or SPACE
   * @return figurative constants
   */
  public String [] getFigurativeConstants ();
  
  /**
   * Special registers like RETURN-CODE
   * @return
   */
  public String [] getSpecialRegisters ();
  
  /**
   * COBOL language keywords, without deprecated
   * keywords.
   * @return
   */
  public String [] getKeywords ();
  
  /**
   * COBOL language deprecated keywords.
   * @return
   */
  public String [] getDeprecatedKeywords ();

  /**
   * Instrinct functions are a COBOL extension. The descriptior provides
   * needed informations for using this functions.
   * @return
   */
  public COBOLInstrinctFunctionDescriptor [] getInstrinctFunctionInformations ();
  
  /**
   * The COBOL dialect specific by this type with associated 
   * COBOLDialectDescripter provides information for this implementation.
   * @return
   */
  public COBOLDialectDescriptor getDialectInformations ();
  
}
