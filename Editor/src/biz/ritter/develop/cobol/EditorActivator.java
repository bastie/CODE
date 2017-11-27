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
package biz.ritter.develop.cobol;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;


/**
 * Die EditorActivator Klasse steuert den Plug-In Lebenszyklus
 * @author Sͬeͥbͭaͭsͤtͬian
 */
public class EditorActivator extends AbstractUIPlugin {
  
  /** Die plug-in ID */
  public static final String     PLUGIN_ID = "CODE_Editors"; //$NON-NLS-1$
                                                             
  /** Die Plug-In Instance */
  private static EditorActivator plugin;
  
  /**
   * Das zugehörige ResourcenBundle
   */
  private ResourceBundle resourceBundle; 
  
  /**
   * Der Konstruktor
   */
  public EditorActivator() {}
  
  /**
   * Liefert die Instanz
   * 
   * @return Instanz des PlugIns
   */
  public static EditorActivator getDefault() {
    return plugin;
  }
  
  @Override
  public void start(BundleContext context) throws Exception {
    super.start(context);
    plugin = this;
    try {
      String name = EditorActivator.class.getName();
      resourceBundle = ResourceBundle.getBundle( name );
    } catch( MissingResourceException ignored ) {}
  }
  @Override
  public void stop(BundleContext context) throws Exception {
    plugin = null;
    super.stop(context);
    this.resourceBundle = null;
  }

  /**
   * Liefert eine Instanz des Workspace
   * @return Workspace Instanz
   */
  public static IWorkspace getWorkspace() {
    return ResourcesPlugin.getWorkspace();
  }
  
  /** Returns the string from the plugin's resource bundle,
   * or '!' + 'key' + '!' if not found.
   * @return Zeichenkette aus dem ResourcenBundle oder Fehlertext
   * @param key Schlüssel unter dem im ResourcenBundle gesucht werden soll. 
   */
  public static String getResourceString( final String key ) {
    ResourceBundle bundle = EditorActivator.getDefault().getResourceBundle();
    try {
      return bundle.getString( key );
    } catch( MissingResourceException e ) {
      return "!" + key + "!";
    }
  }
  
  /** Liefert das ResourcenBundle  
   */
  public ResourceBundle getResourceBundle() {
    return resourceBundle;
  }
}
