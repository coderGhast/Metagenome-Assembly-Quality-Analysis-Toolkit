package toolkit.domain;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created by James Euesden on 09/03/2016.
 *
 * Used by the Controller and View to contain the users data about what they wish to inspect and any additional
 * parameters for that initial data.
 */
public class UserParameters {
    @NotNull
    @Size(min = 1)
    private String _userContent = "";
    private String _fileName = "";
    @NotNull
    @Min(0)
    private int _contigLengthThreshold = 1000;

    /**
     * Returns the users input content (from the paste box).
     * @return The users input content (from the paste box).
     */
    public String getUserContent(){ return _userContent; }

    /**
     * Sets the users input from the View/paste box to be used later.
     * @param userContent the users input from the View/paste box to be used later.
     */
    public void setUserContent(String userContent){
        this._userContent = userContent;
    }

    /**
     * Returns the minimum length of a contiguous read to be considered in the application, as set by the user.
     * @return The minimum length of a contiguous read to be considered in the application, as set by the user.
     */
    public int getContigLengthThreshold() {
        return _contigLengthThreshold;
    }

    /**
     * Sets the minimum length a contiguous read can be in order to be considered.
     * @param contigLengthThreshold The minimum length a contiguous read can be in order to be considered.
     */
    public void setContigLengthThreshold(int contigLengthThreshold) {
        this._contigLengthThreshold = contigLengthThreshold;
    }

    /**
     * Returns a file name as input by a user. NOTE: Currently used for the version of the application with test
     * files, and for future release with allowing user uploads.
     * @return The file name input by a user.
     */
    public String getFileName() {
        return _fileName;
    }

    /**
     * Sets the name of the file the user wishes to carry out an inspection upon with this application.
     * @param fileName The name of the file the user wishes to carry out an inspection upon.
     */
    public void setFileName(String fileName) {
        this._fileName = fileName;
    }
}
