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
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.supercsv.cellprocessor.ift.DateCellProcessor;
import org.supercsv.cellprocessor.ift.StringCellProcessor;
import org.supercsv.exception.ClassCastInputCSVException;
import org.supercsv.exception.NullInputException;
import org.supercsv.exception.SuperCSVException;
import org.supercsv.util.CSVContext;

/**
 * Converts a <code>DateTime</code> into a formatted string using the {@link org.joda.time.format.DateTimeFormatter}
 * class. If you want to convert from a String to a DateTime, use the {@link org.supercsv.cellprocessor.ParseDateTime}
 * processor.
 * <p>
 * Examples of arguments to this cellprocessor are: <br>
 * <code>"MM/dd/yy"</code> to print date such as "01/29/02" <br>
 * <code>"dd-MMM-yy"</code> <br>
 * To print dates such as "29-Jan-02" <br>
 * <code>"yyyy.MM.dd.HH.mm.ss"</code> <br>
 * To print dates such as "2002.01.29.08.36.33" <br>
 * Or even <code>"E, dd MMM yyyy HH:mm:ss Z"</code> <br>
 * To print "Tue, 29 Jan 2002 22:14:02 -0500"
 *
 * @author ¡ssoɹ ☮
 */
public class FmtDateTime extends CellProcessorAdaptor implements DateCellProcessor {

protected DateTimeFormatter formatter;

public FmtDateTime(final String format) {
	super();
	this.formatter = DateTimeFormat.forPattern(format);
}

public FmtDateTime(final String format, final StringCellProcessor next) {
	super(next);
	this.formatter = DateTimeFormat.forPattern(format);
}

/**
 * {@inheritDoc}
 */
@Override
public Object execute(final Object value, final CSVContext context) throws SuperCSVException {
	if( value == null ) { throw new NullInputException("Input cannot be null on line " + context.lineNumber
		+ " column " + context.columnNumber, context, this); }
	if( !(value instanceof DateTime) ) { throw new ClassCastInputCSVException("the value '" + value
		+ "' is not of type Date", context, this); }
	final String result = formatter.print((DateTime) value);
	return next.execute(result, context);
}
}
