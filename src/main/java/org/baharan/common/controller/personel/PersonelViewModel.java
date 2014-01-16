package org.baharan.common.controller.personel;

import org.baharan.model.personel.Personel;

public class PersonelViewModel extends Personel {

	public String getLocationName() {
		return this.getLocation().getName();
	}
}
