package it.redhat.demo.stateful;

import java.util.concurrent.TimeUnit;

import javax.ejb.Stateful;
import javax.ejb.StatefulTimeout;

@Stateful
@StatefulTimeout(unit = TimeUnit.MINUTES, value = 5)
public class RememberService {
	
	private Integer value = 0;
	
	public void add() {
		value++;
	}
	
	public Integer getValue() {
		return value;
	}

}
