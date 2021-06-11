package com.ftn.PreporukaOdevneKombinacije.helper;


import com.ftn.PreporukaOdevneKombinacije.dto.UserDTO;
import com.ftn.PreporukaOdevneKombinacije.model.User;

public class UserMapper implements MapperInterface<User, UserDTO>{

    @Override
    public User toEntity(UserDTO dto) {
        return new User(dto.getEmail(),dto.getPassword(), dto.getPol(), dto.getVisina(), dto.getRamena(), dto.getKukovi(), dto.getStruk());
    }

    @Override
    public UserDTO toDto(User entity) {
        return new UserDTO(entity.getUsername(), entity.getPassword());
    }
}
