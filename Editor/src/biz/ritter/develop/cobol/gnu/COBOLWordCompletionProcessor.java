/**
 * Copyright 2010 Sebastian Ritter
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
package biz.ritter.develop.cobol.gnu;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;

import org.eclipse.jface.text.ITextViewer;
import org.eclipse.jface.text.contentassist.*;

/**
 * COBOLWordCompletionProcessor implements content assist. Press CTRL+SPACE.
 * @author Sͬeͥbͭaͭsͤtͬian
 */
public class COBOLWordCompletionProcessor implements IContentAssistProcessor {
  
  private static HashMap<String, String> displayReplacementContent;
  
  public COBOLWordCompletionProcessor() {
    createFullDisplayReplacemnetContent();
  }
  
  public void createFullDisplayReplacemnetContent() {
    
    displayReplacementContent = new HashMap<String, String>();

    for (int i = 0; i < KeywordScanner.KEYWORDS.length; i++) {
      displayReplacementContent.put(KeywordScanner.KEYWORDS[i],KeywordScanner.KEYWORDS[i]);
    }
    for (int i = 0; i < KeywordScanner.MNEMONIC.length; i++) {
      displayReplacementContent.put(KeywordScanner.MNEMONIC[i],KeywordScanner.MNEMONIC[i]);
    }
  }
  
  
  /*
   * (non-Javadoc)
   * @see org.eclipse.jface.text.contentassist.IContentAssistProcessor#computeCompletionProposals(org.eclipse.jface.text.ITextViewer, int)
   * 
   * Create a sorted CONTENT assist
   */
  public ICompletionProposal[] computeCompletionProposals(
      final ITextViewer viewer, final int documentOffset) {
    ArrayList<CompletionProposal> proposal = new ArrayList<CompletionProposal>();
    // Sort display
    LinkedList<String> ll = new LinkedList<String>(displayReplacementContent.keySet());
    Collections.sort(ll);
    // Create completion proposal collection
    for (String toDisplay : ll) {
      IContextInformation info = new ContextInformation(toDisplay, toDisplay);
      CompletionProposal complete = new CompletionProposal(
          displayReplacementContent.get(toDisplay), documentOffset, 0,
          displayReplacementContent.get(toDisplay).length(), null, toDisplay,
          info, "");
      proposal.add(complete);
    }
    ICompletionProposal[] result = new ICompletionProposal[proposal.size()];
    result = proposal.toArray(result);
    return result;
  }
  
  public IContextInformation[] computeContextInformation(
      final ITextViewer viewer, final int documentOffset) {
    // no context infopops
    return null;
  }
  
  public char[] getCompletionProposalAutoActivationCharacters() {
    return new char[0];
  }
  
  public char[] getContextInformationAutoActivationCharacters() {
    return new char[0];
  }
  
  public String getErrorMessage() {
    return null;
  }
  
  public IContextInformationValidator getContextInformationValidator() {
    return null;
  }
}
