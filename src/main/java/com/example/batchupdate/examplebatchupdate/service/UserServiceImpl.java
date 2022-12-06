package com.example.batchupdate.examplebatchupdate.service;

import com.example.batchupdate.examplebatchupdate.model.User;
import com.github.javafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.apache.commons.lang3.time.StopWatch;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
@Service
public class UserServiceImpl implements UserService{
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public UserServiceImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void batchUpdateTest() {
        List<User> users= create10_000Users();
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        jdbcTemplate.batchUpdate("INSERT INTO Users (user_name, address, email, telephone) VALUES(?, ?, ?, ?)", new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement preparedStatement, int i) throws SQLException {
                preparedStatement.setString(1, users.get(i).getUser_name());
                preparedStatement.setString(2, users.get(i).getAddress());
                preparedStatement.setString(3, users.get(i).getEmail());
                preparedStatement.setString(4, users.get(i).getTelephone());
            }

            @Override
            public int getBatchSize() {
                return users.size();
            }
        });
        stopWatch.stop();
        System.out.println("Time has passed with batch update, ms: " + stopWatch.getTime());
    }
    public void multipleUpdateTest() {
        List<User> users= create10_000Users();
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        for (User user: users) {
            jdbcTemplate.update("INSERT INTO Users (user_name, address, email, telephone) VALUES(?, ?, ?, ?)",
                    user.getUser_name(),
                    user.getAddress(),
                    user.getEmail(),
                    user.getTelephone());
        }
        stopWatch.stop();
        System.out.println("Time has passed with update, ms: " + stopWatch.getTime());
    }

    public static List<User> create10_000Users() {
        List<User> users = new ArrayList<>();
        Faker faker = new Faker();
        for (int i = 0; i < 10_000; i++) {
            String firstName = faker.name().firstName();
            String lastName = faker.name().lastName();
            String sufixTel = String.valueOf(i);
            String telephone = "+000290000000";
            telephone = telephone.substring(0, telephone.length()-sufixTel.length()) + sufixTel;
            User user = new User(
                    firstName + " " + lastName,
                    faker.address().fullAddress(),
                    firstName.toLowerCase() + lastName.toLowerCase() + i + "@gmail.com",
                    telephone);
            users.add(user);
        }
        return users;
    }
}
