package pl.gajowski.mateusz.hotelbooking.customer;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import pl.gajowski.mateusz.hotelbooking.base.db.BaseEntity;

import javax.persistence.*;

@Entity
@Table(name = "CUSTOMER")
@Getter
@Setter
@ToString
public class CustomerEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false)
    private long id;
    @Column(nullable = false)
    private String login;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String surname;
    @Column(nullable = false)
    private String phoneNumber;
    @Column(nullable = false)
    private String email;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private String token;
}
