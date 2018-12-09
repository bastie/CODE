/**
 * Copyright 2017 Sebastian Ritter
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

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.ui.console.MessageConsole;
import org.eclipse.ui.console.MessageConsoleStream;

import biz.ritter.develop.cobol.CorePlugInActivator;
import biz.ritter.develop.cobol.builder.BuildTarget.TargetTypes;
import biz.ritter.develop.cobol.builder.io.BuildTargetCpyReader;
import biz.ritter.develop.cobol.prefs.COBOLPreferenceConstants;

/**
 * @author Sͬeͥbͭaͭsͤtͬian
 *
 */
public class GnuCOBOLTargetBuilder implements BuilderSAM {
	
	GnuCOBOLTargetBuilder(IProject theProject, MessageConsoleStream console) {
		this.project = theProject;
		out = console;
	}
	  protected MessageConsoleStream out;
	  protected MessageConsole compilerConsole;
	  private final IProject project;
	  private IProject getProject () {
		  return this.project;
	  }

	private List<String> createCommandLine (final BuildTarget target) {
		LinkedList<String> result = new LinkedList<>();
		
		
		IPreferenceStore prefs = CorePlugInActivator.getDefault().getPreferenceStore();
		String cobc = prefs.getString(COBOLPreferenceConstants.CODE_PREF_COBOL_COMPILER_PATH);
		cobc += File.separator;
		cobc += prefs.getDefaultString(COBOLPreferenceConstants.CODE_PREF_COBOL_COMPILER_EXECUTABLE);
		
		if (Files.notExists(new File(cobc).toPath(), LinkOption.NOFOLLOW_LINKS)) {
			this.out.println("Compiler "+cobc+" doesn't exist. Change value in COBOL preferences pages!");
		}
		
		result.add(cobc);
		result.add( target.getTargetType().equals(TargetTypes.EXECUTABLE.name()) ? "-x" : "-m");
		if (target.getTargetType().equals(TargetTypes.EXECUTABLE.name())) {
			result.add("-o");
			if (null != target.getTargetName() && target.getTargetName().trim().length() >0) {
					result.add(target.getTargetName());
			}			
			else {
				result.add("CODE.build.result");
			}
		}
		Iterator<String> toInclude = target.getBuildParts();
		while (toInclude.hasNext()) {
			final String next = toInclude.next().trim();
			if (next.length()>0) {
			result.add(toInclude.next());
			}
		}
		
		String [] returnValue = new String [result.size()];
		for (int i = 0; i < result.size(); i++) returnValue[i] = result.get(i);
		
	
		return result;
	}

	public boolean buildResource(IResource resource, Map<String, String> args, IProgressMonitor monitor) {
	    // FIXME COBOL Source file types intend hard codes extensions
	    final String qualifiedPath = resource.getLocation().toOSString();
	    if ("cbt".equals(resource.getFileExtension())) {
	      try {
	        final IPath bin = this.getProject().getLocation().append("/bin");
	        bin.toFile().mkdirs();
	        //String params = CorePlugInActivator.getDefault().getPreferenceStore().getString(COBOLPreferenceConstants.CODE_PREF_COBOL_COMPILER_PARAMETERS);
	        
	        BuildTargetCpyReader reader = new BuildTargetCpyReader(new FileReader(qualifiedPath));
	        BuildTarget target = reader.readBuildTarget();
//	        ProcessBuilder pb = new ProcessBuilder(this.createCommandLine(target).substring(0,this.createCommandLine(target).indexOf(" ")),"-v",this.createCommandLine(target).substring(this.createCommandLine(target).indexOf(" ")) );
	        ProcessBuilder pb = new ProcessBuilder(this.createCommandLine(target));
	    //    pb = new ProcessBuilder(this.getOtherCommandLine(target).substring(0,this.getOtherCommandLine(target).indexOf(" ")),"-vvv", "-x", "-o", "Hello World",   "/Users/cobol/cobol.test/Hello.cbl", "/Users/cobol/cobol.test/World.cob");
	        
	        Map<String, String> env = pb.environment();
	        final String cobcpy = this.getProject().getLocation().append("src").append("cpy").toOSString(); 
	        env.put("COBCPY", env.get("COBCPY")+File.pathSeparatorChar+cobcpy);
	        // BUGFIX: //       pb.directory(new File(this.cobolCompilerPath));  // Permission error if compiler in directory with no user write rights
	        // BUGFIX: // pb.directory(null); // writes to .app/MacOS directory
	        pb.directory(this.getProject().getLocation().append("/bin").toFile()); // trail and error no. 3
	        if (System.getProperty("os.name").toLowerCase().indexOf("mac")> -1) {
	        		env.put("PATH", env.get("PATH")+File.pathSeparator+"/usr/local/bin");///:usr/bin:/bin:/usr/sbin:/sbin");
	        }
	        else {
	        	  //TODO other os other params?
	        }
	        Process p = pb.start();
	
	        BufferedReader compilerNormalOutput = new BufferedReader (new InputStreamReader(p.getInputStream()));
	        BufferedReader compilerErrorOutput = new BufferedReader (new InputStreamReader(p.getErrorStream()));
	        final StringBuilder normalOutput = new StringBuilder();
	        final StringBuilder errorOutput = new StringBuilder();
	        int RC = p.waitFor();
	        //RC = p.exitValue();
	        String s;
	        while ((s = compilerNormalOutput.readLine()) != null) {
	          normalOutput.append(s).append(System.getProperty("line.separator"));
	        }
	        while ((s = compilerErrorOutput.readLine()) != null) {
	          errorOutput.append(s).append(System.getProperty("line.separator"));
	        }
	        if (0 == RC) {
	          final String errorLogMessageText = "Target OK: "
	                                           + qualifiedPath
	                                           + System.getProperty("line.separator")
	                                           + normalOutput.toString().trim();
	          //out.setColor(Display.getCurrent().getSystemColor(SWT.COLOR_DARK_GREEN));
	          out.println(errorLogMessageText);
	        }
	        else {
	          //out.setColor(Display.getCurrent().getSystemColor(SWT.COLOR_RED));
	        	  out.println(normalOutput.toString().trim());
	          out.println(errorOutput.toString());
	        }
	        reader.close();
	      }
	      catch (Exception shit) {
	        CorePlugInActivator.getDefault().getLog().log(new Status(IStatus.ERROR, CorePlugInActivator.PLUGIN_ID, "cannot call cobol compiler for " + qualifiedPath , shit));
	        shit.printStackTrace();
	      }
	    }
	    else {
	      // Nothing to do
	      //System.out.println("call NO cobol compiler for " + qualifiedPath + " in [" + this.getClass().toString() + "]");
	    }
	    
	    return true;
	  }

}
