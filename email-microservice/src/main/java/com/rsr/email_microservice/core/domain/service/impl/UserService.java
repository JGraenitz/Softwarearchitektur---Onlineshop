package com.rsr.email_microservice.core.domain.service.impl;

import com.rsr.email_microservice.core.domain.model.User;
import com.rsr.email_microservice.core.domain.service.interfaces.IUserRepository;
import com.rsr.email_microservice.core.domain.service.interfaces.IUserService;
import com.rsr.email_microservice.port.utils.UnknownUserIdException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@Service
@AllArgsConstructor
public class UserService implements IUserService {

    private final IUserRepository userRepository;

    @Override
    public void saveUser(User user) {
        userRepository.save(user);
        log.info("Saved User: " + user);
    }

    @Override
    public User getUserById(UUID userId) throws UnknownUserIdException {
        return userRepository.findById(userId).orElseThrow(UnknownUserIdException::new);
    }
}
