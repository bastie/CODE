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

import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.IDocumentPartitioner;
import org.eclipse.jface.text.rules.FastPartitioner;
import org.eclipse.ui.editors.text.FileDocumentProvider;

/**
 * Create the IDocument for COBOLSourceEditor, if File is open.
 */
public class COBOLSourceDocumentProvider extends FileDocumentProvider {

  protected IDocument createDocument( final Object element ) 
                                                          throws CoreException {
    // Superclass build an IDocument.
    IDocument document = super.createDocument( element );

    // We need a partition - using the FastPartitioner.
    // For scanning process we use own COBOLSourcePartitionScanner.  
    if( document != null ) {
      String[] tokenTypes = new String[] { COBOLSourcePartitionScanner.COMMENT,
                                           IDocument.DEFAULT_CONTENT_TYPE };
      COBOLSourcePartitionScanner scanner = new COBOLSourcePartitionScanner();
      IDocumentPartitioner partitioner = new FastPartitioner( scanner, 
                                                                 tokenTypes );
      partitioner.connect( document );
      document.setDocumentPartitioner( partitioner );
    }
    return document;
  }
}