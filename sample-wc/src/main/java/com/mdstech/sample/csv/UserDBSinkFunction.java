package com.mdstech.sample.csv;

import com.mdstech.sample.csv.model.User;
import org.apache.flink.streaming.api.functions.sink.RichSinkFunction;

/**
 * Created by Srini on 5/5/17.
 */
public class UserDBSinkFunction extends RichSinkFunction<User> {

    @Override
    public void invoke(User user) throws Exception {
        System.out.println("Received User: " + user);
    }
}
