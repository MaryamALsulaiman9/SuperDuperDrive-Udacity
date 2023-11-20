package com.udacity.jwdnd.course1.cloudstorage.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class File {

    private Integer fileId;
    private String fileName;
    private String contentType;
    private long fileSize;
    private byte[] fileData;
    private Integer userId;
}
