package org.baharan.model.card;

import org.baharan.model.BaseEntity;
import org.baharan.model.baseInfo.BaseInformation;
import org.baharan.model.device.*;
import org.baharan.model.location.Location;

public class CardRequest extends BaseEntity{
	private Location location;
    private String requestDate;
    private int total;
    private int serialFrom;
    private int serialTo;
    private String description;
    
    private BaseInformation cardType;
}
