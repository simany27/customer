package telran.ashkelon2020.customer.dto;

import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AccountDto {
	String id;
	String nameAccount;
	CustomerDto customer;
	Set<SubscriberDto> subscribers;
}
