package com.mdstech.sample.wordcount;

import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.api.java.DataSet;
import org.apache.flink.api.java.ExecutionEnvironment;
import org.apache.flink.api.java.tuple.Tuple;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.util.Collector;

/**
 * Created by Srini on 5/5/17.
 */
public class WordCount {

    public static void main(String []args) throws Exception {
        final ExecutionEnvironment executionEnvironment =
                ExecutionEnvironment.getExecutionEnvironment();

        DataSet<String> text =  executionEnvironment.fromElements(
                "To be, or not to be,--that is the question:--",
                "Whether 'tis nobler in the mind to suffer",
                "The slings and arrows of outrageous fortune",
                "Or to take arms against a sea of troubles,");

        DataSet<Tuple2<String, Integer>> counts =
                text.flatMap(new LineSplitter())
                .groupBy(0)
                .sum(1);
        counts.print();
    }

    public static final class LineSplitter implements FlatMapFunction<String, Tuple2<String, Integer>> {

        @Override
        public void flatMap(String value, Collector<Tuple2<String, Integer>> collector) throws Exception {
            String[] tokens = value.toLowerCase().split("\\W+");
            for (String token : tokens) {
                if (token.length() > 0) {
                    collector.collect(new Tuple2<String, Integer>(token, 1));
                }
            }
        }
    }

}
