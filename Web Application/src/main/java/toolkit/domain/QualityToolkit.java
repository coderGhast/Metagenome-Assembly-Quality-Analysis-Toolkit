package toolkit.domain;

/**
 * Created by James Euesden on 12/04/2016.
 */
public class QualityToolkit {

    public static QualitySummary qualityAssess(ContiguousRead currentContig, int windowSize, int orfLengthThreshold){
        QualitySummary summary = new QualitySummary();

        GcResult gcResult = GcContentCounter.countGcContent(currentContig.getContigContext(), windowSize);
        summary.addGcResult(gcResult);

        OpenReadingFrameResult orfResult = new OpenReadingFrameFinder().findPotentialOrfLocations(currentContig.getContigContext());
        orfResult.removeLowerThanThresholdOrfLocations(orfLengthThreshold);

        summary.addOrfResult(orfResult);
        summary.setContiguousRead(currentContig);

        return summary;
    }
}
