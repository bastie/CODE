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

import java.net.URI;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.Assert;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;

import biz.ritter.develop.cobol.builder.AbstractIncrementalProjectBuilder;
import biz.ritter.develop.cobol.internal.CODEConstants;

/**
 * @author Sͬeͥbͭaͭsͤtͬian
 * @see http://cvalcarcel.wordpress.com/2009/07/26/writing-an-eclipse-plug-in-part-4-create-a-custom-project-in-eclipse-new-project-wizard-the-behavior/
 */
public class COBOLProjectSupport {
  
  /**
   * Erzeugt ein neues Projekt, fügt die COBOL Nature hinzu und legt die 
   * Projektstruktur an.
   * @param projectName Name des Projektes
   * @param location der Ablageort des Projektes 
   * @return das Projekt
   */
  public static IProject createProject(String projectName, URI location) {
    Assert.isNotNull(projectName);
    Assert.isTrue(projectName.trim().length() > 0);
    
    IProject project = createBaseProject(projectName, location);
    try {
      // add default source paths
      String[] paths = { "src/cpy", "src/cbl" }; //$NON-NLS-1$ //$NON-NLS-2$
      addToProjectStructure(project, paths);
      
      // add CODE Builder
      addBuilder(project, CODEConstants.CODE_FULLQUALIFIED_BUILDER_ID);
      // add CODE Nature
      addNature(project, CODEConstants.CODE_FULLQUALIFIED_NATURE_ID);
    }
    catch (CoreException e) {
      e.printStackTrace();
      project = null;
    }
    
    return project;
  }
  
  /**
   * Fügt einen neuen Builder dem Projekt hinzu.
   * @param project
   * @param codeBuilderId
   * @throws CoreException
   */
  private static void addBuilder(IProject project, String codeBuilderId)
      throws CoreException {
//    System.out.println(new Exception().getStackTrace()[0].getMethodName());
    AbstractIncrementalProjectBuilder.addBuilder(project,
        CODEConstants.CODE_FULLQUALIFIED_BUILDER_ID, true, null);
  }
  
  /**
   * Erzeugt das Grundgerüsst unseres Projektes
   * 
   * @param location
   * @param projectName
   */
  private static IProject createBaseProject(String projectName, URI location) {
    // schauen wir mal, ob das Projekt schon existiert
    IProject newProject = ResourcesPlugin.getWorkspace().getRoot()
        .getProject(projectName);
    
    if (!newProject.exists()) {
      URI projectLocation = location;
      IProjectDescription desc = newProject.getWorkspace()
          .newProjectDescription(newProject.getName());
      if (location != null
          && ResourcesPlugin.getWorkspace().getRoot().getLocationURI()
              .equals(location)) {
        projectLocation = null;
      }
      
      desc.setLocationURI(projectLocation);
      try {
        newProject.create(desc, null);
        if (!newProject.isOpen()) {
          newProject.open(null);
        }
      }
      catch (CoreException e) {
        e.printStackTrace();
      }
    }
    
    return newProject;
  }
  
  /**
   * Erzeugt einen neuen Ordner
   * @param folder Ordner
   * @throws CoreException
   */
  private static void createFolder(IFolder folder) throws CoreException {
    IContainer parent = folder.getParent();
    if (parent instanceof IFolder) {
      createFolder((IFolder) parent);
    }
    if (!folder.exists()) {
      folder.create(false, true, null);
    }
  }
  
  /**
   * Erzeugt eine neue Ordnerstruktur.
   * 
   * @param newProject
   * @param paths
   * @throws CoreException
   */
  private static void addToProjectStructure(IProject newProject, String[] paths)
      throws CoreException {
    for (String path : paths) {
      IFolder etcFolders = newProject.getFolder(path);
      createFolder(etcFolders);
    }
  }
  
  /**
   * Fügt die Nature dem Projekt hinzu
   * @param project
   * @param natureID
   * @throws CoreException
   */
  private static void addNature(IProject project, final String natureID)
      throws CoreException {
    if (!project.hasNature(natureID)) {
      IProjectDescription description = project.getDescription();
      String[] prevNatures = description.getNatureIds();
      String[] newNatures = new String[prevNatures.length + 1];
      System.arraycopy(prevNatures, 0, newNatures, 0, prevNatures.length);
      newNatures[prevNatures.length] = natureID;
      description.setNatureIds(newNatures);
      
      IProgressMonitor monitor = null;
      project
          .setDescription(description, IProject.AVOID_NATURE_CONFIG, monitor);
    }
  }
}
