package com.mdstech.sample.csv.parser;

import com.mdstech.sample.csv.mapper.UserMapper;
import com.mdstech.sample.csv.model.User;
import de.bytefish.jtinycsvparser.CsvParser;
import de.bytefish.jtinycsvparser.CsvParserOptions;
import de.bytefish.jtinycsvparser.tokenizer.StringSplitTokenizer;

/**
 * Created by Srini on 5/5/17.
 */
public class Parsers {
    public static CsvParser<User> UserParser() {
        return new CsvParser<>(new CsvParserOptions(true, new StringSplitTokenizer("\\|", true)),
                new UserMapper(() -> new User()));
    }
}
