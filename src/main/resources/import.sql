insert into team(name)values('Azul');
insert into team(name)values('Vermelho');
insert into team(name)values('Amarelo');

insert into team_pokemon(pokemon_id, team_id)values(1, 1);
insert into team_pokemon(pokemon_id, team_id)values(2, 1);
insert into team_pokemon(pokemon_id, team_id)values(3, 1);

insert into team_pokemon(pokemon_id, team_id)values(4, 2);
insert into team_pokemon(pokemon_id, team_id)values(5, 2);
insert into team_pokemon(pokemon_id, team_id)values(6, 2);

insert into team_pokemon(pokemon_id, team_id)values(7, 3);
insert into team_pokemon(pokemon_id, team_id)values(8, 3);
insert into team_pokemon(pokemon_id, team_id)values(9, 3);

insert into file(name, username, status, chunks_number) values('Arquivo_1.txt', 'tatiane.morais', 'SUCESS', 4);

insert into split_file(file_id, chunk_number) values(1, 1);
insert into split_file(file_id, chunk_number) values(1, 2);
insert into split_file(file_id, chunk_number) values(1, 3);
insert into split_file(file_id, chunk_number) values(1, 4);
