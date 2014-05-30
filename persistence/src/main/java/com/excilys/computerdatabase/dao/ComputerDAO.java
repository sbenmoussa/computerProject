package com.excilys.computerdatabase.dao;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.excilys.computerdatabase.model.Computer;

@Repository
public interface ComputerDAO extends PagingAndSortingRepository<Computer, Long>{

	public Computer findOne(Long id);

	Page<Computer>  findAll(Pageable pageable);

	public Page<Computer> findAllByName(String name, Pageable pageable) ;
}
