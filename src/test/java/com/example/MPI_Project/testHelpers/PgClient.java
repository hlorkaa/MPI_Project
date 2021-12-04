package com.example.MPI_Project.testHelpers;

import com.example.MPI_Project.domain.Role;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Closeable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import static java.util.Objects.isNull;

public class PgClient implements Closeable {
    private final Logger LOG = LoggerFactory.getLogger(PgClient.class);
    private static final String FINANCES_TABLE_NAME ="finances";
    private static final String ORDER_TABLE_NAME ="order_card";
    private static final String REPORT_TABLE_NAME ="report";
    private static final String USER_TABLE_NAME ="t_user";
    private static final String TASK_TABLE_NAME ="task";
    private Connection connection;

    public PgClient() {
        connect();
    }

    private void connect() {
        try {
            connection = DriverManager.getConnection(
                    "jdbc:postgresql://localhost/MPI_Project",
                    "postgres",
                    "qwerty123");
        } catch (Exception ex) {
            LOG.error("Connection error", ex);
            throw new RuntimeException("Failed when create connection: " + ex.getMessage(), ex);
        }
    }

    public void close() {
        try {
            if (isNull(connection) || connection.isClosed()) {
                LOG.debug("connection is null or closed already");
                return;
            }
            connection.close();
        } catch (SQLException ex) {
            throw new RuntimeException("Failed when close connection: " + ex.getMessage());
        }
    }

    public void writeOrderTableVolumeTestData(long countRows) {
        for (long i = 0; i<countRows; i++){
            writeOrderTableTestData("Тестовый заказ", "Иванов И.И.", "2021-11-08", "2021-11-18", "Обычное", 10, "Примечания тестового заказа.");
            if (i % 1000 == 0) {
                System.out.println("Filling " + ORDER_TABLE_NAME + " complete on " + i * 100 / countRows + "%");
            }
            if (i == countRows-1) {
                System.out.println("Filling " + ORDER_TABLE_NAME + " completed");
            }
        }
    }

    public void writeTaskTableVolumeTestData(long countRows) {
        for (long i = 0; i<countRows; i++){
            writeTaskTableTestData("Тестовая задача", "2021-11-29", "Утверждено", "Тестовое описание задачи.", "admin");
            if (i % 1000 == 0) {
                System.out.println("Filling " + TASK_TABLE_NAME + " complete on " + i * 100 / countRows + "%");
            }
            if (i == countRows-1) {
                System.out.println("Filling " + TASK_TABLE_NAME + " completed");
            }
        }
    }

    public void writeOrderTableTestData(String orderName, String customer, String orderdate, String orderdeadline, String quality, Integer quantity, String notes) {
        try {
            String TEST_DATA_INSERT_QUERY = String.format("INSERT INTO %s " +
                    "(customer, notes, orderdate, orderdeadline, ordername, quality, quantity) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?)", ORDER_TABLE_NAME);
            try (PreparedStatement st = connection.prepareStatement(TEST_DATA_INSERT_QUERY)) {
                    st.setString(1, customer);
                    st.setString(2, notes);
                    st.setString(3, orderdate);
                    st.setString(4, orderdeadline);
                    st.setString(5, orderName);
                    st.setString(6, quality);
                    st.setInt(7, quantity);
                    st.executeUpdate();
                }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void writeTaskTableTestData(String name, String deadline, String status, String description, String workman) {
        try {
            String TEST_DATA_INSERT_QUERY = String.format("INSERT INTO %s " +
                    "(deadline, description, name, status, workman) " +
                    "VALUES (?, ?, ?, ?, ?)", TASK_TABLE_NAME);
            try (PreparedStatement st = connection.prepareStatement(TEST_DATA_INSERT_QUERY)) {
                st.setString(1, deadline);
                st.setString(2, description);
                st.setString(3, name);
                st.setString(4, status);
                st.setString(5, workman);
                st.executeUpdate();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void writeUserTableTestData(String username, String password, Role role) {
        try {
            String TEST_DATA_INSERT_QUERY = String.format("INSERT INTO %s " +
                    "(password, role, username) " +
                    "VALUES (?, ?, ?)", USER_TABLE_NAME);
            try (PreparedStatement st = connection.prepareStatement(TEST_DATA_INSERT_QUERY)) {
                st.setString(1, password);
                st.setString(2, role.getAuthority());
                st.setString(3, username);
                st.executeUpdate();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void truncateOrderTable() {
        truncateTable(ORDER_TABLE_NAME);
    }

    public void truncateTaskTable() {
        truncateTable(TASK_TABLE_NAME);
    }

    public void truncateUserTable() {
        truncateTable(USER_TABLE_NAME);
    }

    public void truncateTable(String tableName) {
        try(Statement statement = connection.createStatement()) {
            statement.executeUpdate("TRUNCATE " + tableName);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
