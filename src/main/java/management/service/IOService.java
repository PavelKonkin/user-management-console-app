package management.service;

import management.model.dto.UserDto;

public interface IOService {

    String getMainMenuChoice();

    UserDto getUserCreationData();

    String getEmailOfUser();

    void printUserInformation(UserDto user);

    UserDto getUserUpdateData(UserDto userDto);
}
