/*
 * SuperCSV is Copyright 2007, Kasper B. Graversen Licensed under the Apache License, Version 2.0 (the "License"); you
 * may not use this file except in compliance with the License. You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0 Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific language governing permissions and limitations under the
 * License.
 */
package org.supercsv.cellprocessor;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.supercsv.TestConstants;
import org.supercsv.mock.ComparerCellProcessor;

/**
 * Test for the <code>ParseDateTime</code> class.
 *
 * @author ¡ssoɹ ☮
 */
public class ParseDateTimeTest {

    ParseDateTime pdt1 = null, pdt2 = null;

    @Before
    public void setUp() throws Exception {
        pdt1 = new ParseDateTime("dd/MM/yy");
    }

    @Test
    public void testChaining() throws Exception {
        pdt2 = new ParseDateTime("dd/MM/yyyy", new ComparerCellProcessor(TestConstants.EXPECTED_DATE_TIME)); // chain
        // processors
        Assert.assertEquals("get date", true, pdt2.execute("17/4/2007", TestConstants.ANONYMOUS_CSVCONTEXT));

        pdt2 = new ParseDateTime("dd-MM-yyyy", new ComparerCellProcessor(TestConstants.EXPECTED_DATE_TIME)); // chain
        // processors
        Assert.assertEquals("get date", true, pdt2.execute("17-4-2007", TestConstants.ANONYMOUS_CSVCONTEXT));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testEmptyInput() throws Exception {
        pdt1.execute("", TestConstants.ANONYMOUS_CSVCONTEXT);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInValidInput() throws Exception {
	    Assert.assertEquals("never reached", pdt1.execute("21/21/21", TestConstants.ANONYMOUS_CSVCONTEXT));
    }

    @Test
    public void test_weird_not_failing_on_InValidInput_wrong_year_format() throws Exception {
        pdt1.execute("17/04/2007", TestConstants.ANONYMOUS_CSVCONTEXT);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInValidInput2() throws Exception {
	    Assert.assertEquals("never reached", pdt1.execute("a date", TestConstants.ANONYMOUS_CSVCONTEXT));
    }

    @Test
    public void validInputTest() throws Exception {
	    Assert.assertEquals("read date", TestConstants.EXPECTED_DATE_TIME, pdt1.execute("17/04/07",
            TestConstants.ANONYMOUS_CSVCONTEXT));

        pdt1 = new ParseDateTime("MM-dd-yy");
	    Assert.assertEquals("read date", TestConstants.EXPECTED_DATE_TIME, pdt1.execute("04-17-07",
		    TestConstants.ANONYMOUS_CSVCONTEXT));
    }
}

