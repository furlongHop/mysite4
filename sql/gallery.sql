--1. table gallery 생성
create table gallery(
    no number,
    user_no number,
    content varchar2(1000),
    filePath varchar2(500),
    orgName varchar2(200),
    saveName varchar2(500),
    fileSize number,
    primary key(no),
    constraint gallery_fk foreign key(user_no) 
    references users(no) 
);

--2.gallery의 sequence 생성
create sequence seq_gallery_no 
increment by 1 
start with 1  
nocache;

--3. select문
select * from gallery;

--4. 테이블, 시퀀스 삭제
drop table gallery;
drop sequence seq_gallery_no;
