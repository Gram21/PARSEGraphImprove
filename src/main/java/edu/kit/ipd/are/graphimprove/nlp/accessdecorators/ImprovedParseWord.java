package edu.kit.ipd.are.graphimprove.nlp.accessdecorators;

import java.util.Optional;

import edu.kit.ipd.are.archdoclink.nlp.Word;
import edu.kit.ipd.are.archdoclink.nlp.parse.ParseWord;
import edu.kit.ipd.are.graphimprove.nlp.helper.Filter;
import edu.kit.ipd.parse.luna.graph.INode;


/**
 *  Implement the access of token INode, which was generated by the PARSE
 * 
 * @author dominik
 *
 */
public class ImprovedParseWord extends ParseWord {	
	
	private static String NEXT_WORD_ARC_TYPE = "relation";
	
	private INode node;


	public ImprovedParseWord(INode node){	
		super(node);
		this.node = node;
	}
	
	public Optional<? extends Word> nextWord(){
		return Filter.outgoing(this.node, NEXT_WORD_ARC_TYPE).map(
				(node) -> new ImprovedParseWord(node)
				).findFirst();
	}
	
	public Optional<? extends Word> previousWord(){
		return Filter.incomming(this.node, NEXT_WORD_ARC_TYPE).map(
				(node) -> new ImprovedParseWord(node)
				).findFirst();		
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
            return false;
        }
        if (!(obj instanceof ImprovedParseWord)) {
            return false;
        }
        ImprovedParseWord other = (ImprovedParseWord) obj;
        if (node == null) {
            if (other.node != null) {
                return false;
            }
        } else if (!node.equals(other.node)) {
            return false;
        }
        return true;
	}
	
	
	
	

}