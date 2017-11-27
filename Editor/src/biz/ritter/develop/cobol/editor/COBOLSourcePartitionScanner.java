/**
 * Copyright 2010,2017 Sebastian Ritter
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

import org.eclipse.jface.text.rules.*;


/** 
 * Scanner zum Partitionieren des Dokuments, um so das 
 * Dokument in typisierte Abschnitte (partitions) aufzuteilen.
 * Diese kann dann der Editor in unterschiedlicher Weise behandeln
 * (z.B. beim syntaxhighlight)
 * 
 * Funktionalitäten in der SourceViewerConfiguration arbeiten 
 * auf den hier erzeugten Partitionen. 
 *
 * Hier gibt es nur Kommentare und den Rest...
 * 
 * @author Sͬeͥbͭaͭsͤtͬian
 */
public class COBOLSourcePartitionScanner extends RuleBasedPartitionScanner {

  final static String DEFAULT  = "__default";
  final static String COMMENT  = "__comment";

  public COBOLSourcePartitionScanner() {
    IPredicateRule[] rules = new IPredicateRule[] {
      new EndOfLineRule( "*>", new Token( COMMENT ) ),
    };
    setPredicateRules( rules );
  }
}
