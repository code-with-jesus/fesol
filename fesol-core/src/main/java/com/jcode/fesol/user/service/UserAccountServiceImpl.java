package com.jcode.fesol.user.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jcode.fesol.user.dto.UserDto;
import com.jcode.fesol.user.mapper.UserMapper;
import com.jcode.fesol.user.model.UserAccount;
import com.jcode.fesol.user.repository.UserAccountRepository;

@Service("userDetailsService")
public class UserAccountServiceImpl implements UserAccountService {

    @Autowired
    private UserAccountRepository userRepository;

    @Transactional(readOnly = true)
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserAccount> userAccountOptional = this.findByUsername(username);
        if (userAccountOptional.isEmpty()) {
            throw new UsernameNotFoundException("Invalid username or password");
        }
        return UserDetailsMapper.build(userAccountOptional.get());
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<UserAccount> getUserAccount(long id) {
        return userRepository.findById(id);
    }

    @Transactional
    @Override
    public UserAccount save(UserAccount userAccount) {
        return userRepository.save(userAccount);
    }

    @Transactional(readOnly = true)
    @Override
    public List<UserDto> findAll() {
        List<UserAccount> users = userRepository.findAll(); 
        return users.stream().map(UserMapper.INSTANCE::userAccountToUserDto).collect(Collectors.toList());
    }

    @Override
    public Optional<UserAccount> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

}
