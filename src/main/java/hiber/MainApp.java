package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
    public static void main(String[] args) throws SQLException {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        UserService userService = context.getBean(UserService.class);
        User user1 = new User(
                "User1",
                "Lastname1",
                "user1@mail.ru",
                new Car("user1car", 1)
        );
        user1.getCar().setUser(user1);
        userService.add(user1);

        User user2 = new User(
                "User2",
                "Lastname2",
                "user2@mail.ru",
                new Car("user2car", 2)
        );
        user2.getCar().setUser(user2);
        userService.add(user2);

        User user3 = new User(
                "User3",
                "Lastname3",
                "user3@mail.ru",
                new Car("user3car", 3)
        );
        user3.getCar().setUser(user3);
        userService.add(user3);

        User user4 = new User(
                "User4",
                "Lastname4",
                "user4@mail.ru",
                new Car("user4car", 4)
        );
        user4.getCar().setUser(user4);
        userService.add(user4);

        List<User> users = userService.listUsers();
        for (User user : users) {
            System.out.println("Id = " + user.getId());
            System.out.println("First Name = " + user.getFirstName());
            System.out.println("Last Name = " + user.getLastName());
            System.out.println("Email = " + user.getEmail());
            System.out.println("Car = " + user.getCar());
            System.out.println();
        }

        userService.findAnyUserByCarModelAndSeries("user4car", 4)
                .ifPresent(System.out::println);

        context.close();
    }
}
