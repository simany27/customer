package telran.ashkelon2020.customer.service;

import telran.ashkelon2020.customer.dto.AccountDto;
import telran.ashkelon2020.customer.dto.CustomerDto;
import telran.ashkelon2020.customer.dto.SubscriberDto;

public interface CustomerService {

	boolean addAccount(AccountDto accountDto);
	AccountDto findAccountById(String id);
	AccountDto removeAccount(String id);
	AccountDto updateAccount(String id, String nameAccount);
	
	boolean addCustomer(CustomerDto customerDto);
	CustomerDto findCustomerByLoginCustomer(String loginCustomer);
	CustomerDto removeCustomer(String loginCustomer);
	CustomerDto updateCustomer(String loginCustomer, String nameCustomer);
	
	boolean addSubscriber(SubscriberDto subscriberDto, String accountId);
	SubscriberDto findSubscriberByLoginSubscriber(String loginSubscriber);
	SubscriberDto removeSubscriber(String loginSubscriber);
	SubscriberDto updateSubscriber(String loginSubscriber, String nameSubscriber);
	
	//поиск Аккаунта по какому-либо параметру в Кастомере
	Iterable<AccountDto> findAccountsByCustomer(String loginCustomer);
	
	//поиск нескольких сабскрайберов по любому параметрам используя "like".
	Iterable<SubscriberDto> findSubscribersByCustomer(String loginCustomer);
}
