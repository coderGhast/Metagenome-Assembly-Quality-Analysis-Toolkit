package toolkit.domain;

/**
 * Created by James Euesden on 22/03/2016.
 *
 * Represents a Codon within the contiguous read. A Codon, for this application) is either a start (ATG)
 * or a stop (TGA, TAG, TAA). The Codon object is used to hold the data for the characters of the Codon
 * and where it lies within the contiguous read.
 */
public class Codon {
    private String _codonCharacters;
    private int _contigStartIndex;

    /**
     * A Codons fields are set on creation, as they will never change during its lifespan.
     * @param codonCharacters The characters of the Codon, ATG, TGA, TAA or TAG.
     * @param contigStartIndex The start index of the Codon in the contiguous read String.
     */
    public Codon(String codonCharacters, int contigStartIndex){
        _codonCharacters = codonCharacters;
        _contigStartIndex = contigStartIndex;
    }

    /**
     * Returns the start index of the Codon within the contiguous read.
     * @return The start index in the contiguous read.
     */
    public int getContigStartIndex() {
        return _contigStartIndex;
    }

    /**
     * Returns the character of the Codon (ATG, TGA, TAA, TAG)
     * @return The characters of the Codon.
     */
    public String getCodonCharacters() {
        return _codonCharacters;
    }
}
