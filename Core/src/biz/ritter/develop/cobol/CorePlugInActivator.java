/**
 * Copyright 2010 Sebastian Ritter
 *
 * Lizenziert unter der EUPL, nur Version 1.1 ("Lizenz");
 * 
 * Sie d�rfen dieses Werk ausschlie�lich gem��
 * dieser Lizenz nutzen.
 * Eine Kopie der Lizenz finden Sie hier:
 *
 * http://ec.europa.eu/idabc/eupl5
 * 
 * Sofern nicht durch anwendbare Rechtsvorschriften
 * gefordert oder in schriftlicher Form vereinbart, wird
 * die unter der Lizenz verbreitete Software "so wie sie
 * ist", OHNE JEGLICHE GEW�HRLEISTUNG ODER BEDINGUNGEN -
 * ausdr�cklich oder stillschweigend - verbreitet.
 * Die sprachspezifischen Genehmigungen und Beschr�nkungen
 * unter der Lizenz sind dem Lizenztext zu entnehmen.
 */
package biz.ritter.develop.cobol;

import biz.ritter.develop.cobol.internal.CODEConstants;

import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

/**
 * Der PlugIn Aktivator verwaltet den Lebenszyklus.
 * @author Sͬeͥbͭaͭsͤtͬian
 */
public class CorePlugInActivator extends AbstractUIPlugin {
  
  // The plug-in ID
  public static final String         PLUGIN_ID = CODEConstants.CODE_CORE_ID;
  
  // The shared instance
  private static CorePlugInActivator plugin;
  
  /**
   * Erstellt eine neue Instanz
   */
  public CorePlugInActivator() {
  }
  
  /*
   * (non-Javadoc)
   * 
   * @see
   * org.eclipse.ui.plugin.AbstractUIPlugin#start(org.osgi
   * .framework.BundleContext)
   */
  public void start(BundleContext context) throws Exception {
    super.start(context);
    plugin = this;
  }
  
  /*
   * (non-Javadoc)
   * 
   * @see
   * org.eclipse.ui.plugin.AbstractUIPlugin#stop(org.osgi
   * .framework.BundleContext)
   */
  public void stop(BundleContext context) throws Exception {
    plugin = null;
    super.stop(context);
  }
  
  /**
   * Returns the shared instance
   * 
   * @return the shared instance
   */
  public static CorePlugInActivator getDefault() {
    return plugin;
  }
  
}
