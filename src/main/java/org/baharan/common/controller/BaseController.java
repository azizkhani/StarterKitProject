package org.baharan.common.controller;

import java.util.Date;

import org.baharan.core.QueryResult;
import org.baharan.dao.IGenericRepository;
import org.baharan.model.BaseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.support.SessionStatus;

public class BaseController {
	
	@Autowired
	private IGenericRepository genericRepo;
	
	@RequestMapping("/list")
	public @ResponseBody QueryResult<BaseEntity> list(String searchFilter,String order,int pageNumber, int pageSize) {
		return new QueryResult<BaseEntity>(pageNumber, genericRepo.count(),  pageSize, genericRepo.getAll(searchFilter, order, pageNumber,pageSize));
	}

	@RequestMapping("/load/{Id}")
	public @ResponseBody BaseEntity load(@PathVariable int Id) {
		return (BaseEntity)genericRepo.loadByEntityId(Id);
	}

	@RequestMapping("/delete/{Id}")
	public @ResponseBody Boolean delete(@PathVariable int Id) {
		genericRepo.deleteByEntityId(Id);//Will Check if UnSuc Raise Error
		return true;
	}

	@RequestMapping("/save")
	public @ResponseBody Boolean save(@ModelAttribute("entity") BaseEntity entity, BindingResult result,SessionStatus status) {
		if (result.hasErrors()) {
			return false;
		} else {
			entity.setUpdatedDate(new Date());
			entity.setCreatedDate(new Date());
			status.setComplete();
			if (entity.getId() > 0) 
				genericRepo.update(entity);
			 else 
				genericRepo.add(entity);
			return true;
		}
	}
}
