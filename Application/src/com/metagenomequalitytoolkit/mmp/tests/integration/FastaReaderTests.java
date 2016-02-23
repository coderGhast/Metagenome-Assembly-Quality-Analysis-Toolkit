package com.metagenomequalitytoolkit.mmp.tests.integration;

import com.metagenomequalitytoolkit.mmp.FastaReader;
import org.junit.Before;

/**
 * Created by James Euesden on 2/10/2016.
 */
public class FastaReaderTests {

    private FastaReader sut;

    @Before
    public void Setup(){
        sut = new FastaReader();
    }

}
