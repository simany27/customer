package telran.ashkelon2020.customer.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = {"loginCustomer"})
@Entity
public class Customer implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 7568263318528861080L;
	
	@Id
	String loginCustomer;
	String nameCustomer;
	@OneToMany(mappedBy = "customer")
	Set<Account> accounts;
	
	public Customer(String loginCustomer) {
		this.loginCustomer = loginCustomer;
	}
	
	
}
