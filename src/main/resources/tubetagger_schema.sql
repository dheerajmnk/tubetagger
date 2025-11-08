create table if not exists video (
	video_id int generated always as identity primary key,
	url varchar(100) not null,
	title varchar(200) not null
);
