package com.mdstech.sample;

import com.mdstech.sample.csv.UserDBSinkFunction;
import com.mdstech.sample.csv.UserSourceFunction;
import com.mdstech.sample.csv.model.User;
import org.apache.flink.api.java.functions.KeySelector;
import org.apache.flink.streaming.api.TimeCharacteristic;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.datastream.KeyedStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

/**
 * Created by Srini on 5/5/17.
 */
public class CsvStreamingSample {
    public static void main(String[] args) throws Exception {
        final StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        env.setStreamTimeCharacteristic(TimeCharacteristic.IngestionTime);
        env.setParallelism(1);

        DataStream<User> userDataStream = env.addSource(new UserSourceFunction("datafiles/users1M.csv"));

        KeyedStream<User, Integer> userIntegerKeyedStream = userDataStream.keyBy(new KeySelector<User, Integer>() {
            @Override
            public Integer getKey(User user) throws Exception {
                return user.getId();
            }
        });

        DataStream<User> maxUserStream = userIntegerKeyedStream.maxBy("id");
        maxUserStream.addSink(new UserDBSinkFunction());

        env.execute("Max Temperature By Day example");
    }
}
