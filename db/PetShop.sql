create table UserTbl(
UId int identity(1,1) primary key,
UName varchar(50),
UPass varchar(50)
);

create table CustomerTbl(
CustId int identity(1,1) primary key,
CustName varchar(50),
CustAdd varchar(50),
CustPhone varchar(50)
);

create table CategoryTbl(
CatId int identity(1,1) primary key,
CatDes varchar(50)
);

create table PetTbl(
PId int identity(1,1) primary key,
PName varchar(50),
PCat int foreign key references CategoryTbl(CatId),
Pqty int,
Pprice int
);

create table BillTbl(
BNum int,
BDate varchar(40),
CusId int foreign key references CustomerTbl(CustId),
UId int foreign key references UserTbl(UId),
amt int);


CREATE FUNCTION UF_SelectAllCustomer()
RETURNS table 
as return select*from CustomerTbl
Go


create function UF_SelectAllUser()
returns table
as return select*from UserTbl
go


create function UF_deleteACust(@CustId int)
returns table
as
return
select * from CustomerTbl where CustId = @CustId
go

create function UF_MaxId()
returns int
as 
begin
declare @id int = 0
select @id = Max(CustId) from CustomerTbl
return @id
end

create function UF_ChooseAUser(@UId int)
returns table
as
return
select * from UserTbl where UId = @UId
go

create function UF_MaxUId()
returns int
as 
begin
declare @id int = 0
select @id = Max(UId) from UserTbl
return @id
end

create function UF_SelectAllCat()
returns table
as return select*from CategoryTbl
go

create function UF_ChooseACat(@CatId int)
returns table
as
return
select * from CategoryTbl where CatId = @CatId
go

create function UF_MaxCatId()
returns int
as 
begin
declare @id int = 0
select @id = Max(CatId) from CategoryTbl
return @id
end

create function UF_SelectAllPet()
returns table
as return select*from PetTbl
go

create function UF_ChooseAPet(@PetId int)
returns table
as
return
select * from PetTbl where PId = @PetId
go

create function UF_MaxPId()
returns int
as 
begin
declare @id int = 0
select @id = Max(PId) from PetTbl
return @id
end