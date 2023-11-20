package com.udacity.jwdnd.course1.cloudstorage.mappers;

import com.udacity.jwdnd.course1.cloudstorage.models.File;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface FileMapper {

    @Insert("INSERT INTO FILES (filename, contenttype, filesize, filedata, userid) " +
            "VALUES(#{fileName}, #{contentType}, #{fileSize}, #{fileData}, #{userId})")
    @Options(useGeneratedKeys = true, keyProperty = "fileId")
    int insertFile(File file);

    @Update("UPDATE FILES SET filename=#{fileName}, contenttype=#{contentType}, filesize=#{fileSize}, userid=#{userId} WHERE fileid=#{fileId}")
    int updateFile(File file);

    @Delete("DELETE FROM FILES WHERE fileid=#{fileId}")
    int deleteFile(Integer fileId);

    @Select("SELECT * FROM FILES WHERE (userid = #{userId})")
    List<File> getFilesFor(Integer userId);

    @Select("SELECT * FROM FILES WHERE filename = #{fileName} AND userId = #{userId}")
    File getFile(String fileName, Integer userId);

    @Select("SELECT * FROM FILES WHERE fileid = #{fileId}")
    File getFileById(Integer fileId);

}
