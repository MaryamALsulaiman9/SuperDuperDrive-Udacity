package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mappers.FileMapper;
import com.udacity.jwdnd.course1.cloudstorage.models.File;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FileService {

    private final FileMapper fileMapper;

    public int uploadFile(MultipartFile file, Integer userId) throws IOException {
        return fileMapper.insertFile(new File(null, file.getOriginalFilename(), file.getContentType(), file.getSize(), file.getBytes(), userId));
    }

    public File getFile(Integer fileId) {
        return fileMapper.getFileById(fileId);
    }

    public int deleteFile(Integer fileId) {
        return fileMapper.deleteFile(fileId);
    }

    public List<File> getFilesFor(Integer userId) {
        return fileMapper.getFilesFor(userId);
    }

    public boolean isFileExists(String fileName, Integer userId) {
        return fileMapper.getFile(fileName, userId) != null;
    }

}
