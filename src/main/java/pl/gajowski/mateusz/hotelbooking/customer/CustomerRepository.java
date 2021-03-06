package pl.gajowski.mateusz.hotelbooking.customer;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<CustomerEntity, Long> {
    Optional<CustomerEntity> findOneByEmailIgnoreCase(String email);

    Optional<CustomerEntity> findOneByLogin(String login);
}
