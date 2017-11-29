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
package biz.ritter.develop.cobol.builder;

import java.util.Map;

import org.eclipse.core.resources.ICommand;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.core.resources.IResourceDeltaVisitor;
import org.eclipse.core.resources.IResourceVisitor;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;

/**
 *
 * @author Sͬeͥbͭaͭsͤtͬian
 */
public abstract class AbstractIncrementalProjectBuilder extends
    org.eclipse.core.resources.IncrementalProjectBuilder {
  
  /*
   * (non-Javadoc)
   * 
   * @see
   * org.eclipse.core.resources.IncrementalProjectBuilder
   * #build(int, java.util.Map,
   * org.eclipse.core.runtime.IProgressMonitor)
   */
  @SuppressWarnings({ "rawtypes", "unchecked" })
  @Override
  protected IProject[] build(final int kind, final Map args,
      final IProgressMonitor monitor) throws CoreException {
    final IProject project = getProject();
    switch (kind) {
    case FULL_BUILD:
      project.accept(new IResourceVisitor() {
        @Override
        public boolean visit(IResource resource) throws CoreException {
          return buildResource(resource, args, monitor);
        }
        
      });
      break;
    case INCREMENTAL_BUILD:
    case AUTO_BUILD:
    case CLEAN_BUILD:
    default:
      final IResourceDelta delta = getDelta(project);
      delta.accept(new IResourceDeltaVisitor() {
        
        @Override
        public boolean visit(IResourceDelta delta) throws CoreException {
          return buildResourceDelta(delta, kind, args, monitor);
        }
      });
    }
    return obtainInterestingProjects(kind, args);
  }
  
  protected abstract IProject[] obtainInterestingProjects(int kind,
      Map<String, String> args);
  
  protected boolean buildResourceDelta(IResourceDelta delta, int kind,
      Map<String, String> args, IProgressMonitor monitor) {
    final IResource resource = delta.getResource();
    return this.buildResource(resource, args, monitor);
  }
  
  protected abstract boolean buildResource(IResource resource,
      Map<String, String> args, IProgressMonitor monitor);
  
  public static void addBuilder(final IProject project, final String builderID,
      final boolean before, final IProgressMonitor monitor)
      throws CoreException {
    final IProjectDescription desc = project.getDescription();
    // Check builder added some times before?
    for (ICommand builder : desc.getBuildSpec()) {
      if (builder.getBuilderName().equals(builderID)) return;
    }
    // if no then add
    final ICommand[] commands = new ICommand[desc.getBuildSpec().length + 1];
    ICommand builder = desc.newCommand();
    builder.setBuilderName(builderID);
    if (before) {
      System.arraycopy(desc.getBuildSpec(), 0, commands, 1,
          desc.getBuildSpec().length);
      commands[0] = builder;
    }
    else {
      System.arraycopy(desc.getBuildSpec(), 0, commands, 0,
          desc.getBuildSpec().length);
      commands[commands.length - 1] = builder;
    }
    desc.setBuildSpec(commands);
    project.setDescription(desc, monitor);
  }
  
  public static void removeBuilder(final IProject project,
      final String builderID, final boolean before,
      final IProgressMonitor monitor) throws CoreException {
    final IProjectDescription desc = project.getDescription();
    final ICommand[] commands = new ICommand[desc.getBuildSpec().length - 1];
    int pos = -1;
    // Remove builder
    for (int i = 0; i < desc.getBuildSpec().length; i++) {
      if (desc.getBuildSpec()[i].getBuilderName().equals(builderID)) {
        pos = i;
      }
      else {
        if (i < commands.length) commands[i] = desc.getBuildSpec()[i];
      }
    }
    if (-1 != pos) {
      desc.setBuildSpec(commands);
      project.setDescription(desc, monitor);
    }
  }
  
  
  
}
