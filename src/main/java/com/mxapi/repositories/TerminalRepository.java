package com.mxapi.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.mxapi.modals.Terminal;

@Repository("terminalRepository")
public interface TerminalRepository extends CrudRepository<Terminal, Long>{
	
	@Query("select t from Terminal t where 1=1 and ( t.logic = ?1 and t.version = ?2 ) ")
	Terminal findByAnyVariable(long logic, String version);
	
	Terminal findByLogic(long logic);
	

}
