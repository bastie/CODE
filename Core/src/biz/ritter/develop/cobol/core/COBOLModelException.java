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

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;

import biz.ritter.develop.cobol.CorePlugInActivator;

/**
 * Eine COBOLModelException
 * 
 * @author Sͬeͥbͭaͭsͤtͬian
 * 
 */
public class COBOLModelException extends CoreException {
  
  /*
   * 
   */
  private static final long serialVersionUID = 1L;
  
  /**
   * Ummantelt eine andere Exception oder ein Error mit dieser neuen 
   * Instanz.
   * @param exceptionOrErrorToWrap Fehler
   */
  public COBOLModelException (Throwable exceptionOrErrorToWrap) {
    this();
    super.initCause(exceptionOrErrorToWrap);
  }
  
  /**
   * Erzeugt eine einfache COBOLModelException Instanz
   */
  public COBOLModelException() {
    super(new Status(Status.ERROR, CorePlugInActivator.PLUGIN_ID,
        "COBOL model exception"));
  }
  
  /**
   * Liefert ein logbares IStatus Objekt zur Verwendung.
   * 
   * <pre>
   * yourPlugIn.getLog().log(result);
   * </pre>
   * 
   * @see CoreException#getStatus()
   * @return IStatus logable IStatus
   */
  public IStatus getLogableStatus() {
    final IStatus result = new Status(this.getStatus().getSeverity(), this
        .getStatus().getPlugin(), this.getStatus().getMessage(), this
        .getStatus().getException());
    return result;
  }
}
