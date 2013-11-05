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
import org.supercsv.cellprocessor.ift.CellProcessor;
import org.supercsv.cellprocessor.ift.StringCellProcessor;
import org.supercsv.exception.NullInputException;
import org.supercsv.util.CSVContext;

/**
 * Convert a string to a <code>DateTime</code> using the {@link DateTimeFormatter} class. If you want to convert from a
 * DateTime to a String, use the {@link FmtDateTime} processor.
 * <p>
 * examples of arguments to the cellprocessor are:
 * <p>
 * <code>"MM/dd/yy"</code> to parse dase such as "01/29/02" <br>
 * <code>"dd-MMM-yy"</code> to parse dates such as "29-Jan-02" <br>
 * <code>"yyyy.MM.dd.HH.mm.ss"</code> To parse dates such as "2002.01.29.08.36.33"<br>
 * Or even <code>"E, dd MMM yyyy HH:mm:ss Z"</code> To parse "Tue, 29 Jan 2002 22:14:02 -0500"
 *
 * @author ¡ssoɹ ☮
 *
 */
public class ParseDateTime extends CellProcessorAdaptor implements StringCellProcessor {

    protected DateTimeFormatter formatter;

    public ParseDateTime(final String format) {
        super();
        this.formatter = DateTimeFormat.forPattern(format);
    }

    public ParseDateTime(final String format, CellProcessor next) {
        super(next);
        this.formatter = DateTimeFormat.forPattern(format);
    }

    @Override
    public Object execute(Object value, CSVContext context) {
        if( value == null ) {
            throw new NullInputException("Input cannot be null on line " + context.lineNumber + " at column "
                    + context.columnNumber, context, this);
        }

        final DateTime result = formatter.parseDateTime((String) value);
        return next.execute(result, context);
    }
}
