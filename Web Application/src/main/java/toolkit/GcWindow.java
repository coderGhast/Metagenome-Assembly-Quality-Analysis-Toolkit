package toolkit;

/**
 * Created by James Euesden on 2/15/2016.
 */
public class GcWindow {
    private String windowContent;
    private int gCount;
    private int cCount;

    public GcWindow(String windowContent){
        this.windowContent = windowContent;
        calculateCounts();
    }

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

    public int getGCount(){
        return gCount;
    }

    public int getCCount(){
        return cCount;
    }

    public int getWindowContentSize(){
        return windowContent.length();
    }
}
