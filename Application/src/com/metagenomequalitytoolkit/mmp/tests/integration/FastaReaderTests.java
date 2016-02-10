package com.metagenomequalitytoolkit.mmp.tests.integration;

import com.metagenomequalitytoolkit.mmp.FastaReader;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by James Euesden on 2/10/2016.
 */
public class FastaReaderTests {

    private FastaReader sut;

    @Before
    public void Setup(){
        sut = new FastaReader();
    }

    @Test
    public void fileReadShouldReturnExpectedFileContent(){
        String testFileContent = sut.readFile("testFile");
        assertEquals("Test file content should match testtest", testFileContent, "testtest");
    }
}
