package org.azizkhani.common.controller.personel;

import org.azizkhani.model.personel.Personel;

public class PersonelViewModel extends Personel {

	public String getLocationName() {
		return this.getLocation().getName();
	}
}
