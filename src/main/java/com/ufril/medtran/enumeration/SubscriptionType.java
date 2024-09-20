package com.ufril.medtran.enumeration;

public enum SubscriptionType {
	
	ONE_TIME(0), WEEKLY(7), BI_WEEKLY(14), MONTHLY(30);

	Integer value;

	SubscriptionType(Integer v) {
		this.value = v;
	}

	public Integer value() {
		return value;
	}

	public static SubscriptionType fromValue(Integer v) {
		for (SubscriptionType e : SubscriptionType.values()) {
			if (e.value.equals(v)) {
				return e;
			}
		}
		throw new IllegalArgumentException(v.toString());
	}


	}
