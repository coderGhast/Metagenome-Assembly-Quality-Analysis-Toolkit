package toolkit.domain;

/**
 * Created by James Euesden on 12/04/2016.
 */
public class QualityToolkit {

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
