package toolkit.domain;

/**
 * Created by James Euesden on 12/04/2016.
 *
 * Part of the Controller of the application, creating the results summary in QualitySummary and calling for the
 * requested inspections with the provided contiguous read, returning a QualitySummary as a result to be used in
 * the View.
 */
public class QualityToolkit {

    /**
     * Using a provided contiguous read (ContiguousRead), carries out quality inspections on it using the techniques
     * known to the application and returns a summary result.
     * @param currentContig The provided ContiguousRead to inspect further.
     * @return The collected result of the inspections in a QualitySummary object.
     */
    public static QualitySummary qualityAssess(ContiguousRead currentContig){
        QualitySummary summary = new QualitySummary();

        GcResult gcResult = GcContentCounter.countGcContent(currentContig.getContigContext(), currentContig.getGcWindowSize());
        summary.addGcResult(gcResult);

        OpenReadingFrameResult orfResult = new OpenReadingFrameFinder().findPotentialOrfLocations(currentContig.getContigContext());
        orfResult.removeLowerThanThresholdOrfLocations(currentContig.getOrfLengthThreshold());

        summary.addOrfResult(orfResult);
        summary.setContiguousRead(currentContig);

        return summary;
    }
}
