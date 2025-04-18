package com.threedgarden.api.service;


import com.threedgarden.api.dto.AddressRequest;
import com.threedgarden.api.model.Address;
import com.threedgarden.api.model.Users;
import com.threedgarden.api.repository.AddressRepository;
import com.threedgarden.api.repository.UsersRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;


@Service
@RequiredArgsConstructor
public class UsersService {

    private final UsersRepository usersRepository;
    private final AddressRepository addressRepository;
    private final PasswordEncoder passwordEncoder;

    public List<Users> getAllUsers() {
        return usersRepository.findAll();
    }

    public Users getUserById(Long id) {
        return usersRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
    }

    public Users addUser(Users user) {
        if (usersRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new RuntimeException("Email already exist");
        }

        user.setRegistrationDate(LocalDateTime.now());
        user.setRole("user");
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return usersRepository.save(user);
    }

    @Transactional
    public Users addUsersAddress(Long id, AddressRequest addressRequest) {
        Users user = getUserById(id);

        Address address = new Address();
        address.setStreet(addressRequest.getStreet());
        address.setExtNumber(addressRequest.getExtNumber());
        address.setIntNumber(addressRequest.getIntNumber());
        address.setNeighborhood(addressRequest.getNeighborhood());
        address.setZipCode(addressRequest.getZipCode());
        address.setCity(addressRequest.getCity());
        address.setState(addressRequest.getState());
        address.setUser(user);

        addressRepository.save(address);
        user.getAddresses().add(address);

        return user;
    }

    public Users deleteUserById(Long id) {
        Users user = getUserById(id);
        usersRepository.deleteById(id);
        return user;
    }

    public Users updatedUserById(Long id, Users updatedUser) {
        Users user = getUserById(id);
        if (updatedUser.getEmail() != null) user.setEmail(updatedUser.getEmail());
        if (updatedUser.getName() != null) user.setName(updatedUser.getName());
        if (updatedUser.getLastName() != null) user.setLastName(updatedUser.getLastName());
        if (updatedUser.getPhoneNumber() != null) user.setPhoneNumber(updatedUser.getPhoneNumber());
        if (updatedUser.getRole() != null) user.setRole(updatedUser.getRole());
        if (updatedUser.getPassword() != null && !updatedUser.getPassword().isEmpty()) {
            user.setPassword(passwordEncoder.encode(updatedUser.getPassword()));
        }

        return usersRepository.save(user);
    }

    public Users loginUser(String email, String password) {
        Users user = usersRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Invalid data"));

        if(!passwordEncoder.matches(password, user.getPassword())) {
            throw new RuntimeException("Invalid data");
        }

        return user;
    }
}
