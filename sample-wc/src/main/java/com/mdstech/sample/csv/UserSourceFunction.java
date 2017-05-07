package com.mdstech.sample.csv;

import com.mdstech.sample.csv.model.User;
import com.mdstech.sample.csv.parser.Parsers;
import de.bytefish.jtinycsvparser.mapping.CsvMappingResult;
import org.apache.flink.streaming.api.functions.source.SourceFunction;

import java.nio.charset.StandardCharsets;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.Iterator;
import java.util.stream.Stream;

/**
 * Created by Srini on 5/5/17.
 */
public class UserSourceFunction implements SourceFunction<User> {

    private volatile boolean isRunning = true;
    private final String userCsvPath;

    public UserSourceFunction(String userCsvPath) {
        this.userCsvPath = userCsvPath;
    }

    @Override
    public void run(SourceContext<User> sourceContext) throws Exception {
        try(Stream<User> stream = getUsers(userCsvPath)) {
            Iterator<User> iterator = stream.iterator();
            while (isRunning && iterator.hasNext()) {
                sourceContext.collect(iterator.next());
            }
        }
    }

    @Override
    public void cancel() {
        isRunning = false;
    }

    private static Stream<User> getUsers(String path) throws Exception {
        return Parsers.UserParser()
                .readFromFile(FileSystems.getDefault().getPath(path), StandardCharsets.US_ASCII)
                .filter(x -> x.isValid())
                .map(x -> x.getResult());
    }
}
