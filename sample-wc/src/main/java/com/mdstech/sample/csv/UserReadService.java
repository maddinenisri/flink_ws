package com.mdstech.sample.csv;

import com.mdstech.sample.csv.model.User;
import com.mdstech.sample.csv.parser.Parsers;
import de.bytefish.jtinycsvparser.mapping.CsvMappingResult;

import java.nio.charset.StandardCharsets;
import java.nio.file.FileSystems;
import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by Srini on 5/5/17.
 */
public class UserReadService {

    public static void main(String[] args) {

        LocalDateTime startDateTime = LocalDateTime.now();
        try {
            AtomicInteger currentIndex = new AtomicInteger(1);
            List<User> users = getUsers("datafiles/users1M.csv").skip(1).map(x -> x.getResult()).collect(Collectors.toList());
            users.forEach(System.out::println);
            LocalDateTime endDateTime = LocalDateTime.now();
            long diffInNano = java.time.Duration.between(startDateTime, endDateTime).getNano();
            long diffInSeconds = java.time.Duration.between(startDateTime, endDateTime).getSeconds();
            long diffInMilli = java.time.Duration.between(startDateTime, endDateTime).toMillis();
            long diffInMinutes = java.time.Duration.between(startDateTime, endDateTime).toMinutes();
            long diffInHours = java.time.Duration.between(startDateTime, endDateTime).toHours();
            System.out.println(diffInHours +":" + diffInMinutes +":" + diffInMilli +":" + diffInSeconds +":" + diffInNano);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private static Stream<CsvMappingResult<User>> getUsers(String path) throws Exception {
        return Parsers.UserParser().readFromFile(FileSystems.getDefault().getPath(path), StandardCharsets.US_ASCII);
    }
}
