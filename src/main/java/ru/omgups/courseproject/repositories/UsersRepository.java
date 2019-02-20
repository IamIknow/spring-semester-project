package ru.omgups.courseproject.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.omgups.courseproject.entities.User;

public interface UsersRepository extends JpaRepository<User, Long> {
}
