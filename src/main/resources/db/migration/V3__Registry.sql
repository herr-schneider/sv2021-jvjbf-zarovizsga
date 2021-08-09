alter table players add constraint const foreign key (team_id) references teams (id);
