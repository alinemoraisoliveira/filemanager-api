package com.amorais.filemanager.business;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.amorais.filemanager.domain.Pokemon;
import com.amorais.filemanager.domain.Team;
import com.amorais.filemanager.domain.TeamPokemon;
import com.amorais.filemanager.repository.TeamPokemonRepository;
import com.amorais.filemanager.repository.TeamRepository;

@Component
public class TeamBusiness {
	
	@Autowired
	private TeamRepository teamRepository;  
	
	@Autowired
	private TeamPokemonRepository teamPokemonRepository; 
	
	@Autowired
	private PokemonBusiness pokemonBusiness;  
	
	@Transactional
	public void save(Team team) {
		teamRepository.save(team);
	}
	
	@Transactional
	public void delete(Integer teamId){
		teamPokemonRepository.deleteByTeamId(teamId);
		teamRepository.delete(teamId);	
	}
	
	@Transactional
	public List<Pokemon> findPokemonByTeamId(Integer teamId) throws JSONException {
		
		List<TeamPokemon> listTeamPokemon = teamId != null ? teamPokemonRepository.findByIdTeamId(teamId) : null;
		
		List<Pokemon> pokemons = new ArrayList<Pokemon>();  
		if(listTeamPokemon != null) {
			for(TeamPokemon teamPokemon : listTeamPokemon) {
				String obj = pokemonBusiness.find(teamPokemon.getId().getPokemonId());
				
				if(obj != null) {
					JSONObject jsonObj = new JSONObject(obj);
			
					Pokemon pokemon = new Pokemon();
					pokemon.setId(teamPokemon.getId().getPokemonId());
					pokemon.setName(jsonObj.get("name").toString());
					pokemons.add(pokemon);
				}
			}
		}
		
		return pokemons;
	}
	
}

