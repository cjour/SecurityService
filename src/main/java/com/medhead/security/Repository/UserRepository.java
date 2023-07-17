package com.medhead.security.Repository;

import com.medhead.security.entity.InternalUser;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<InternalUser, Integer> {
    InternalUser findByUsername(String username);
}
