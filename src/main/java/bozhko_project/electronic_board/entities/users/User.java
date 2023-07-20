package bozhko_project.electronic_board.entities.users;

import bozhko_project.electronic_board.entities.ads.Announcement;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Data
@Table(name = "user", schema = "public")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;


	@Column(name = "login", unique = true)
	private String login;

	@Column(name = "password")
	private String password;

	@Column
	private String name;

	@Column
	private String surname;

	@Column(unique = true)
	private String email;

	@Column(unique = true)
	private String phone;

	@OneToOne(fetch = FetchType.LAZY, targetEntity = State.class)
	@JoinColumn(name="state_id")
	@JsonIgnore
	private State state;

	@OneToOne(fetch = FetchType.LAZY, targetEntity = Status.class)
	@JoinColumn(name="status_id")
	@JsonIgnore
	private Status statuses;

	@ManyToMany(cascade={CascadeType.MERGE})
	@JoinTable(name = "user_role", joinColumns = {@JoinColumn(name="user_id", referencedColumnName="id")},
			inverseJoinColumns = {@JoinColumn(name="role_id", referencedColumnName="id")})
	private List<Role> roles;

	@ManyToOne(cascade={CascadeType.MERGE})
	@JoinTable(name = "user_announcement", joinColumns = {@JoinColumn(name="announcement_id", referencedColumnName="id")},
			inverseJoinColumns = {@JoinColumn(name="user_id", referencedColumnName="id")})
	private Announcement announcement;

}

