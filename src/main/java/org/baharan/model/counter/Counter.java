package org.baharan.model.counter;

import org.baharan.model.BaseEntity;
import org.baharan.model.location.Location;
import org.baharan.model.personel.Personel;

public class Counter extends BaseEntity{
	private String code;
    private String name;
    private Location location;
    private Personel responsible;
    private String phoneNumber;
    private String address;
    private String description;
}
