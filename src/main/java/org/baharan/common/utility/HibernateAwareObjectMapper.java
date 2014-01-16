package org.baharan.common.utility;

import org.codehaus.jackson.map.ObjectMapper;




public class HibernateAwareObjectMapper extends ObjectMapper {
	public HibernateAwareObjectMapper() {
		//HibernateModule hm = new HibernateModule();
		//registerModule(hm);
	}
	public void setPrettyPrint(boolean prettyPrint) {
	}
}