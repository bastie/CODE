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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;

import biz.ritter.develop.cobol.builder.BuildTarget;

/**
 * Die BuildTarget eines COBOL Projektes kennzeichnen sich durch:
 * <ul><li>Information, des zu erstellenden Dateityp</li>
 * <li>die einzubindenen Quellen (und Ressourcen)<ul><li>mit Informationen zu den Buildparametern (wie Compilerschalter)</li></ul>
 * </li>
 * </ul>
 * 
 * @author Sͬeͥbͭaͭsͤtͬian
 *
 */
public class BuildTargetCpyReader extends BufferedReader {
	
	@SuppressWarnings("unused")
	private BuildTargetCpyReader () {
		this (new StringReader(""));
	}
	
	public BuildTargetCpyReader (final Reader src) {
		super (src,160);
	}
	
// TODO: Abbildung in der Baumstruktur... (Drag und Drop der Quellen => Übersicht bei Exe welche Source enthalten und parallel bei den Quellen Anzeige welche Exes es enthalten)
// TODO: Wizard erstellen um ein neues Programm / Library zu erstellen
	/**
	 * Aufbau des COBOL Formates:
	 * <pre>
	 * 01 COBOL-FORMAT                  PIC X(80).
	 *   05 SEQUENCE-NUMBER               PIC X(6).
	 *   05 INDICATOR-AREA                PIC X.
	 *     88 INDICATOR-COMMENT             VALUE '*'.
	 *     88 TARGET-NAME                   VALUE 'N'.
	 *     88 TARGET-TYPE                   VALUE 'T'.
	 *     88 TARGET-CONTENT                VALUE 'C', ' '.
	 *   05 PROGRAM-TEXT                  PIC X(65).
	 *   05 REFERENCE-AREA                PIC X(8).
	 * 01 FILLER REDEFINES COBOL-FORMAT.
	 *   05 SEQUENCE-NUMBER               PIC X(6).
	 *   05 ACCEPTED-TARGET-TYPES         PIC X(74).
	 *     88 TARGET-TYPE-EXECUTABLE        VALUE 'T=EXECUTABLE'.
	 *     88 TARGET-TYPE=LIBRARY           VALUE 'T=LIBRARY'.
	 *   
	 * </pre> 
	 * Beispiel:
	 * <pre>
	 * 100000* Beispiel COBOL Format
	 * 100010*
	 * 100200* Es soll eine Ausführbare Datei erstellt werden
	 * 100201
	 * 100210T=EXECUTABLE
	 * 100211
	 * 100300* Der Name der Ausgabe soll 'Hello World' sein 
	 * 100301* (führende und abschließende Leerzeichen werden entfernt).
	 * 100302
	 * 100310N Hello World
	 * 100311
	 * 100312
	 * 100400* Der Quelltext ist in den Dateien Hello.cbl und World.cob zu finden.
	 * 100401* (führende und abschließende Leerzeichen werden entfernt), wobei
	 * 100402* das erste Programm den Einstiegspunkt darstellt ("main")
	 * 100410 Hello.cbl
	 * 100420   World.cob
	 * 100421
	 * 100500* Der zugehörige Compileraufruf bei
	 * 100501* GnuCOBOL executable: cobc -x -o HelloWorld.exe Hello.cbl World.cob
	 * 100502* GnuCOBOL libraries:  cobc -m Hello.cbl World.cbl
	 * 100600*END
	 * </pre>
	 */
	public BuildTarget readBuildTarget () {
		BuildTarget result = null;
		try {
			final int COBOL_FORMAT_IDENTIFIER_INDEX = 6; // pos = 7
			final char COBOL_FORMAT_COMMENT_LINE_IDENTIFIER = '*';
			final char COBOL_FORMAT_TARGET_TYPE_IDENTIFIER = 'T';
			final char COBOL_FORMAT_NAME_IDENTIFIER = 'N';
			String s = null;
			while (null != (s = this.readLine())) {
				if (s.length()>7) {
					switch (s.charAt(COBOL_FORMAT_IDENTIFIER_INDEX)) {
					case COBOL_FORMAT_COMMENT_LINE_IDENTIFIER:
						break;
					case COBOL_FORMAT_NAME_IDENTIFIER:
						result.setTargetName(s.substring(COBOL_FORMAT_IDENTIFIER_INDEX+1).trim());
						break;
					case COBOL_FORMAT_TARGET_TYPE_IDENTIFIER:
						try {
							final String EXPECTED_TARGET_TYPE = s.substring(COBOL_FORMAT_IDENTIFIER_INDEX+2).trim();
							result = BuildTarget.newInstance(EXPECTED_TARGET_TYPE);
						}
						catch (ArrayIndexOutOfBoundsException | IllegalArgumentException iKnowYouYouNeedAnHeavyKnockOnYourHeadToWriteCorrectBuildTargetDefinitions) {
						  // FIXME use CoreException
							throw new IllegalArgumentException("Expected target type unknown.");
						}
						break;
					case ' ':
						final String part = s.substring(COBOL_FORMAT_IDENTIFIER_INDEX+1).trim();
						result.addPart (null == part ? "" : part);
					default:
						// ignoring unsupported 
						break;
					}
				}
			}
		}
		catch (IOException nothingToDoYippi) {
			nothingToDoYippi.printStackTrace();
		}
		return result;
	}
	
	
	@Override
	public void close() throws IOException {
		super.close();
	}
	@Override
	public int read(char[] arg0, int arg1, int arg2) throws IOException {
		return super.read(arg0, arg1, arg2);
	}
	
}
