package com.ftn.PreporukaOdevneKombinacije.service;

import com.ftn.PreporukaOdevneKombinacije.model.User;
import com.ftn.PreporukaOdevneKombinacije.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    public User findOne(Long id) {
        return repository.findById(id).orElse(null);
    }

    public User update(User user) {
        return repository.save(user);
    }

    public User create(User entity){
        User existUserMail = repository.findByUsername(entity.getUsername());
        User user = null;
        try{
            if (existUserMail != null) {
                throw new Exception("Email already exists");
            }
            user = repository.save(entity);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return user;
    }
}
