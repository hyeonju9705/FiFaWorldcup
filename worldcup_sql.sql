
create table NATION(
n_NAME varchar(20) primary key not null, 
h_LOC varchar(10), #개최지 지역
DIS int, # 개최지간 거리 
MOV_T int , #이동시간
DIF_T int , #시차 
S_VOL INT, #응원단 규모
WEATHER varchar(30), # 날씨 특성 어떤 날씨 선호하는지 
n_CO_COM int, #감독역량 
n_CO_NAM varchar(20) #감독이름
);

insert into  NATION(n_NAME , h_LOC ,DIS ,MOV_T,DIF_T,S_VOL,WEATHER,n_CO_COM,n_CO_NAM)
values('프랑스', '러시아' ,5614 ,8 ,3, 6542, 'rain', 9 ,'디디에데샹'),
('잉글랜드', '러시아' ,6221 ,8 ,2, 6820, 'humid', 7 ,'개러스사우스게이트'),
('벨기에', '러시아' ,5737 ,8 ,2, 1163, 'hot', 8 ,'로베르토마르티네스'),
('크로아티아', '러시아' ,5731 ,8 ,2, 408, 'sunny', 9 ,'즐라트코달리치');

CREATE TABLE MATCH_D(
m_DATE DATE, # 경기 날짜 
a_NATION varchar(20), # 국가 
b_NATION varchar(20), # 국가
a_SCORE INT, # 국가 득점
b_SCORE INT, # 국가 득점
m_DELAY boolean, #연장 진행 여부 
m_STAD varchar(20), #경기장
m_STAGE varchar(20)
);
insert into MATCH_D(m_DATE ,a_NATION ,b_NATION ,a_SCORE ,b_SCORE ,m_DELAY  ,m_STAD ,m_STAGE )
values('2018/07/11' , '프랑스' ,'벨기에' ,1 ,0, false, '상트페테르부르크 스타디움', '4강'),
('2018/07/12' , '크로아티아' ,'잉글랜드' ,2 ,1, true, '루즈니키 스타디움', '4강'),
('2018/07/16' , '프랑스' , '크로아티아' ,null ,null, null, '루즈니키 스타디움', '결승');

create table PLAYER(
p_FIRSTNAME varchar(20) , 
p_LASTNAME varchar(20), 
p_POM varchar(20) , #선수 위치
p_STAT int, # 선수 스텟
P_BACKNUM int, #선수 등번호
p_AGE INT, #선수 나이 
n_NAME varchar(20) , #국가 이름
p_COND double, #컨디션
p_INJ BOOLEAN #부상여부
);

create table weather(
nation varchar(40), #국가이름 
date varchar(10), #월 
low_avg_t double,  #최소 평년기온
heigh_avg_t double #최대 평년기온 
);


insert into weather(nation , date , low_avg_t ,heigh_avg_t )
values('러시아', '7월' , 12.5 ,23.1),
('프랑스', '7월' , 15.5 ,24.4),
('크로아티아', '7월' , 15.5 ,28.4),
('잉글랜드', '7월' , 13.7 ,22.3),
('벨기에', '7월' , 13.6 ,22.4);

