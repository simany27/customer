package telran.ashkelon2020.customer.model;


import java.io.Serializable;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = {"loginSubscriber"})
@Entity
public class Subscriber implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 176541567586760179L;
	
	@Id
	String loginSubscriber;
	String nameSubscriber;
	@ManyToMany(mappedBy = "subscribers")
	Set<Account> accounts;
	
	public Subscriber(String loginSubscriber) {
		this.loginSubscriber = loginSubscriber;
	}
	
	
}
