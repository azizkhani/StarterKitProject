package org.azizkhani.model.card;

import org.azizkhani.model.BaseEntity;
import org.azizkhani.model.baseInfo.BaseInformation;
import org.azizkhani.model.device.*;
import org.azizkhani.model.location.Location;

public class CardRequest extends BaseEntity{
	private Location location;
    private String requestDate;
    private int total;
    private int serialFrom;
    private int serialTo;
    private String description;
    
    private BaseInformation cardType;
}
