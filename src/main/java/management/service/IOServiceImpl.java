package management.service;

import management.model.dto.UserDto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Service
public class IOServiceImpl implements IOService{
    private final Scanner scanner = new Scanner(System.in);

    private final String MAIN_MENU_REGEX_FILTER = "\\b[1-4]\\b|\\bвыход\\b";
    private final String ROLE_PHONE_MENU_REGEX_FILTER = "\\b[1-3]\\b";
    private final String DELETION_MENU_REGEX_FILTER = "\\b[1-2]\\b";
    private final String STRING_REGEX_FILTER = "^[\\p{L}]{2,}$";
    private final String EMAIL_REGEX_FILTER = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
    private final String PHONE_NUMBER_REGEX_FILTER = "^375\\s*\\d\\s*\\d\\s*\\d\\s*\\d\\s*\\d\\s*\\d\\s*\\d\\s*\\d\\s*\\d\\s*$";
    private final String STRING_INCORRECT_MESSAGE
            = "Длина должна быть минимум 2 символа и содержать только буквы";
    private final String EMAIL_INCORRECT_MESSAGE
            = "Введен некорректный адрес электронной почты";
    private final String PHONE_NUMBER_INCORRECT_MESSAGE
            = "Введен некорректный номер телефона," +
            " номер должен начинаться с 375 и содержать ровно 12 цифр с любым количеством пробелов";
    private final Integer MAX_ROLES_COUNT = 3;
    private final Integer MAX_PHONE_NUMBERS_COUNT = 3;

    @Override
    public String getMainMenuChoice() {
        printMainMenu();
        return getUserInput(MAIN_MENU_REGEX_FILTER,
                "Введите целое число от 1 до 4, " +
                        "для окончания работы с программой введите Выход", "");
    }

    @Override
    public UserDto getUserCreationData() {
        String firstName = getUserInput(STRING_REGEX_FILTER,
                STRING_INCORRECT_MESSAGE,
                "Введите имя пользователя: ");
        String lastName = getUserInput(STRING_REGEX_FILTER,
                STRING_INCORRECT_MESSAGE,
                "Введите фамилию пользователя: ");
        String email = getUserInput(EMAIL_REGEX_FILTER,
                EMAIL_INCORRECT_MESSAGE,
                "Введите email пользователя: ");
        List<String> roles = getRecordsList("Добавление ролей", MAX_ROLES_COUNT, STRING_REGEX_FILTER,
                STRING_INCORRECT_MESSAGE, new ArrayList<>());
        List<String> phones = getRecordsList("Добавление телефонов", MAX_PHONE_NUMBERS_COUNT, PHONE_NUMBER_REGEX_FILTER,
                PHONE_NUMBER_INCORRECT_MESSAGE, new ArrayList<>());

        return UserDto.builder()
                .firstName(firstName)
                .lastName(lastName)
                .email(email)
                .roles(roles)
                .phones(phones)
                .build();
    }

    @Override
    public String getEmailOfUser() {
        String email = null;
        System.out.println("Выберите действие:");
        System.out.println("1 - Ввести email пользователя");
        System.out.println("2 - Вернуться в основное меню");
        String input = getUserInput(DELETION_MENU_REGEX_FILTER,
                "Введите 1 или 2", "");
        if (input.equals("1")) {
            email = getUserInput(EMAIL_REGEX_FILTER,
                    EMAIL_INCORRECT_MESSAGE, "Введите email: ");
        }
        return email;
    }

    @Override
    public void printUserInformation(UserDto user) {
        System.out.println("Информация о пользователе:");
        System.out.print("Имя: ");
        System.out.println(user.getFirstName());
        System.out.print("Фамилия: ");
        System.out.println(user.getLastName());
        System.out.print("Email: ");
        System.out.println(user.getEmail());
        System.out.print("Роли: ");
        System.out.println(user.getRoles());
        System.out.print("Телефоны: ");
        System.out.println(user.getPhones());
    }

    @Override
    public UserDto getUserUpdateData(UserDto userDto) {
        System.out.print("Текущее имя пользователя: ");
        System.out.println(userDto.getFirstName());
        String firstName = getUserInput(STRING_REGEX_FILTER,
                STRING_INCORRECT_MESSAGE,
                "Введите новое имя пользователя: ");
        System.out.print("Текущая фамилия пользователя: ");
        System.out.println(userDto.getLastName());
        String lastName = getUserInput(STRING_REGEX_FILTER,
                STRING_INCORRECT_MESSAGE,
                "Введите новую фамилию пользователя: ");
        System.out.print("Текущий email пользователя: ");
        System.out.println(userDto.getEmail());
        String email = getUserInput(EMAIL_REGEX_FILTER,
                EMAIL_INCORRECT_MESSAGE,
                "Введите новый email пользователя: ");
        List<String> roles = getRecordsList("Добавление ролей", MAX_ROLES_COUNT, STRING_REGEX_FILTER,
                STRING_INCORRECT_MESSAGE, userDto.getRoles());
        List<String> phones = getRecordsList("Добавление телефонов", MAX_PHONE_NUMBERS_COUNT,
                PHONE_NUMBER_REGEX_FILTER, PHONE_NUMBER_INCORRECT_MESSAGE, userDto.getPhones());

        return UserDto.builder()
                .firstName(firstName)
                .lastName(lastName)
                .email(email)
                .roles(roles)
                .phones(phones)
                .build();
    }

    private void printMainMenu() {
        System.out.println("Выберите действие:");
        System.out.println("1 - Создать нового пользователя");
        System.out.println("2 - Редактировать пользователя");
        System.out.println("3 - Удалить пользователя");
        System.out.println("4 - Вывести информацию о пользователе");
        System.out.println("Выход - Выйти из программы");
    }

    private String getUserInput(String regexFilter,
                                String incorrectInputMessage, String inputMessage) {
        System.out.print(inputMessage);
        String userInput = scanner.nextLine().toLowerCase();

        while (!userInput.matches(regexFilter)) {
            System.out.println(incorrectInputMessage);
            System.out.print(inputMessage);
            userInput = scanner.nextLine().toLowerCase();
        }

        return userInput;
    }

    private List<String> getRecordsList(String action, int maxCount, String regexFiler, String incorrectMessage, List<String> existingRecords) {
        List<String> records = new ArrayList<>(List.copyOf(existingRecords));
        while (true) {
            System.out.println(action);
            System.out.print("Список добавленных записей: ");
            System.out.println(records);
            System.out.println("Выберите действие:");
            System.out.println("1 - Добавить запись");
            System.out.println("2 - Удалить запись");
            System.out.println("3 - Продолжить");
            String userChoice = getUserInput(ROLE_PHONE_MENU_REGEX_FILTER,
                    "Введите  1, 2 или 3", "");
            if (userChoice.equals("1")) {
                if (records.size() == maxCount) {
                    System.out.println("Количество записей не может быть больше " + maxCount);
                } else {
                    String record = getUserInput(regexFiler,
                            incorrectMessage, "Введите запись: ");
                    records.add(record);
                }
            } else if (userChoice.equals("2")) {
                if (records.isEmpty()) {
                    System.out.println("Не добавлено ни одной записи");
                } else {
                    String record = getUserInput(regexFiler,
                            incorrectMessage, "Введите запись для удаления: ");
                    records.remove(record);
                }
            } else {
                if (records.isEmpty()) {
                    System.out.println("Количество записей не может быть меньше 1");
                } else {
                    break;
                }
            }
        }
        return records;
    }

}
