package org.azizkhani.model.counter;

import org.azizkhani.model.BaseEntity;
import org.azizkhani.model.location.Location;
import org.azizkhani.model.personel.Personel;

public class Counter extends BaseEntity{
	private String code;
    private String name;
    private Location location;
    private Personel responsible;
    private String phoneNumber;
    private String address;
    private String description;
}
