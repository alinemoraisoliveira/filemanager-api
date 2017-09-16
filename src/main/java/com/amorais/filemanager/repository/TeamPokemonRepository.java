package com.amorais.filemanager.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.amorais.filemanager.domain.TeamPokemon;
import com.amorais.filemanager.domain.TeamPokemonId;

public interface TeamPokemonRepository extends PagingAndSortingRepository<TeamPokemon, TeamPokemonId>, JpaSpecificationExecutor<TeamPokemon> {
	
	List<TeamPokemon> findByIdTeamId(Integer teamId);

	@Modifying
	@Query(" delete from TeamPokemon teamPokemon where teamPokemon.id.teamId = :teamId ")
	void deleteByTeamId(@Param("teamId") Integer teamId);
	
}
