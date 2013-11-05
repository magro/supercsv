/*
 * SuperCSV is Copyright 2007, Kasper B. Graversen Licensed under the Apache License, Version 2.0 (the "License"); you
 * may not use this file except in compliance with the License. You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0 Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific language governing permissions and limitations under the
 * License.
 */
package org.supercsv.cellprocessor;

import org.joda.time.DateTime;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.supercsv.cellprocessor.ift.CellProcessor;
import org.supercsv.exception.ClassCastInputCSVException;
import org.supercsv.exception.SuperCSVException;
import org.supercsv.mock.ComparerCellProcessor;
import org.supercsv.util.CSVContext;

/**
 * Test case for the <code>FmtDateTime</code> class.
 *
 * @author ¡ssoɹ ☮
 */
public class FmtDateTimeTest {

    private static final CSVContext CTXT = new CSVContext(0, 0);
    CellProcessor cp = null, ccp = null;

    @Before
    public void setUp() throws Exception {
        cp = new FmtDateTime("dd/MM/yyyy");
    }

    @Test
    public void testChaining() throws Exception {
        ccp = new FmtDateTime("dd/MM/yyyy", new ComparerCellProcessor("17/04/2007")); // chain
        // processors
        Assert.assertEquals("get date", true, ccp.execute(getDayDate(2007,4,17), CTXT));

        ccp = new FmtDateTime("dd-MM-yyyy", new ComparerCellProcessor("17-04-2007")); // chain
        // processors
        Assert.assertEquals("get date", true, ccp.execute(getDayDate(2007,4,17), CTXT));
    }

    @Test
    public void testGoAndBack() throws Exception {
        ccp = new FmtDateTime("dd/MM/yyyy", new ParseDateTime("dd/MM/yyyy")); // chain
        // processors
        DateTime dateTime = getDayDate(2007,4,17);
        Assert.assertEquals("go and back", true, dateTime.equals(ccp.execute(dateTime, CTXT)));

        ccp = new ParseDateTime("dd/MM/yyyy", new FmtDateTime("dd/MM/yyyy")); // chain
        // processors
        String sDate = "17/04/2007";
        Assert.assertEquals("go and back", true, sDate.equals(ccp.execute(sDate, CTXT)));
    }

    @Test(expected = SuperCSVException.class)
    public void test_null_Input() throws Exception {
        cp.execute(null, CTXT);
    }

    @Test(expected = SuperCSVException.class)
    public void testEmptyInput() throws Exception {
        cp.execute("", CTXT);
    }

    @Test(expected = ClassCastInputCSVException.class)
    public void testInvalidInput() throws Exception {
        cp.execute("text-not-a-date", CTXT);
    }

    private static DateTime getDayDate(int year, int month, int day) {
        return new DateTime(year, month, day, 0, 0);
    }
}
