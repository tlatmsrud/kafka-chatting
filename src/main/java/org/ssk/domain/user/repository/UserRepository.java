package org.ssk.domain.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.ssk.domain.user.domain.User;

/**
 * title        :
 * author       : sim
 * date         : 2023-11-27
 * description  :
 */
public interface UserRepository extends JpaRepository<User, String> {
}
