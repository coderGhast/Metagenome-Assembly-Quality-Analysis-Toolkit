package toolkit.domain;

/**
 * Created by James Euesden on 2/10/2016.
 */
public class QualityToolkit {
    private FastaReader _reader;

    public QualityToolkit(){
        _reader = new FastaReader();
    }

    public QualitySummary runInput(UserParameters params){
        return _reader.readUserContent(params);
    }

    public QualitySummary runFile(UserParameters params){
        return _reader.readFile(params);
    }
}
