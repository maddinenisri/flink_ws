package com.mdstech.sample;

import com.mdstech.sample.wordcount.Sample;
import org.apache.flink.api.java.DataSet;
import org.apache.flink.api.java.ExecutionEnvironment;
import org.apache.flink.api.java.tuple.Tuple6;
import org.apache.flink.core.fs.FileSystem;
import org.apache.flink.table.api.TableEnvironment;
import org.apache.flink.table.api.java.BatchTableEnvironment;

/**
 * Created by Srini on 5/6/17.
 */
public class GenerateData {
    public static void main(String[] args) throws Exception {
        final ExecutionEnvironment env = ExecutionEnvironment.getExecutionEnvironment();
        final BatchTableEnvironment tableEnv = TableEnvironment.getTableEnvironment( env );
        GenerateData generateData = new GenerateData();
        generateData.generateSampleData(env);
    }

    private void generateSampleData(ExecutionEnvironment environment) throws Exception {
        DataSet<Tuple6<String, String, String, String, String, String>> tuple = environment.fromCollection(environment
                .generateSequence(0, 1000)
                .map(idx -> {
                    final String age = Double.valueOf(Math.random()*10).toString();
                    final double key = (double)idx % 10;
                    return new Tuple6<String, String, String, String, String, String>(Double.valueOf(key).toString(),
                            idx.toString(), "First_"+idx, "Last_"+idx, age, "Sample_"+((long)idx%10));
                })
                .collect());
        tuple.writeAsCsv("datafiles/sample.csv", "\n", "|");
    }

}
