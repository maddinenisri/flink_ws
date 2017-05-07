package com.mdstech.sample.csv;

import org.apache.flink.api.java.ExecutionEnvironment;
import org.apache.flink.api.java.io.CsvReader;
import org.apache.flink.api.java.operators.DataSource;
import org.apache.flink.api.java.tuple.Tuple3;
import org.apache.flink.types.IntValue;
import org.apache.flink.types.StringValue;

/**
 * Created by Srini on 5/5/17.
 */
public class CSVParser {
    public static void main(String []args) throws Exception {
        final ExecutionEnvironment executionEnvironment =
                ExecutionEnvironment.getExecutionEnvironment();
        CsvReader csvReader = executionEnvironment.readCsvFile("datafiles/users.csv").fieldDelimiter("|");
        csvReader.ignoreFirstLine();
        DataSource<Tuple3<IntValue, StringValue, StringValue>> items =
                csvReader.types(IntValue.class, StringValue.class, StringValue.class);
        items.print();
    }
}
