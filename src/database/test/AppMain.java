package database.test;

import database.repositories.ConfigRepository;
import database.repositories.ConfigRepositoryJdbcImpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class AppMain {
    public static void main(String[] args) {
        String url = "jdbc:postgresql://localhost:5432/shop_db";
        Properties props = new Properties();
        props.setProperty("user", "postgres");
        props.setProperty("password", "postgres");
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, props);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ConfigRepository configRepository =
                new ConfigRepositoryJdbcImpl(conn);

        /*

        // findAll
        System.out.println("Users:");
        System.out.println(usersRepository.findAll());
        System.out.println();


        // findOneByUsername
        String username = "'; DROP TABLE shop_user; SELECT '";
        System.out.println("findOneByUsername: " + username);
        Optional<User> optionalUser =
                usersRepository.findOneByUsername(username);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            System.out.println(user);
        } else {
            System.out.println("User with username = " + username + " not found");
        }
        System.out.println();


        // find
        User user2 = null;
        Integer id = 2;
        System.out.println("find by id: id = " + id);

        Optional<User> findingUser =
                usersRepository.find(id);
        if (findingUser.isPresent()) {
            User user = findingUser.get();
            user2 = user;
            System.out.println(user);
        } else {
            System.out.println("User with id = " + id + " not found");
        }
        System.out.println();


        // delete
        System.out.println("Delete: user.id = " + user2.getId());
        usersRepository.delete(user2);
        showUsers(usersRepository);


        // update
        System.out.println("Update: user.id = " + 1);
        usersRepository.update(new User(1, "Ezio", "72"));
        showUsers(usersRepository);


        // save
        User user4 = new User(5, "Altair", "1111");
        System.out.println("Save: " + user4);
        usersRepository.save(user4);
        showUsers(usersRepository);
        */

    }
}