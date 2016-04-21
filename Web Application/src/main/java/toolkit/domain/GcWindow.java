package toolkit.domain;

/**
 * Created by James Euesden on 2/15/2016.
 *
 * Represents a window of the contiguous read for use in the GC Content calculation.
 */
public class GcWindow {
    private String windowContent;
    private int gCount;
    private int cCount;

    /**
     * Initialized with the characters of the particular window this GcWindow represents. Calculates the G and C counts
     * as soon as the window is created. As the window content won't change, this can be done now and doesn't need
     * to be recounted every time the counts are requested.
     * @param windowContent The characters of the window.
     */
    public GcWindow(String windowContent){
        this.windowContent = windowContent;
        calculateCounts();
    }

    /**
     * Counts the number of Gs and Cs within the window.
     */
    private void calculateCounts(){
        for(int i=0; i < windowContent.length(); i++){
            if(windowContent.toLowerCase().charAt(i) == 'c'){
                cCount++;
            }
            if(windowContent.toLowerCase().charAt(i) == 'g'){
                gCount++;
            }
        }
    }

    /**
     * Returns the number of 'G' characters in the window.
     * @return The number of 'G' characters in the window.
     */
    public int getGCount(){
        return gCount;
    }

    /**
     * Returns the number of 'G' characters in the window.
     * @return The number of 'G' characters in the window.
     */
    public int getCCount(){
        return cCount;
    }

    /**
     * Returns the length of this window, based on the size of the String that has the characters of the window.
     * @return The length of this window, based on the size of the String that has the characters of the window.
     */
    public int getWindowContentSize(){
        return windowContent.length();
    }
}
