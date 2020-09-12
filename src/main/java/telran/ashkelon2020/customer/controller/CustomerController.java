package telran.ashkelon2020.customer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import telran.ashkelon2020.customer.dto.AccountDto;
import telran.ashkelon2020.customer.dto.CustomerDto;
import telran.ashkelon2020.customer.dto.SubscriberDto;
import telran.ashkelon2020.customer.service.CustomerService;

@RestController
public class CustomerController {

	@Autowired
	CustomerService customerService;
	
	@PostMapping("/account")
	public boolean addAccount(@RequestBody AccountDto accountDto) {
		return customerService.addAccount(accountDto);
	}
	
	@GetMapping("/account/{id}")
	public AccountDto findAccountById(@PathVariable String id) {
		return customerService.findAccountById(id);
	}
	
	@DeleteMapping("/account/{id}")
	public AccountDto removeAccount(@PathVariable String id) {
		return customerService.removeAccount(id);
	}
	
	@PutMapping("/account/{id}/name/{nameAccount}")
	public AccountDto updateAccount(@PathVariable String id, @PathVariable String nameAccount) {
		return customerService.updateAccount(id, nameAccount);
	}
	
	@PostMapping("/customer")
	public boolean addCustomer(@RequestBody CustomerDto customerDto) {
		return customerService.addCustomer(customerDto);
	}
	
	@GetMapping("/customer/{loginCustomer}")
	public CustomerDto findCustomerByLoginCustomer(@PathVariable String loginCustomer) {
		return customerService.findCustomerByLoginCustomer(loginCustomer);
	}
	
	@DeleteMapping("/customer/{loginCustomer}")
	public CustomerDto removeCustomer(@PathVariable String loginCustomer) {
		return customerService.removeCustomer(loginCustomer);
	}
	
	@PutMapping("/customer/{loginCustomer}/name/{nameCustomer}")
	public CustomerDto updateCustomer(@PathVariable String loginCustomer, @PathVariable String nameCustomer) {
		return customerService.updateCustomer(loginCustomer, nameCustomer);
	}
	
	@PostMapping("/subscriber/{accountId}")
	public boolean addSubscriber(@RequestBody SubscriberDto subscriberDto, @PathVariable String accountId) {
		return customerService.addSubscriber(subscriberDto, accountId);
	}
	
	@GetMapping("/subscriber/{loginSubscriber}")
	public SubscriberDto findSubscriberByLoginSubscriber(@PathVariable String loginSubscriber) {
		return customerService.findSubscriberByLoginSubscriber(loginSubscriber);
	}
	
	@DeleteMapping("/subscriber/{loginSubscriber}")
	public SubscriberDto removeSubscriber(@PathVariable String loginSubscriber) {
		return customerService.removeSubscriber(loginSubscriber);
	}
	
	@PutMapping("/subscriber/{loginSubscriber}/name/{nameSubscriber}")
	public SubscriberDto updateSubscriber(@PathVariable String loginSubscriber, @PathVariable String nameSubscriber) {
		return customerService.updateSubscriber(loginSubscriber, nameSubscriber);
	}
	
	@GetMapping("/accounts/customer/{loginCustomer}")
	public Iterable<AccountDto> findAccountsByCustomer(@PathVariable String loginCustomer){
		return customerService.findAccountsByCustomer(loginCustomer);
	}
	
	@GetMapping("/subscriber/customer/{loginCustomer}")
	public Iterable<SubscriberDto> findSubscribersByCustomer(@PathVariable String loginCustomer){
		return customerService.findSubscribersByCustomer(loginCustomer);
	}
	
	
	

	
	
	
	
	
}
