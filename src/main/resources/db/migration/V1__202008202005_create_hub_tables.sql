create table hub.file (
  id int not null primary key AUTO_INCREMENT,
  pipeline_id varchar(32),
  file_name varchar(64)
);

create table hub.meta (
  id int not null primary key AUTO_INCREMENT,
  pipeline_id varchar(32),
  description text,
  status varchar(32),
  ip varchar(32)
);

create table hub.note (
  id int not null primary key AUTO_INCREMENT,
  pipeline_id varchar(32),
  note_content text
);
