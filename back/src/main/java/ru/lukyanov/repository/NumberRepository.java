package ru.lukyanov.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.lukyanov.model.PhoneNumber;

public interface NumberRepository extends JpaRepository<PhoneNumber, Long> {
}
