package toolkit.domain;

/**
 * Created by James Euesden on 22/03/2016.
 */
public class Codon {
    private String _codonCharacters;
    private int _contigStartIndex;

    public Codon(String codonCharacters, int contigStartIndex){
        _codonCharacters = codonCharacters;
        _contigStartIndex = contigStartIndex;
    }

    public int getContigStartIndex() {
        return _contigStartIndex;
    }

    public String get_codonCharacters() {
        return _codonCharacters;
    }
}
