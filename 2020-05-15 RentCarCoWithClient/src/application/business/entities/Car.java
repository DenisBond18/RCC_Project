package application.business.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
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
@EqualsAndHashCode(of = "idCar")
@Getter@Setter


@Entity
@Table(name = "rcc_car")
public class Car {

	@Id
	@Column(length = 100)
	private String idCar;
	
	@Column(columnDefinition = "enum('AVAILABLE', 'IN_USE', 'OUT_OF_SERVICE', 'WRITTEN_OFF')")
	@Enumerated(EnumType.STRING)
	private CarState state;
	
	@ManyToOne
	private Model model;
	
	/*
	 * @JsonIgnore
	 * 
	 * @OneToMany(mappedBy = "car", cascade = CascadeType.ALL) private
	 * Set<RentRecord> records = new HashSet<>();
	 */

	public Car(String idCar, Model model) {
		super();
		this.idCar = idCar;
		this.model = model;
	}
	
}
