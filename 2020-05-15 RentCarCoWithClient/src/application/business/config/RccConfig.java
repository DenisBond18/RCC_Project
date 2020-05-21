package application.business.config;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data

@Entity
@Table(name = "rcc_config")
public class RccConfig {

	@Id
	int id = 1;
	
	private double delayPenalty;
	private double fuelPrice;
	
	public RccConfig(double delayPenalty, double fuelPrice) {
		this.delayPenalty = delayPenalty;
		this.fuelPrice = fuelPrice;
	}
	
}
