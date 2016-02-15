package com.metagenomequalitytoolkit.mmp;

import java.io.*;

/**
 * Created by James Euesden on 2/10/2016.
 */
public class FastaReader{

    public String readFile(String fileLocation){
        StringBuilder fastaFileContentBuilder = new StringBuilder();
        try {
            BufferedReader in = new BufferedReader(new FileReader(fileLocation));


            String line;
            while((line = in.readLine())!= null){
                if(!line.startsWith(">")){
                    FastaLineCleaner.cleanLine(line);
                    fastaFileContentBuilder.append(line);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }catch (IOException e) {
            e.printStackTrace();
        }
        return fastaFileContentBuilder.toString();
    }
}
