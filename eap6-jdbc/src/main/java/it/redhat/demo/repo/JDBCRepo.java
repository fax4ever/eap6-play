package it.redhat.demo.repo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import javax.ejb.Asynchronous;
import javax.ejb.Stateless;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Created by fabio on 27/10/16.
 */
@Stateless
public class JDBCRepo {

    private static Logger log = LoggerFactory.getLogger(JDBCRepo.class);

    @Resource(name = "java:jboss/datasources/ExampleDS")
    private DataSource myDB;

    @Asynchronous
    public void executeQuery(Integer task, Integer star) {

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {

            conn = myDB.getConnection();
            stmt = conn.prepareStatement("select 1");
            rs = stmt.executeQuery();

            if (task % star == 0) {
                log.info("completed {}", task);
            }

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
