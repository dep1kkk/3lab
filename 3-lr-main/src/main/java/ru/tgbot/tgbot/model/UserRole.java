package ru.tgbot.tgbot.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "user_roles")
@Entity(name = "user_roles")
public class UserRole {
    @Id
    @GeneratedValue(generator = "user_role_id_seq", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "user_role_id_seq", sequenceName = "user_role_id_seq", allocationSize = 1)
    private Long id;

    @Enumerated
    private UserAuthority userAuthority;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

}
