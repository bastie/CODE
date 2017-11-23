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
package biz.ritter.develop.cobol.filetypes;

import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

/**
 * Die Activator Klasse kontrolliert den Plug-In Levenszyklus
 * @author Sͬeͥbͭaͭsͤtͬian
 */
public class FileTypesPlugInActivator extends AbstractUIPlugin {
  
  /** Die plug-in ID */
  public static final String              PLUGIN_ID = "CODE_FileTypes"; //$NON-NLS-1$
                                                                        
  /** Die Instance */
  private static FileTypesPlugInActivator plugin;
  
  /**
   * The constructor
   */
  public FileTypesPlugInActivator() {
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
  public static FileTypesPlugInActivator getDefault() {
    return plugin;
  }
  
}
