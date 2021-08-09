DROP TABLE if exists players;
create table players (id bigint not null auto_increment, birth_date date, name varchar(255) not null, position integer, team_id bigint, primary key (id));