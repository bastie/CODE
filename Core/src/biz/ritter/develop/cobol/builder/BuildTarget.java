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

import java.util.Iterator;
import java.util.LinkedList;

/**
 * @author Sͬeͥbͭaͭsͤtͬian
 *
 */
public class BuildTarget {

	/**
	 * Unterstützte build types für COBOL
	 * @author Sͬeͥbͭaͭsͤtͬian
	 *
	 */
	public enum TargetTypes {
		/**
		 * Spezifiziert, dass eine ausführbare Datei erstellt werden soll
		 */
		EXECUTABLE, 
		/**
		 * Spezifiziert, dass eine dynamische Bibliothek erstellt werden soll
		 */
		LIBRARY
	}
	
	/**
	 * Eine Liste der Programme (Cobolquellen) die berücksichtigt werden sollen
	 */
	private LinkedList<String> buildParts = new LinkedList<>();
	
	/**
	 * Der Name des zu erstellenden Targets.
	 * Die Berücksichtigung ist builderspezifisch. 
	 */
	private String targetName = null;
	
	/**
	 * Erzeugt ein neues BuildTarget Objekts
	 * @pattern FactoryMethod
	 * @param targetType Ergebnistyp des Buildprozesses
	 * @return BuildTarget instance
	 */
	public static BuildTarget newInstance (String targetType) {
		BuildTarget instance = new BuildTarget();
		instance.setTargetType (targetType);
		return instance;
	}

	/**
	 * Mit dem Target Tye wird angegeben, welcher Dateityp gebaut werden soll.
	 */
	private TargetTypes type = TargetTypes.LIBRARY;
	
	/**
	 * Setzt einen neuen Zieltyp
	 * @param newTargetType der Typ der gebaut werden soll
	 * @see TargetTypes
	 */
	public void setTargetType (final String newTargetType) {
		this.type = TargetTypes.valueOf(newTargetType);
	}
	
	/**
	 * Liefert den Zieltyp zurück
	 * @return Zieltyp des Erstellungsprozesses
	 * @see TargetTypes
	 */
	public String getTargetType () {
		return this.type.name();
	}

	/**
	 * Fügt eine Datei dem Erstellungsprozess hinzu
	 * @param part Datei
	 */
	public void addPart(String part) {
		if (null == part) return; // @pattern NullPattern
		// beim Erstellprozess m�ssen die Entrypointsbezeichnung eineindeutig sein
		// auch wenn bei einer executable der main entry point zus�tzlich zum "part"
		// hinzugef�gt wird, ist dies unsauber und wird mal einfach hier verhindert
		synchronized (this.buildParts) {
			if (!buildParts.contains(part)) {
				this.buildParts.offerLast(part);
			}
		}	
	}
	
	/**
	 * Fügt eine Datei dem Erstellungsprozess an einer definierten Stelle hinzu
	 * @param part
	 * @param index
	 */
	public void addPart(int index, String part) {
		// beim Erstellprozess müssen die Entrypointsbezeichnung eineindeutig sein
		// auch wenn bei einer executable der main entry point zusätzlich zum "part"
		// hinzugefügt wird, ist dies unsauber und wird mal einfach hier verhindert
		synchronized (this.buildParts) {
			if (!buildParts.contains(part)) {
				this.buildParts.offerLast(part);
			}
		}	
	}
	
	/**
	 * Setzt den MainEntryPoint, das erste Programm in der Liste der 
	 * bei diesem Buildtarget zu berücksichtigenden Dateien
	 * @param part
	 */
	public void setMainEntryPart (String part) {
		if (buildParts.size()>0) {
			synchronized (this.buildParts) {
				// wir wollen keine Doppel hier haben
				if (buildParts.contains(part)) {
					this.buildParts.remove(part);
				}
				this.buildParts.offerFirst(part);
			}
		}
	}
	
	/**
	 * Liefert die Liste der zu berücksichtigenden Programme
	 * @return
	 */
	public Iterator<String> getBuildParts () {
		return this.buildParts.iterator();
	}
	
	
	public String getTargetName () {
		return this.targetName;
	}
	
	public void setTargetName (String newTargetName) {
		this.targetName = newTargetName;
	}
}
