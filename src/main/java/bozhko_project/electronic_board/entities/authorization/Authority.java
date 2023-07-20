package bozhko_project.electronic_board.entities.authorization;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

/** Модель ролей */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "authorities")
public class Authority {

    /** Логин */
    @Id
    @Column(name = "login")
    private String login;

    /** Роль */
    private String authority;

}