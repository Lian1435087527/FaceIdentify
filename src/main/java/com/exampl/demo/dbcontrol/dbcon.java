package com.exampl.demo.dbcontrol;

import java.nio.file.attribute.FileAttribute;
import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface dbcon {

@Select("select id,fPath as FPath,fName as FName,createTime as CreateTime from File_Attribute where fName=#{fileName}")
public ArrayList<FileAttribute> findFileDataByName(String fileName);

@Select("select id,fPath as FPath,fName as FName,createTime as CreateTime from File_Attribute")
public ArrayList<FileAttribute> findAll();
}
