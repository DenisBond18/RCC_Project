package client;

import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.RequestEntity;
import org.springframework.web.client.RestTemplate;

import application.business.dto.CarDto;
import application.business.dto.CarNumber;
import application.business.dto.DriverDto;
import application.business.dto.DriverNumber;
import application.business.dto.ModelDto;
import application.business.dto.RccConfigDto;
import application.business.dto.RentRecordDto;
import application.business.services.AnalistService;
import application.business.services.BossService;
import application.business.services.ClerkService;
import application.business.services.ManagerService;


public class RentCarServiceProxyImpl implements ClerkService, BossService, ManagerService, AnalistService{

	private String token;
	private static final String URL = "http://localhost:8080";
	private static final String AUTHORIZATION = "Authorization";
	private static final String BEARER = "Bearer ";	
	private RestTemplate restTemplate = new RestTemplate();

	public RentCarServiceProxyImpl(String token) {
		this.token = token;
	}

	@Override
	public DriverDto addDriver(DriverDto driverDto) {
		RequestEntity<?> request = null;
		try {
			request = RequestEntity.post(new URI(URL + "/clerk/add_driver"))
					.header(AUTHORIZATION, BEARER + token)
					.body(driverDto);
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		return restTemplate.exchange(request, DriverDto.class).getBody();
	}

	@Override
	public DriverDto getDriver(String idDriver) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DriverDto correctDriver(DriverDto driverDto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<RentRecordDto> getRecordsDelayedByDriver(String idDriver) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<RentRecordDto> getRecordsByDriverInRentDateRange(String idDriver, LocalDate start, LocalDate finish) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ModelDto> getModelsAvailable() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CarDto> getCarsAvailable() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CarDto> getCarsAvailableByModel(String idModel) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CarDto getCar(String idCar) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RentRecordDto getRecord(int idRecord) {
		RequestEntity<?> request = null;
		try {
			//String login = Base64.getEncoder().encodeToString("mylogin:myPwd".getBytes());
			request = RequestEntity.get(new URI(URL + "/clerk/get_record?idRecord=" + idRecord))
					.header(AUTHORIZATION, BEARER + token)
					.build();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		return restTemplate.exchange(request, RentRecordDto.class).getBody();
	}

	@Override
	public RentRecordDto rentCar(String idCar, String idDriver, LocalDate plannedReturnDate) {
		RequestEntity<?> request = null;
		try {
			request = RequestEntity.put(new URI(URL + "/clerk/rent_car?idCar=" + idCar + "&idDriver=" + idDriver + "&plannedReturnDate=" + plannedReturnDate))
					.header(AUTHORIZATION, BEARER + token)
					.build();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		return restTemplate.exchange(request, RentRecordDto.class).getBody();
	}

	@Override
	public RentRecordDto receiveCar(int idRecord, double fuelInTankPercent, boolean noDamages) {
		RequestEntity<?> request = null;
		try {
			request = RequestEntity.put(new URI(URL + "/clerk/get_record?idRecord=" + idRecord + "&fuelInTankPercent=" + fuelInTankPercent + "&noDamages=" + noDamages))
					.header(AUTHORIZATION, BEARER + token)
					.build();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		return restTemplate.exchange(request, RentRecordDto.class).getBody();
	}

	@Override
	public RccConfigDto setConfig(RccConfigDto config) {
		RequestEntity<?> request = null;
		try {
			request = RequestEntity.post(new URI(URL + "/boss/set_config"))
					.header(AUTHORIZATION, BEARER + token)
					.body(config);
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		return restTemplate.exchange(request, RccConfigDto.class).getBody();
	}

	@Override
	public ModelDto addModel(ModelDto model) {
		RequestEntity<?> request = null;
		try {
			request = RequestEntity.post(new URI(URL + "/boss/add_model"))
					.header(AUTHORIZATION, BEARER + token)
					.body(model);
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		return restTemplate.exchange(request, ModelDto.class).getBody();
	}

	@Override
	public ModelDto setModelDailyRate(String idModel, double dailyRate) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CarDto setCarWrittenOff(String idCar) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<RentRecordDto> getRecordsUnreceived() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<RentRecordDto> getRecordsUnclosed() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CarDto> getAllCars() {
		ParameterizedTypeReference<ArrayList<CarDto>> listCars 
		= new ParameterizedTypeReference<ArrayList<CarDto>>() {};
		RequestEntity<?> request = null;
		try {
			request = RequestEntity.get(new URI(URL + "/manager/get_all_cars"))
					.header(AUTHORIZATION, BEARER + token)
					.build();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		return restTemplate.exchange(request, listCars).getBody();
	}

	@Override
	public List<CarDto> getCarsInUse() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CarDto> getCarsWrittenOff() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CarDto addCar(CarDto car) {
		RequestEntity<?> request = null;
		try {
			request = RequestEntity.post(new URI(URL + "/manager/add_car"))
					.header(AUTHORIZATION, BEARER + token)
					.body(car);
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		return restTemplate.exchange(request, CarDto.class).getBody();
	}

	@Override
	public RentRecordDto closeRecord(int idRecord, double damagesRepairPrice) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CarDto> getCarsByModel(String idModel) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<RentRecordDto> getRecordsInRentDateRange(LocalDate from, LocalDate to) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<DriverNumber> getMostSloopyDrivers() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public double findIncomeInDateRange(LocalDate from, LocalDate to) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<RentRecordDto> getRecordsDelayedInRentDateRange(LocalDate from, LocalDate to) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ModelDto> getAllModels() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ModelDto> getModelsEmpty() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<RentRecordDto> getRecordsByModelInDateRange(String idModel, LocalDate from, LocalDate to) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public double getTotalInDateRange(LocalDate from, LocalDate to) {
		RequestEntity<?> request = null;
		try {
			request = RequestEntity.get(new URI(URL + "/analist/get_total_in_date_range?from=" + from + "&to=" + to))
					.header(AUTHORIZATION, BEARER + token)
					.build();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		return restTemplate.exchange(request, Double.class).getBody();
	}

	@Override
	public List<DriverNumber> getMostPayDrivers() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<RentRecordDto> getRecordsByCarInDateRange(String idCar, LocalDate from, LocalDate to) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public double getTotalByCarInDateRange(String idCar, LocalDate from, LocalDate to) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getTotalByModelInDateRange(String idModel, LocalDate from, LocalDate to) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getIncomeByCarInDateRange(String idCar, LocalDate from, LocalDate to) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<CarNumber> getMostUsedCars() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public double getTotalDamagesRepairPriceInDateRange(LocalDate from, LocalDate to) {
		// TODO Auto-generated method stub
		return 0;
	}


}
