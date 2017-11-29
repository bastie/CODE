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
package biz.ritter.develop.cobol.builder.io;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Iterator;

import biz.ritter.develop.cobol.builder.BuildTarget;

/**
 * Der Writer ermöglicht aus einem vorhandenen BuildTarget eine
 * Beschreibungsdatei im CPY Format zu erstellen.
 * 
 * @author Sͬeͥbͭaͭsͤtͬian
 *
 */
public class BuildTargetCpyWriter extends BufferedWriter{
	
	private final Writer delegate;
	private boolean withComments = false;
	
	/**
	 * Erzeugt eine neue Instanz
	 * @param writer der Writer in den wir unsere Ausgaben machen
	 */
	public BuildTargetCpyWriter(final Writer writer) {
		super(writer);
		this.delegate = null == writer ? new StringWriter() : writer;
		this.setWriteWithComments(false);
	}
	
	/**
	 * Schreibt die übergebene BuildTarget Beschreibung in den Stream
	 * @param desc BuildTarget Beschreibung
	 * @return self
	 * @throws IOException
	 */
	public BuildTargetCpyWriter write (BuildTarget desc) throws IOException {
		// stoppe Multithreadingzugriff, um mit withComments sauber zu arbeiten
		synchronized (this) {
			// Kommentar zur Datei
			if (withComments) {
				this.write("      * BuildTarget "+desc.getTargetName());
				this.newLine();
				this.write("      *");
				this.newLine();
			}
			
			// Der Typ des zu bauenden Objektes
			if (withComments) {
				this.write("      * Target type (T) request can be EXECUTABLE or LIBRARY");
				this.newLine();
			}
			this.write("      T="+desc.getTargetType());
			this.newLine();

			// Der Name des zu bauenden Objektes
			if (withComments) {
				this.newLine();
				this.write("      * Name (N) of needed target");
				this.newLine();
			}
			this.write("      N="+desc.getTargetName());
			this.newLine();
			
			// Die zugeh�rigen Module des zu bauendesn Objektes
			if (withComments) {
				this.newLine();
				this.write("      * All needed sources for target");
				this.newLine();
			}
			
			Iterator<String> moduln = desc.getBuildParts();
			while (moduln.hasNext()) {
				this.write("       "+moduln.next());
				this.newLine();
			}
			
			// EOF erreicht
			if (withComments) {
				this.newLine();
				this.write("      * EOF");
				this.newLine();
			}
			
		}
		return this; // @pattern FluentApiPattern
	}
	
	/**
	 * Erweitert den ausgegebenen Zeichenstrom um erklärende Kommentare
	 * @param iWantComments Kommentare sollen geschrieben werden oder auch nicht
	 * @return self
	 */
	public BuildTargetCpyWriter setWriteWithComments (boolean iWantComments) {
		this.withComments = iWantComments;
		return this; // @pattern FluentApiPattern
	}


	@Override
	public void write(char[] arg0, int arg1, int arg2) throws IOException {
		this.delegate.write(arg0, arg1, arg2);
	}

}
