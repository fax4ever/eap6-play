package it.redhat.demo.repo;

import javax.annotation.Resource;
import javax.ejb.LocalBean;
import javax.ejb.Stateful;
import javax.ejb.Stateless;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Created by fabio on 27/10/16.
 */
@Stateful
public class JDBCRepo {

    @Resource(name = "java:jboss/datasources/ExampleDS")
    private DataSource myDB;

    private int counter = 0;

    public Integer executeQuery() {

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {

            conn = myDB.getConnection();
            stmt = conn.prepareStatement("select 1");
            rs = stmt.executeQuery();

            return ++counter;

        } catch (Exception e) {

            throw new RuntimeException(e);

        } finally {

            try {
                if (rs != null) rs.close();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

            try {
                if (stmt != null) stmt.close();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

            try {
                if (conn != null) conn.close();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }


        }

    }


}
