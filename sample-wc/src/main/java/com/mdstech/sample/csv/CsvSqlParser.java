package com.mdstech.sample.csv;

import com.mdstech.sample.csv.model.User;
import com.mdstech.sample.csv.model.UserDetail;
import org.apache.flink.api.common.typeinfo.TypeInformation;
import org.apache.flink.api.java.DataSet;
import org.apache.flink.api.java.ExecutionEnvironment;
import org.apache.flink.api.java.io.CsvInputFormat;
import org.apache.flink.api.java.io.jdbc.JDBCOutputFormat;
import org.apache.flink.table.api.Table;
import org.apache.flink.table.api.TableEnvironment;
import org.apache.flink.table.api.Types;
import org.apache.flink.table.api.java.BatchTableEnvironment;
import org.apache.flink.table.expressions.Expression;
import org.apache.flink.table.sources.CsvTableSource;
import org.apache.flink.types.IntValue;
import org.apache.flink.types.Row;
import org.apache.flink.types.StringValue;
import scala.collection.Seq;

import java.sql.SQLType;
import java.time.LocalDateTime;

/**
 * Created by Srini on 5/5/17.
 */
public class CsvSqlParser {
    public static void main(String[] args) throws Exception {
        LocalDateTime startDateTime = LocalDateTime.now();

        final ExecutionEnvironment env = ExecutionEnvironment.getExecutionEnvironment();
        final BatchTableEnvironment tableEnv = TableEnvironment.getTableEnvironment( env );

        CsvTableSource userTableSource = new CsvTableSource("datafiles/sample1M.csv", "key,id,fname,lname,age,company".split(","),
                new TypeInformation[]{ Types.INT(), Types.INT(), Types.STRING(), Types.STRING(), Types.INT(), Types.STRING() },
                "|", CsvInputFormat.DEFAULT_LINE_DELIMITER, '"',
                true, null, true);
        tableEnv.registerTableSource("users", userTableSource);
        Table userTable = tableEnv.scan("users");
        Table userTable2 = tableEnv.scan("users");


        //userTable.where("key = '10'").select("id, fname, lname, age, company");
        Table subSetTable2 =
        tableEnv.sql("SELECT id, fname, lname, age, company FROM users WHERE key = 20");
        DataSet<Row> result2 = tableEnv.toDataSet(subSetTable2, Row.class);
        JDBCOutputFormat.JDBCOutputFormatBuilder outputBuilder2 =
                JDBCOutputFormat.buildJDBCOutputFormat()
                        .setDrivername("org.postgresql.Driver")
                        .setDBUrl("jdbc:postgresql://localhost:5432/postgres")
                        .setUsername("postgres")
                        .setPassword("postgres")
                        .setQuery("insert into member_det (id,fname,lname,age,company) values (?,?,?,?,?)")
                        .setSqlTypes(new int[] { java.sql.Types.INTEGER, java.sql.Types.VARCHAR, java.sql.Types.VARCHAR, java.sql.Types.INTEGER, java.sql.Types.VARCHAR });

        result2.output(outputBuilder2.finish());

//        env.execute();

        Table subSetTable = tableEnv.sql("SELECT id, fname, lname FROM users WHERE key = 10");
                //userTable2.where("key = 10").select("id, fname, lname");
        DataSet<Row> result = tableEnv.toDataSet(subSetTable, Row.class);

        JDBCOutputFormat.JDBCOutputFormatBuilder outputBuilder =
                JDBCOutputFormat.buildJDBCOutputFormat()
                        .setDrivername("org.postgresql.Driver")
                        .setDBUrl("jdbc:postgresql://localhost:5432/postgres")
                        .setUsername("postgres")
                        .setPassword("postgres")
                        .setQuery("insert into user_map (id,first_name,last_name) values (?,?,?)")
                        .setSqlTypes(new int[] { java.sql.Types.INTEGER, java.sql.Types.VARCHAR, java.sql.Types.VARCHAR });

        result.output(outputBuilder.finish());

        env.execute();

        LocalDateTime endDateTime = LocalDateTime.now();
        long diffInNano = java.time.Duration.between(startDateTime, endDateTime).getNano();
        long diffInSeconds = java.time.Duration.between(startDateTime, endDateTime).getSeconds();
        long diffInMilli = java.time.Duration.between(startDateTime, endDateTime).toMillis();
        long diffInMinutes = java.time.Duration.between(startDateTime, endDateTime).toMinutes();
        long diffInHours = java.time.Duration.between(startDateTime, endDateTime).toHours();
        System.out.println(diffInHours +":" + diffInMinutes +":" + diffInSeconds +":" + diffInMilli +":" + diffInNano);

    }
}
