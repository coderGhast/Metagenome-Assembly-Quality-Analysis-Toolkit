package toolkit.utilities;

/**
 * Created by James Euesden on 30/03/2016.
 */
public class SequenceUtilities {
    public static String getReverseSequence(String contig){
        StringBuilder reverseContig = new StringBuilder();
        int i = contig.length() - 1;
        while(i >= 0){
            switch(contig.toLowerCase().charAt(i)){
                case('t') : reverseContig.append('A');
                    break;
                case('a') : reverseContig.append('T');
                    break;
                case('g') : reverseContig.append('C');
                    break;
                case('c') : reverseContig.append('G');
                    break;
                default : reverseContig.append('N');
                    break;
            }
            i--;
        }

        return reverseContig.toString();
    }
}
