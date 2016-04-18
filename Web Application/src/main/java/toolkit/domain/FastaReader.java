package toolkit.domain;

import toolkit.utilities.UserContentValidator;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by James Euesden on 2/10/2016.
 */
public class FastaReader{
    private UserContentValidator _validator;
    private StringBuilder _sb;
    private ArrayList<ContiguousRead> _contigsList;

    public FastaReader(){
        _validator = new UserContentValidator();
        _sb = new StringBuilder();
        _contigsList = new ArrayList<>();
    }

    public ContigResult readSequenceInput(UserParameters params){
        ContigResult contigResult;
        if(_validator.validateUserContent(params.getUserContent())){
            contigResult = readUserInput(params.getUserContent(), params.getContigLengthThreshold());
        } else {
            // TODO: Own generated files - This is a temporary measure
            params.setFileName("./src/main/resources/static/contig.1274754.fa");
            contigResult = readFile(params.getFileName(), params.getContigLengthThreshold());
        }
        return contigResult;
    }

    public ContigResult readUserInput(String userContent, int lengthThreshold){
        ContiguousRead currentContig = new ContiguousRead();
        int discardedContigs = 0;
        String contig = userContent.trim();
        String[] contigCompontents = contig.split("\\n");

        for(int i= 0; i < contigCompontents.length; i ++){
            String line = contigCompontents[i];
            if (line.startsWith(">")) {
                if (currentContig.getContigInformation() != null) {
                    currentContig.setContigContext(_sb.toString());
                    if (_sb.length() > lengthThreshold || lengthThreshold == 0) {
                        currentContig.setContigLength(_sb.length());
                        _contigsList.add(currentContig);
                    } else {
                        discardedContigs++;
                    }
                    _sb.setLength(0);
                }
                currentContig = new ContiguousRead();
                currentContig.setContigInformation(line);
            } else {
                _sb.append(line.trim());
            }

        }
        // Assess the final contig
        currentContig.setContigContext(_sb.toString());
        currentContig.setContigLength(_sb.length());
        if(_sb.length() > lengthThreshold || lengthThreshold == 0) {
            _contigsList.add(currentContig);
        } else {
            discardedContigs++;
        }

        ContigResult result = new ContigResult();
        result.setContigList(_contigsList);
        result.setDiscardedContigCount(discardedContigs);
        return result;
    }

    public ContigResult readFile(String fileName, int lengthThreshold){
        int discardedContigs = 0;
        try {
            BufferedReader in = new BufferedReader(new FileReader(fileName));
            ContiguousRead currentContig = new ContiguousRead();

            String line;
            while((line = in.readLine())!= null){
                    if (line.startsWith(">")) {
                        if (currentContig.getContigInformation() != null) {
                            currentContig.setContigContext(_sb.toString());
                            if (_sb.length() > lengthThreshold || lengthThreshold == 0) {
                                currentContig.setContigLength(_sb.length());
                                _contigsList.add(currentContig);
                            } else {
                                discardedContigs++;
                            }
                            _sb.setLength(0);
                        }
                        currentContig = new ContiguousRead();
                        currentContig.setContigInformation(line);
                    } else {
                        _sb.append(line.trim());
                    }

            }
            // Assess the final contig
            currentContig.setContigContext(_sb.toString());
            currentContig.setContigLength(_sb.length());
            if(_sb.length() > lengthThreshold || lengthThreshold == 0) {
                _contigsList.add(currentContig);
            } else {
                discardedContigs++;
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }catch (IOException e) {
            e.printStackTrace();
        }

        ContigResult result = new ContigResult();
        result.setContigList(_contigsList);
        result.setDiscardedContigCount(discardedContigs);
        return result;
    }



}
