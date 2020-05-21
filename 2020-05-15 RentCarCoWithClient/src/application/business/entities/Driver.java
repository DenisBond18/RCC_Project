package application.business.entities;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@EqualsAndHashCode(of = "idDriver")
@Getter@Setter

@Entity
@Table(name = "rcc_driver")
public class Driver {

	@Id
	@Column(length = 100)
	private String idDriver; // identification document
	
	private String firstName;
	private String lastName;
	private LocalDate birthDate;
	private String phoneNumber;
	private String email;
	
	/*
	 * @JsonIgnore
	 * 
	 * @OneToMany(mappedBy = "driver", cascade = CascadeType.ALL) private
	 * Set<RentRecord> records = new HashSet<>();
	 */

	public Driver(String idDriver, String firstName, String lastName, LocalDate birthDate, String phoneNumber,
			String email) {
		this.idDriver = idDriver;
		this.firstName = firstName;
		this.lastName = lastName;
		this.birthDate = birthDate;
		this.phoneNumber = phoneNumber;
		this.email = email;
	}

	
	
}
