package com.ra.UploadFile;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.StorageOptions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

@Configuration
public class StorageConfig {
    private final String uploadFirebaseConfigPath
            = "G:\\ASUS Zenbook 11112024\\PTHAO\\MD4\\session10\\src\\main\\resources\\firebase-key.json";

    @Bean
    public com.google.cloud.storage.Storage storage() throws IOException {
        InputStream inputStream = Files.newInputStream(Paths.get(uploadFirebaseConfigPath));
        return StorageOptions.newBuilder().
                setCredentials(GoogleCredentials.fromStream(inputStream))
                .build().getService();
    }
}