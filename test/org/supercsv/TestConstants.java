package org.supercsv;


import org.joda.time.DateTime;
import org.supercsv.util.CSVContext;
import spiffy.core.util.DateHelper;

import java.util.Date;

public class TestConstants {

	public static final int VALUE_1_AS_INT = 1;
	public static final int VALUE_100_AS_INT = 100;
	public static final int VALUE_17_AS_INT = 17;
	public static final String VALUE_17_AS_STRING = "17";
	public static final long VALUE_17_AS_LONG = 17L;
	public static final Date EXPECTED_DATE = DateHelper.date(2007, 4, 17);
	public static final CSVContext ANONYMOUS_CSVCONTEXT = new CSVContext(0, 0);
    public static final DateTime EXPECTED_DATE_TIME = new DateTime(2007, 4, 17, 0, 0);

}
