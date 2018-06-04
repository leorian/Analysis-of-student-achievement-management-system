# 创建学生成绩管理系统数据库student score manager --> ssm
create database ssm;

# 使用ssm数据库
use ssm;

# 创建石牌高级中学课程类别表sp_course	1
# 课程编号 c_id   (起始编号C1001)
# 课程名称 c_name
create table sp_course(
	c_id varchar(10) not null primary key,
	c_name varchar(32) not null,
	delflag boolean default false
);

# 创建石牌高级中学教师基本信息表(国内)	2
# 编号 t_id
# 姓名 t_name
# 密码 t_pass
# 性别 t_sex
# 身份证号 t_idcard
# 家庭地址 t_address
# 民族 t_nation
# 政治面貌 t_political_status
# 入职时间 t_job_time
# 最高学历 t_edu_bg
# 毕业院校 t_graduate_institutions
# 联系电话 t_phone
# 教师证件照 t_pic_path
create table sp_teacher(
	t_id varchar(10) not null primary key,
	t_name varchar(32) not null,
	t_pass varchar(32),
	t_sex varchar(4) not null,
	t_nation varchar(25) not null,
	t_pol_stat varchar(10) not null,
    t_idcard varchar(20) not null,
	t_address varchar(100) not null,
	t_job_time date not null,
	t_edu_bg varchar(15) not null,
	t_gra_ins varchar(50) not null,
	t_phone varchar(15) not null,
	t_course varchar(10),
	t_pic_path varchar(100),
	delflag boolean default false,
	foreign key(t_course) references sp_course(c_id)
);

# 创建石牌高级中学班级类别表	4
# 班级编号 cs_id 主键 
# 入学日期 cs_date
# 班级序号 cs_class
# 班主任 （教师编号）外键 关联教师表
create table sp_classes(
	cs_id varchar(10) not null primary key,
	cs_date int not null,
	cs_class int not null,
	cs_adviser varchar(10) not null,
	delflag boolean default false,
	foreign key(cs_adviser) references sp_teacher(t_id)
);

# 石牌高级中学学生基本信息表（国内）	3
# 学号 s_id 主键
# 姓名 s_name
# 密码 s_pass
# 性别 s_sex
# 身份证号 s_idcard
# 家庭住址 s_address
# 民族 s_nation
# 政治面貌 s_nation
# 入学时间 s_school_time
# 户口类别 s_household
# 就读方式 s_schoolmethod
# 证件照路径 s_pic_path
create table sp_student(
	s_id int auto_increment primary key,
	s_name varchar(32) not null,
	s_pass varchar(32) not null,
	s_sex varchar(4) not null,
	s_idcard varchar(20) not null,
	s_address varchar(100) not null,
	s_nation varchar(25) not null,
	s_pol_stat varchar(10) not null,
	s_school_time varchar(10) not null,
	s_household varchar(10) not null,
	s_schoolmethod varchar(10) not null,
	s_class varchar(10),
	s_pic_path varchar(100),
	delflag boolean default false,
	foreign key(s_class) references sp_classes(cs_id)
);


# 创建石牌高级中学课程安排表	5
# 班级编号 ctc_classid
# 授课教师编号 ctc_teacherid
# 授课科目 ctc_courseid
# 学期：从1起始
create table sp_ctc(
	ctc_classid varchar(10) not null,
	ctc_teacherid varchar(10) not null,
	ctc_courseid varchar(10) not null,
	ctc_xueqi int not null,
	primary key(ctc_classid, ctc_teacherid, ctc_courseid,ctc_xueqi),
	foreign key(ctc_classid) references sp_classes(cs_id),
	foreign key(ctc_teacherid) references sp_teacher(t_id),
	foreign key(ctc_courseid) references sp_course(c_id)
);


# 创建石牌高级中学学生分班表	6
# 班级编号 sc_classid
# 学生编号 sc_stuid
create table sp_sc(
	sc_classid varchar(10) not null,
	sc_stuid int not null,
	primary key(sc_classid, sc_stuid),
	foreign key(sc_classid) references sp_classes(cs_id),
	foreign key(sc_stuid) references sp_student(s_id)
);


# 创建石牌高级中学成绩记录表  7
# 班级编号 sr_gradeid
# 学生编号 sr_stuid
# 科目编号 sr_courseid
# 考试时间 sr_examtime
# 考试类型 sr_examtype
# 学期 sr_xueqi
# 成绩 sr_score
create table sp_score_record(
	sr_gradeid varchar(10) not null,
	sr_stuid int not null,
	sr_courseid varchar(10) not null,
	sr_examtime varchar(10) not null,
	sr_examtype varchar(10) not null,
	sr_xueqi int not null,
	sr_score double not null,
	primary key(sr_stuid, sr_courseid, sr_examtime),
	foreign key(sr_gradeid) references sp_classes(cs_id),
	foreign key(sr_stuid) references sp_student(s_id),
	foreign key(sr_courseid) references sp_course(c_id)
);

# 创建管理员表 8
# 用户身份ID admin_id
# 用户名 admin_name
# 用户密码 admin_pass
# 登录IP reg_ip
# 登录时间 reg_time
create table sp_admin(
	admin_id varchar(10) not null primary key,
	admin_name varchar(32) not null,
	admin_pass varchar(32) not null,
	reg_ip varchar(32),
	reg_time varchar(32),
	rel_t_id varchar(10),
	foreign key(rel_t_id) references sp_teacher(t_id)
);

insert into sp_admin(admin_id, admin_name, admin_pass)
values('M20140001','校长','2804118302');

# 记录指针
create table sp_record(
	categorys varchar(4) primary key,
	count int
);

INSERT INTO sp_record(categorys, count) VALUES('S',0);

INSERT INTO sp_record(categorys, count) VALUES('T',0);

INSERT INTO sp_record(categorys, count) VALUES('C',0);

INSERT INTO sp_record(categorys, count) VALUES('G',0);

#################################################################
# 初始化
#################################################################
insert into sp_course(c_id, c_name) values('C10001','语文');
insert into sp_course(c_id, c_name) values('C10002','数学');
insert into sp_course(c_id, c_name) values('C10003','英语');
insert into sp_course(c_id, c_name) values('C10004','物理');
insert into sp_course(c_id, c_name) values('C10005','化学');
insert into sp_course(c_id, c_name) values('C10006','生物');

insert into sp_course(c_id, c_name) values('C10007','地理');
insert into sp_course(c_id, c_name) values('C10008','政治');
insert into sp_course(c_id, c_name) values('C10009','历史');
insert into sp_course(c_id, c_name) values('C10010','音乐');
UPDATE sp_record SET count = 10 WHERE categorys = 'C';

insert into sp_teacher values('T10001','谢中贵', '1', '男', '汉族', '中共党员', 
'340822199108084316', '安徽省合肥市', '2014-04-18', '本科', '安徽科技学院', '18365073582', 'C10001', null, false);

insert into sp_teacher values('T10002','洛天', '1', '男', '汉族', '中共党员', 
'340822199108084317', '安徽省合肥市', '2014-04-18', '本科', '安徽科技学院', '18365073582', 'C10002', null, false);

insert into sp_teacher values('T10003','洛熙', '1', '男', '汉族', '中共党员', 
'340822199108084318', '安徽省合肥市', '2014-04-18', '本科', '安徽科技学院', '18365073582', 'C10003', null, false);

insert into sp_teacher values('T10004','落夕', '1', '女', '汉族', '中共党员', 
'340822199108084319', '安徽省合肥市', '2014-04-18', '本科', '安徽科技学院', '18365073582', 'C10004', null, false);

insert into sp_teacher values('T10005','张三', '1', '男', '汉族', '中共党员', 
'340822199108084320', '安徽省合肥市', '2014-04-18', '本科', '安徽科技学院', '18365073582', 'C10005', null, false);

insert into sp_teacher values('T10006','李四', '1', '男', '汉族', '中共党员', 
'340822199108084321', '安徽省合肥市', '2014-04-18', '本科', '安徽科技学院', '18365073582', 'C10006', null, false);

insert into sp_teacher values('T10007','王五', '1', '男', '汉族', '中共党员', 
'340822199108084322', '安徽省合肥市', '2014-04-18', '本科', '安徽科技学院', '18365073582', 'C10007', null, false);

insert into sp_teacher values('T10008','王世博', '1', '男', '汉族', '中共党员', 
'340822199108084323', '安徽省合肥市', '2014-04-18', '本科', '安徽科技学院', '18365073582', 'C10008', null, false);

insert into sp_teacher values('T10009','刘伯温', '1', '男', '汉族', '中共党员', 
'340822199108084324', '安徽省合肥市', '2014-04-18', '本科', '安徽科技学院', '18365073582', 'C10009', null, false);

insert into sp_teacher values('T10010','赵六', '1', '男', '汉族', '中共党员', 
'340822199108084325', '安徽省合肥市', '2014-04-18', '本科', '安徽科技学院', '18365073582', 'C10010', null, false);
UPDATE sp_record SET count = 10 WHERE categorys = 'T';

insert into sp_classes values('G10001', 2014, 1, 'T10001',false);

insert into sp_classes values('G10002', 2013, 1, 'T10001',false);

insert into sp_classes values('G10003', 2012, 1, 'T10001',false);

insert into sp_classes values('G10004', 2011, 1, 'T10001',false);

insert into sp_classes values('G10005', 2014, 2, 'T10001',false);
UPDATE sp_record SET count = 5 WHERE categorys = 'G';

insert into sp_student values(1, '孙悟空', '1570000000', '男', '340822199108080000', 
'安徽省合肥市', '少数民族', '共青团员', '2014-04-18', '城市', '走读生', 'G10001', null,false);
insert into sp_student values(2, '猪八戒', '1570000001', '男', '340822199108080001', 
'安徽省合肥市', '少数民族', '共青团员', '2014-04-18', '城市', '走读生', 'G10001', null,false);
insert into sp_student values(3, '沙悟净', '1570000002', '男', '340822199108080002', 
'安徽省合肥市', '少数民族', '共青团员', '2014-04-18', '城市', '走读生', 'G10001', null,false);
insert into sp_student values(4, '刘备', '1570000003', '男', '340822199108080003', 
'安徽省合肥市', '少数民族', '共青团员', '2014-04-18', '城市', '走读生', 'G10001', null,false);
insert into sp_student values(5, '关羽', '1570000004', '男', '340822199108080004', 
'安徽省合肥市', '少数民族', '共青团员', '2014-04-18', '城市', '走读生', 'G10001', null,false);
insert into sp_student values(6, '张飞', '1570000005', '男', '340822199108080005', 
'安徽省合肥市', '少数民族', '共青团员', '2014-04-18', '城市', '走读生', 'G10001', null,false);
insert into sp_student values(7, '赵云', '1570000006', '男', '340822199108080006', 
'安徽省合肥市', '少数民族', '共青团员', '2014-04-18', '城市', '走读生', 'G10001', null,false);
insert into sp_student values(8, '曹操', '1570000007', '男', '340822199108080007', 
'安徽省合肥市', '少数民族', '共青团员', '2014-04-18', '城市', '走读生', 'G10001', null,false);
insert into sp_student values(9, '诸葛亮', '1570000008', '男', '340822199108080008', 
'安徽省合肥市', '少数民族', '共青团员', '2014-04-18', '城市', '走读生', 'G10001', null,false);
insert into sp_student values(10, '曹植', '1570000009', '男', '340822199108080009', 
'安徽省合肥市', '少数民族', '共青团员', '2014-04-18', '城市', '走读生', 'G10001', null,false);
insert into sp_student values(11, '曹丕', '1570000010', '男', '340822199108080010', 
'安徽省合肥市', '少数民族', '共青团员', '2014-04-18', '城市', '走读生', 'G10001', null,false);
insert into sp_student values(12, '王云', '1570000011', '男', '340822199108080011', 
'安徽省合肥市', '少数民族', '共青团员', '2014-04-18', '城市', '走读生', 'G10001', null,false);
insert into sp_student values(13, '李渊', '1570000012', '男', '340822199108080012', 
'安徽省合肥市', '少数民族', '共青团员', '2014-04-18', '城市', '走读生', 'G10001', null,false);
insert into sp_student values(14, '李世民', '1570000013', '男', '340822199108080013', 
'安徽省合肥市', '少数民族', '共青团员', '2014-04-18', '城市', '走读生', 'G10001', null,false);
insert into sp_student values(15, '李元吉', '1570000014', '男', '340822199108080014', 
'安徽省合肥市', '少数民族', '共青团员', '2014-04-18', '城市', '走读生', 'G10001', null,false);
insert into sp_student values(16, '李元霸', '1570000015', '男', '340822199108080015', 
'安徽省合肥市', '少数民族', '共青团员', '2014-04-18', '城市', '走读生', 'G10001', null,false);
insert into sp_student values(17, '姜子牙', '1570000016', '男', '340822199108080016', 
'安徽省合肥市', '少数民族', '共青团员', '2014-04-18', '城市', '走读生', 'G10001', null,false);
insert into sp_student values(18, '武吉', '1570000017', '男', '340822199108080017', 
'安徽省合肥市', '少数民族', '共青团员', '2014-04-18', '城市', '走读生', 'G10001', null,false);
UPDATE sp_record SET count = 18 WHERE categorys = 'S';
