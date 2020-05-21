package application.security.services;

import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import application.security.dto.AccountDto;
import application.security.entities.Account;
import application.security.repositories.AccountMongoRepository;

@Transactional
@Service
public class SecurityService implements ISecurityService {
	
	@Autowired AccountMongoRepository accountRepo;
	@Autowired DtoSecurityService dtoService;
	@Autowired PasswordEncoder encoder;

	private Account getAccount(String login) {
		Account account = accountRepo.findById(login).orElse(null);
		if (account == null)
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Login "+login+" not found");
		return account;
	}

	
	  //@Override 
	  public AccountDto addUser(String login, String password) { 
		  if(accountRepo.existsById(login)) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Duplicated login "+login);
		  Account account = new Account(login, encoder.encode(password), "ROLE_USER");
		  accountRepo.save(account); 
		  return dtoService.accountDto(account); }
	 
	
	@Override
	public AccountDto addAccount(String login, String password, String role) {
		
		if (accountRepo.existsById(login))
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Duplicated login "+login);
		
		Account account = new Account(login, encoder.encode(password), "ROLE_"+role);
		accountRepo.save(account);
		return dtoService.accountDto(account);
	}
	
	@Override
	public AccountDto grantRole(String login, String role) {
		
		Account account = getAccount(login);
		account.getRoles().add("ROLE_"+role);
		accountRepo.save(account);
		
		return dtoService.accountDto(account);
	}
	
	
	@Override
	public AccountDto depriveRole(String login, String role) {
		
		Account account = getAccount(login);
		
		Set<String> roles = account.getRoles();
		if (roles.size() < 2)
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Account "+login+" has only one role. Accounts without roles are not allowed.");
		String prefixedRole = "ROLE_"+role;
		if (!roles.contains(prefixedRole))
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Account "+login+" has no role "+role);
		roles.remove(prefixedRole);
		
		accountRepo.save(account);
		return dtoService.accountDto(account);
	}

	@Override
	public Set<String> getRolesByLogin(String login) {
		return getAccount(login).getRoles();
	}
	
	
	@Override
	public List<AccountDto> getAllAccounts() {
		return dtoService.accountDtoList(accountRepo.findAll());
	}

	@Override
	public AccountDto removeAccount(String login) {
		Account account = getAccount(login);
		accountRepo.deleteById(login);
		return dtoService.accountDto(account);
	}
	
	//@Override
	public AccountDto removeUser(String login) {
		Account account = getAccount(login);
		Set<String> roles = account.getRoles();
		if (roles.contains("ROLE_ADMIN") || roles.contains("ROLE_MANAGER"))
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Removal not authorized");
		accountRepo.deleteById(login);
		return dtoService.accountDto(account);
	}
}
