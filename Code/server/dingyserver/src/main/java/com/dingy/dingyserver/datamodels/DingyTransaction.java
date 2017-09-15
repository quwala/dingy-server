package com.dingy.dingyserver.datamodels;

import java.util.List;

public class DingyTransaction {

	private Integer amount_payed;
	private DingyUser payed_by;
	private List<DingyUser> participants;
	
	private String description;//(supermarket, new chair, anything)
}
