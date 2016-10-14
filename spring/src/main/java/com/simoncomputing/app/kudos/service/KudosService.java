package com.simoncomputing.app.kudos.service;

import java.util.List;

import com.simoncomputing.app.kudos.entity.Kudos;

public interface KudosService {
	public Kudos save(Kudos be);

	public Kudos findOne(Long id);

	public List<Kudos> findAll();

	public void delete(Long id);
	
	public List<Kudos> findAllByOrderByEntryDateTimeDesc();
}
