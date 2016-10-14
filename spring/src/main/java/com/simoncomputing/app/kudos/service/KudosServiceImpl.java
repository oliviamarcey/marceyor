package com.simoncomputing.app.kudos.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.simoncomputing.app.kudos.entity.Kudos;
import com.simoncomputing.app.kudos.repository.KudosRepository;

@Service
public class KudosServiceImpl implements KudosService {

	@Autowired
	private KudosRepository kudosRepository;

	@Override
	public Kudos save(Kudos be) {
		return kudosRepository.save(be);
	}

	@Override
	public Kudos findOne(Long id) {
		return kudosRepository.findOne(id);
	}

	@Override
	public List<Kudos> findAll() {
		return kudosRepository.findAll();
	}

	@Override
	public void delete(Long id) {
		kudosRepository.delete(id);
	}
	
	@Override 
	public List<Kudos> findAllByOrderByEntryDateTimeDesc() {
		return kudosRepository.findAllByOrderByEntryDateTimeDesc();
	}
}
