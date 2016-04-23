package toolkit.utilities;

/**
 * Created by James Euesden on 30/03/2016.
 *
 * A class used to house utility methods for use with sequences.
 */
public class SequenceUtilities {

    /**
     * Returns the reverse base pair sequences of a provided sequence of characters.
     * @param sequence The sequence to return the reverse base pairs of.
     * @return The reversed base pairs of the originally provided sequence.
     */
    public static String getReverseSequence(String sequence){
        StringBuilder reverseSequence = new StringBuilder();
        int i = sequence.length() - 1;
        while(i >= 0){
            switch(sequence.toLowerCase().charAt(i)){
                case('t') : reverseSequence.append('A');
                    break;
                case('a') : reverseSequence.append('T');
                    break;
                case('g') : reverseSequence.append('C');
                    break;
                case('c') : reverseSequence.append('G');
                    break;
                default : reverseSequence.append('N');
                    break;
            }
            i--;
        }

        return reverseSequence.toString();
    }
}
