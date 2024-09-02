package ru.lukyanov.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.lukyanov.model.PhoneNumber;
@Repository
public interface NumberRepository extends JpaRepository<PhoneNumber, Long> {
}
