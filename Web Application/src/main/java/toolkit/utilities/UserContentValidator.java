package toolkit.utilities;

/**
 * Created by James Euesden on 07/04/2016.
 *
 * Utility methods for checking the validity of a users input content.
 * A user can mostly input anything they want, they just won't get results from bad data. Informing the user they have
 * bad data has not been dealt with though, as it is possible that the bad data they have is just what their assembler
 * gave them, in this instance, the fact they won't get results should pose to show their broken data.
 */
public class UserContentValidator {

    /**
     * Simple method checking that the users content is not null or empty, and that their first line (the header of
     * the FASTA file), starts with the correct delimiter. Room for expansion to more validation methods if required
     * in the future.
     * @param content The users pasted content.
     * @return The result of if the users content matches the expected format.
     */
    public boolean validateUserContent(String content){
            if(content == null || content.equals("")){
                return false;
            }
            if(!content.startsWith(">")){
                return false;
            }
        return true;
    }
}
