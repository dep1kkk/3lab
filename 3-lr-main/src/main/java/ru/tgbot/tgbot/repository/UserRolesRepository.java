package ru.tgbot.tgbot.repository;

import ru.tgbot.tgbot.model.UserRole;
import org.springframework.data.repository.CrudRepository;

public interface UserRolesRepository extends CrudRepository<UserRole, Long> {
}
