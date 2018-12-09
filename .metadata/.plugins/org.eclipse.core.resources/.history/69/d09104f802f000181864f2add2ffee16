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
package biz.ritter.develop.cobol.builder;

import biz.ritter.develop.cobol.CorePlugInActivator;
import biz.ritter.develop.cobol.prefs.COBOLPreferenceConstants;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.util.Map;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.ui.console.ConsolePlugin;
import org.eclipse.ui.console.IConsole;
import org.eclipse.ui.console.MessageConsole;
import org.eclipse.ui.console.MessageConsoleStream;

/**
 * Kompiliert das Projekt mit dem GnuCOBOL Compiler.
 * @author Sͬeͥbͭaͭsͤtͬian
 */
public class GnuCOBOLProjectBuilder extends AbstractIncrementalProjectBuilder {
  
  protected String fullQualifiedCobolCompilerExecutable = null;
  protected String cobolCompilerPath = null;
  protected MessageConsoleStream out;
  protected MessageConsole compilerConsole;

  protected void createOutputStream () {
    compilerConsole = new MessageConsole("GnuCOBOL Compiler", null);
    ConsolePlugin.getDefault().getConsoleManager().addConsoles(new IConsole [] {compilerConsole});
    out = compilerConsole.newMessageStream();
  }
  
  @Override
  protected void startupOnInitialize() {
    super.startupOnInitialize();
    try {
      this.createOutputStream();
      String[] natures = this.getProject().getDescription().getNatureIds();
      for (String nature : natures) {
        IStatus natureStatus = ResourcesPlugin.getWorkspace()
            .validateNatureSet(new String[] { nature });
        
        // check the status and decide what to do
        if (IStatus.OK == natureStatus.getSeverity()) {
          
        }
        else {
          out.println("BULLSHIT");
        }
      }
    }
    catch (CoreException shit) {
      out.println(shit.getLocalizedMessage());
    }
  }
  
  @Override
  protected void clean(IProgressMonitor monitor) throws CoreException {
    // TODO Implement own clean method
    out.println(new Exception().getStackTrace()[0].getMethodName());
    super.clean(monitor);
  }
  
  @Override
  protected IProject[] obtainInterestingProjects(int kind,
      Map<String, String> args) {
    // TODO Auto-generated method stub
    if (CorePlugInActivator.getDefault().isDebugging())
      System.out.println(new Exception().getStackTrace()[0].getMethodName());
    return null;
  }
  
  @Override
  protected boolean buildResource(IResource resource, Map<String, String> args,
      IProgressMonitor monitor) {
    
    // TODO Check for exist COBOL compiler
    //out.setColor(Display.getCurrent().getSystemColor(SWT.COLOR_BLACK));
    
    if (null == this.fullQualifiedCobolCompilerExecutable) {
      this.fullQualifiedCobolCompilerExecutable = 
        CorePlugInActivator.getDefault().getPreferenceStore().getString(COBOLPreferenceConstants.CODE_PREF_COBOL_COMPILER_PATH)
       +File.separatorChar
       +CorePlugInActivator.getDefault().getPreferenceStore().getString(COBOLPreferenceConstants.CODE_PREF_COBOL_COMPILER_EXECUTABLE);
      this.cobolCompilerPath = CorePlugInActivator.getDefault().getPreferenceStore().getString(COBOLPreferenceConstants.CODE_PREF_COBOL_COMPILER_PATH);
    }
    
    // FIXME COBOL Source file types intend hard codes extensions
    final String qualifiedPath = resource.getLocation().toOSString();
    if ("cbl".equals(resource.getFileExtension())
        || "cob".equals(resource.getFileExtension())) {
      try {
        final IPath bin = this.getProject().getLocation().append("/bin");
        bin.toFile().mkdirs();
        String params = CorePlugInActivator.getDefault().getPreferenceStore().getString(COBOLPreferenceConstants.CODE_PREF_COBOL_COMPILER_PARAMETERS);
        
        ProcessBuilder pb = null;
        if (null == params || 0 == params.trim().length()) {
          params = "-S";
          CorePlugInActivator.getDefault().getPreferenceStore().setDefault(COBOLPreferenceConstants.CODE_PREF_COBOL_COMPILER_PARAMETERS,params);          
          pb = new ProcessBuilder(
              fullQualifiedCobolCompilerExecutable, 
              params, 
              qualifiedPath,  
              "-o", bin.append(resource.getName().substring(0,resource.getName().indexOf(resource.getFileExtension())-1)+".s").toOSString() );
        }
        else {
          pb = new ProcessBuilder(
              fullQualifiedCobolCompilerExecutable, 
              params, 
              qualifiedPath
          );
        }

        Map<String, String> env = pb.environment();
        final String cobcpy = this.getProject().getLocation().append("src").append("cpy").toOSString(); 
        env.put("COBCPY", env.get("COBCPY")+File.pathSeparatorChar+cobcpy);
        env.put("PATH", env.get("PATH")+File.pathSeparatorChar+this.cobolCompilerPath);
        // BUGFIX: //       pb.directory(new File(this.cobolCompilerPath));  // Permission error if compiler in directory with no user write rights
        // BUGFIX: // pb.directory(null); // writes to .app/MacOS directory
        pb.directory(this.getProject().getLocation().append("/bin").toFile()); // trail and error no. 3
        Process p = pb.start();

        BufferedReader compilerNormalOutput = new BufferedReader (new InputStreamReader(p.getInputStream()));
        BufferedReader compilerErrorOutput = new BufferedReader (new InputStreamReader(p.getErrorStream()));
        final StringBuilder normalOutput = new StringBuilder();
        final StringBuilder errorOutput = new StringBuilder();
        int RC = p.waitFor();
        //RC = p.exitValue();
        String s;
        while ((s = compilerNormalOutput.readLine()) != null) {
          normalOutput.append(s);
        }
        while ((s = compilerErrorOutput.readLine()) != null) {
          errorOutput.append(s).append(System.getProperty("line.separator"));
        }
        if (0 == RC) {
          final String errorLogMessageText = "Compile OK: "
                                           + qualifiedPath
                                           + System.getProperty("line.separator")
                                           + normalOutput.toString().trim();
          //out.setColor(Display.getCurrent().getSystemColor(SWT.COLOR_DARK_GREEN));
          out.println(errorLogMessageText);
        }
        else {
          //out.setColor(Display.getCurrent().getSystemColor(SWT.COLOR_RED));
          out.println(errorOutput.toString());
        }
      }
      catch (Exception shit) {
        CorePlugInActivator.getDefault().getLog().log(new Status(IStatus.ERROR, CorePlugInActivator.PLUGIN_ID, "cannot call cobol compiler for " + qualifiedPath , shit));
        this.fullQualifiedCobolCompilerExecutable = null;
        shit.printStackTrace();
      }
    }
    else if ("cbt".equals(resource.getFileExtension())) {
    		new GnuCOBOLTargetBuilder(getProject(), out).buildResource(resource, args, monitor);
    }
    else {
      // Nothing to do
      //System.out.println("call NO cobol compiler for " + qualifiedPath + " in [" + this.getClass().toString() + "]");
    }
    
    return true;
  }
  
}
