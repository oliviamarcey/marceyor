package com.simoncomputing.app.kudos.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.simoncomputing.app.kudos.entity.Kudos;

@Repository
public interface KudosRepository extends JpaRepository<Kudos, Long> { 
	public List<Kudos> findAllByOrderByEntryDateTimeDesc();
}
