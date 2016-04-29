package toolkit.domain;

import toolkit.utilities.UserContentValidator;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by James Euesden on 2/10/2016.
 *
 * Understands how to process a FASTA file to create an ArrayList of ContiguousReads from users input.
 */
public class FastaReader{
    private UserContentValidator _validator;
    private StringBuilder _sb;
    private ArrayList<ContiguousRead> _contigsList;

    /**
     * Initializes the required components.
     */
    public FastaReader(){
        _validator = new UserContentValidator();
        _sb = new StringBuilder();
        _contigsList = new ArrayList<>();
    }

    /**
     * Reads the input from the user parameters (looking for user pasted data) and calls to the correct method.
     * Currently, the application does not allow users to upload files, and leaving the input paste box empty or pasting
     * invalid content will return them the test file results while my system was under development. This method is left
     * in order to be expanded upon later to allow users to upload their files rather than paste them.
     * @param params UserParameters of user input, contains the pasted assembly data and what is the minimum
     *               size of a contiguous read to be considered.
     * @return A ContigResult that holds the contiguous reads from the user input.
     */
    public ContigResult readSequenceInput(UserParameters params) throws Exception {
        ContigResult contigResult;
        // Clear the String builder before any new input.
        _sb.setLength(0);
        if(_validator.validateUserContent(params.getUserContent())){
            contigResult = readUserInput(params.getUserContent(), params.getContigLengthThreshold());
        } else {
            throw new Exception("User content cannot be null or invalid.");
            // Test file...
            //params.setFileName("./src/main/resources/static/artificialtestcontigs.fa");
            // contigResult = readFile(params.getFileName(), params.getContigLengthThreshold());
        }
        return contigResult;
    }

    /**
     * Handles extracting the contiguous reads and their content from the user pasted input, and ignoring any
     * under the users set minimum length threshold.
     * @param userContent The pasted assembly content from the user.
     * @param minimumLengthThreshold The users set threshold for the minimum size a contiguous read can be to be shown.
     * @return A ContigResult containing the contiguous reads from the users input.
     */
    public ContigResult readUserInput(String userContent, int minimumLengthThreshold){
        ContiguousRead currentContig = new ContiguousRead();
        int discardedContigs = 0;
        String contig = userContent.trim();
        String[] contigCompontents = contig.split("\\n");

        for(int i= 0; i < contigCompontents.length; i ++){
            String line = contigCompontents[i];
            // If the next contig compontent from the String array is the header of a new contig
            if (line.startsWith(">")) {
                // And if there is already contig information (header) stored
                if (currentContig.getContigInformation() != null) {
                    // Set the content of the contig to the contig context, as we're finishing one contig
                    // and moving onto a new one
                    currentContig.setContigContext(_sb.toString());
                    // As long as the length of this contig is above the threshold, add it to the contigs
                    // we want to return to the user.
                    if (_sb.length() > minimumLengthThreshold || minimumLengthThreshold == 0) {
                        _contigsList.add(currentContig);
                    } else {
                        discardedContigs++;
                    }
                    // Clear out the string builder to build up new contig context.
                    _sb.setLength(0);
                }
                // Set the contig information back to new.
                currentContig = new ContiguousRead();
                // Then set the header to be the current line that starts with '>'
                currentContig.setContigInformation(line);
            } else {
                // Otherwise just add this line to the string builder for the contig context, as it is part
                // of the content of the contig sequence.
                _sb.append(line.trim());
            }

        }
        // Create the final contig, as we reached the end of the String array, but don't have a new header to indicate the
        // end of the previous contig, we set it manually like so.
        currentContig.setContigContext(_sb.toString());
        if(_sb.length() > minimumLengthThreshold || minimumLengthThreshold == 0) {
            _contigsList.add(currentContig);
        } else {
            discardedContigs++;
        }

        ContigResult result = new ContigResult();
        result.setContigList(_contigsList);
        result.setDiscardedContigCount(discardedContigs);
        return result;
    }


    /**
     * Handles extracting the contiguous reads and their content from the user file, and ignoring any
     * under the users set minimum length threshold.
     * @param fileName The name of the file to be processed.
     * @param minimumLengthThreshold The users set threshold for the minimum size a contiguous read can be to be shown.
     * @return A ContigResult containing the contiguous reads from the users input.
     */
    public ContigResult readFile(String fileName, int minimumLengthThreshold){
        int discardedContigs = 0;
        try {
            BufferedReader in = new BufferedReader(new FileReader(fileName));
            ContiguousRead currentContig = new ContiguousRead();

            String line;
            while((line = in.readLine())!= null){
                // If the next line is the header of a new contig
                    if (line.startsWith(">")) {
                        // And if there is already contig information (header) stored
                        if (currentContig.getContigInformation() != null) {
                            // Set the content of the contig to the contig context, as we're finishing one contig
                            // and moving onto a new one
                            currentContig.setContigContext(_sb.toString());
                            // As long as the length of this contig is above the threshold, add it to the contigs
                            // we want to return to the user.
                            if (_sb.length() > minimumLengthThreshold || minimumLengthThreshold == 0) {
                                _contigsList.add(currentContig);
                            } else {
                                discardedContigs++;
                            }
                            // Clear out the string builder to build up new contig context.
                            _sb.setLength(0);
                        }
                        // Set the contig information back to new.
                        currentContig = new ContiguousRead();
                        // Then set the header to be the current line that starts with '>'
                        currentContig.setContigInformation(line);
                    } else {
                        // Otherwise just add this line to the string builder for the contig context, as it is part
                        // of the content of the contig sequence.
                        _sb.append(line.trim());
                    }

            }
            // Create the final contig, as we reached the end of the file, but don't have a new header to indicate the
            // end of the previous contig, we set it manually like so.
            currentContig.setContigContext(_sb.toString());
            if(_sb.length() > minimumLengthThreshold || minimumLengthThreshold == 0) {
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
