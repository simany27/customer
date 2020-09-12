package telran.ashkelon2020.customer.service;

import java.util.Set;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import telran.ashkelon2020.customer.dao.AccountRepository;
import telran.ashkelon2020.customer.dao.CustomerRepository;
import telran.ashkelon2020.customer.dao.SubscriberRepository;
import telran.ashkelon2020.customer.dto.AccountDto;
import telran.ashkelon2020.customer.dto.CustomerDto;
import telran.ashkelon2020.customer.dto.EntityNotFoundException;
import telran.ashkelon2020.customer.dto.SubscriberDto;
import telran.ashkelon2020.customer.model.Account;
import telran.ashkelon2020.customer.model.Customer;
import telran.ashkelon2020.customer.model.Subscriber;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	AccountRepository accountRepository;
	@Autowired
	CustomerRepository customerRepository;
	@Autowired
	SubscriberRepository subscriberRepository;
	@Autowired
	ModelMapper modelMapper;
	
	@Override
	@Transactional
	public boolean addAccount(AccountDto accountDto) {
		if(accountRepository.existsById(accountDto.getId())) return false;
		// Customer
		CustomerDto customerDto = accountDto.getCustomer();
		addCustomer(customerDto);
		Customer customer = modelMapper.map(customerDto,Customer.class);
		// Subscriber
		Set<SubscriberDto> subscribersDto = accountDto.getSubscribers();
		subscribersDto.stream().forEach(s -> addSubscriber(s,accountDto.getId()));
		Set<Subscriber> subscribers = subscribersDto.stream().map(s -> modelMapper.map(s, Subscriber.class)).collect(Collectors.toSet());
		
		Account account = new Account(accountDto.getId(), accountDto.getNameAccount(), customer, subscribers);
		accountRepository.save(account);
		return true;
	}
	@Override
	public AccountDto findAccountById(String id) {
		Account account = accountRepository.findById(id).orElseThrow(()->new EntityNotFoundException());
		return modelMapper.map(account, AccountDto.class);
	}
	@Override
	@Transactional
	public AccountDto removeAccount(String id) {
		Account account = accountRepository.findById(id).orElseThrow(()->new EntityNotFoundException());
		accountRepository.delete(account);
		return modelMapper.map(account, AccountDto.class);
	}
	@Override
	@Transactional
	public AccountDto updateAccount(String id, String nameAccount) {
		Account account = accountRepository.findById(id).orElseThrow(()->new EntityNotFoundException());
		account.setNameAccount(nameAccount);
		return modelMapper.map(account, AccountDto.class);
	}
	@Override
	@Transactional
	public boolean addCustomer(CustomerDto customerDto) {
		if(customerRepository.existsById(customerDto.getLoginCustomer())) return false;
		Customer customer = modelMapper.map(customerDto, Customer.class);
		customerRepository.save(customer);
		return true;
	}
	@Override
	public CustomerDto findCustomerByLoginCustomer(String loginCustomer) {
		Customer customer = customerRepository.findById(loginCustomer).orElseThrow(()->new EntityNotFoundException());
		return modelMapper.map(customer, CustomerDto.class);
	}
	@Override
	@Transactional
	public CustomerDto removeCustomer(String loginCustomer) {
		Customer customer = customerRepository.findById(loginCustomer).orElseThrow(()->new EntityNotFoundException());
		accountRepository.deleteByCustomerLoginCustomer(loginCustomer);
		customerRepository.delete(customer);
		return modelMapper.map(customer, CustomerDto.class);
	}
	@Override
	@Transactional
	public CustomerDto updateCustomer(String loginCustomer, String nameCustomer) {
		Customer customer = customerRepository.findById(loginCustomer).orElseThrow(()->new EntityNotFoundException());
		customer.setNameCustomer(nameCustomer);
		return modelMapper.map(customer, CustomerDto.class);
	}
	@Override
	@Transactional
	public boolean addSubscriber(SubscriberDto subscriberDto, String accountId) {
		if(subscriberRepository.existsById(subscriberDto.getLoginSubscriber())) return false;
		Subscriber subscriber = modelMapper.map(subscriberDto, Subscriber.class);
		subscriberRepository.save(subscriber);
		Account account = accountRepository.findById(accountId).orElseThrow(()->new EntityNotFoundException());
		if(account.getSubscribers().contains(subscriber)) return true;
		else {
			account.getSubscribers().add(subscriber);
			accountRepository.save(account);
		}
		return true;
	}
	@Override
	public SubscriberDto findSubscriberByLoginSubscriber(String loginSubscriber) {
		Subscriber subscriber = subscriberRepository.findById(loginSubscriber).orElseThrow(()->new EntityNotFoundException());
		return modelMapper.map(subscriber, SubscriberDto.class);
	}
	@Override
	@Transactional
	public SubscriberDto removeSubscriber(String loginSubscriber) {
		Subscriber subscriber = subscriberRepository.findById(loginSubscriber).orElseThrow(()->new EntityNotFoundException());
		subscriberRepository.delete(subscriber);
		return modelMapper.map(subscriber, SubscriberDto.class);
	}
	@Override
	@Transactional
	public SubscriberDto updateSubscriber(String loginSubscriber, String nameSubscriber) {
		Subscriber subscriber = subscriberRepository.findById(loginSubscriber).orElseThrow(()->new EntityNotFoundException());
		subscriber.setNameSubscriber(nameSubscriber);
		return modelMapper.map(subscriber, SubscriberDto.class);
	}
	@Override
	@Transactional(readOnly = true)
	public Iterable<AccountDto> findAccountsByCustomer(String loginCustomer) {
		Customer customer = customerRepository.findById(loginCustomer).orElseThrow(()->new EntityNotFoundException());
		return customer.getAccounts().stream().map(a -> modelMapper.map(a, AccountDto.class)).collect(Collectors.toSet());
	}
	@Override
	@Transactional(readOnly = true)
	public Iterable<SubscriberDto> findSubscribersByCustomer(String loginCustomer) {
		return subscriberRepository.findByAccountsCustomerLoginCustomer(loginCustomer)
				.map(s -> modelMapper.map(s, SubscriberDto.class))
				.collect(Collectors.toSet());
	}
	
	
	
}
