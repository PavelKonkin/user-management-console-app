package management;

import management.model.dto.UserDto;
import management.service.IOService;
import management.service.UserService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class Main {
    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(Main.class, args);
        UserService userService = context.getBean(UserService.class);
        IOService ioService = context.getBean(IOService.class);

        label:
        while (true) {
            String userChoice = ioService.getMainMenuChoice();
            String email;
            UserDto userDto;
            switch (userChoice) {
                case "1":
                    userDto = ioService.getUserCreationData();
                    userService.create(userDto);
                    break;
                case "2":
                    email = ioService.getEmailOfUser();
                    if (email != null) {
                        userDto = userService.get(email);
                        if (userDto != null) {
                            userDto = ioService.getUserUpdateData(userDto);
                            userService.update(userDto);
                        } else {
                            System.out.println("Пользователя с таким email не существует");
                        }
                    }

                    break;
                case "3":
                    email = ioService.getEmailOfUser();
                    userService.delete(email);
                    break;
                case "4":
                    email = ioService.getEmailOfUser();
                    if (email != null) {
                        userDto = userService.get(email);
                        if (userDto != null) {
                            ioService.printUserInformation(userDto);
                        } else {
                            System.out.println("Пользователя с таким email не существует");
                        }
                    }
                    break;
                case "выход":
                    break label;
            }
        }
    }
}