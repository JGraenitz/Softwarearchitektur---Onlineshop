package com.rsr.email_microservice.core.domain.service.interfaces;

import com.rsr.email_microservice.core.domain.model.User;
import com.rsr.email_microservice.port.utils.UnknownUserIdException;

import java.util.UUID;

public interface IUserService {

    void saveUser(User user);

    User getUserById(UUID userId) throws UnknownUserIdException;

}
