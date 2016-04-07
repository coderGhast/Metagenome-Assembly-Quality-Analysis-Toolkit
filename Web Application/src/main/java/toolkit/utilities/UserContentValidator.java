package toolkit.utilities;

/**
 * Created by James Euesden on 07/04/2016.
 */
public class UserContentValidator {

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
