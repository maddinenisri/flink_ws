package com.mdstech.sample.csv.mapper;

import com.mdstech.sample.csv.model.User;
import de.bytefish.jtinycsvparser.builder.IObjectCreator;
import de.bytefish.jtinycsvparser.mapping.CsvMapping;

/**
 * Created by Srini on 5/5/17.
 */
public class UserMapper extends CsvMapping<User> {

    public UserMapper(IObjectCreator creator) {
        super(creator);
        mapProperty(0, Integer.class, User::setId);
        mapProperty(1, String.class, User::setFname);
        mapProperty(2, String.class, User::setLname);
    }
}
