package org.azizkhani.model.card;

import org.azizkhani.model.BaseEntity;
import org.azizkhani.model.counter.Counter;

public class CardAllocation extends BaseEntity{
	private CardRequest cardRequestId;
    private Counter counter;
    private String allocationDate;
    private int total;
    private int serialFrom;
    private int serialTo;
    private String description;
}
