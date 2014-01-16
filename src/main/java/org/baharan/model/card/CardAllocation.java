package org.baharan.model.card;

import org.baharan.model.BaseEntity;
import org.baharan.model.counter.Counter;

public class CardAllocation extends BaseEntity{
	private CardRequest cardRequestId;
    private Counter counter;
    private String allocationDate;
    private int total;
    private int serialFrom;
    private int serialTo;
    private String description;
}
