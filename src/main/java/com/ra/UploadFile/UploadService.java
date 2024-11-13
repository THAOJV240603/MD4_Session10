package com.ra.UploadFile;

import com.google.cloud.storage.*;
import jakarta.servlet.ServletContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class UploadService {
    private final String bucketName = "data-5672e.firebasestorage.app";

    @Autowired
    private ServletContext servletContext;

    //Lấy Storage trong FirebaseConfig
    @Autowired
    private Storage storage;

    //Upload file lên server
    public String uploadFileToServer(MultipartFile file){
        //Tạo đường dẫn đến thư mục upload
        String uploadPath = servletContext.getRealPath("/upload");
        //Kiểm tra xem thư mục có tồn tại hay không, nếu chưa có thì tạo thư mục mới
        File fileUpload = new File(uploadPath);
        if(!fileUpload.exists()){
            //Tạo thư mục mkdir
            fileUpload.mkdir();
        }
        //Nối thêm thời gian để file không bị trùng file đã có
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss");
        //fileName để nối file
        String fileName = dateTimeFormatter.format(LocalDateTime.now())+file.getOriginalFilename();
        try {
            FileCopyUtils.copy(file.getBytes(),new File(uploadPath+File.separator+fileName));
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
        return uploadFileFromServerToFireBase(uploadPath+File.separator+fileName);
    }

    //Upload file lên firebase
    public String uploadFileFromServerToFireBase(String filePath){
        Path localPath = Paths.get(filePath);
        //Lấy ra tên ảnh
        String fileName = localPath.getFileName().toString();
        //bucketName: Up lên bucket của firebase
        BlobId blobId = BlobId.of(bucketName, fileName);
        BlobInfo blobInfo = BlobInfo.newBuilder(blobId).build();
        //Thiết lập quyền truy cập công cộng
        List<Acl> acls = new ArrayList<>();
        acls.add(Acl.of(Acl.User.ofAllUsers(), Acl.Role.READER));
        blobInfo = blobInfo.toBuilder().setAcl(acls).build();
        try {
            Blob blob = storage.create(blobInfo, Files.readAllBytes(localPath));
            return blob.getMediaLink();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
