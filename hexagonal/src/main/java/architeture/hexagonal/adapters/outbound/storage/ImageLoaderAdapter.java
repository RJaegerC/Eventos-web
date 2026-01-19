package architeture.hexagonal.adapters.outbound.storage;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.model.GetUrlRequest;
import software.amazon.awssdk.core.sync.RequestBody;

import java.nio.ByteBuffer;
import java.util.UUID;

@Component
@Slf4j
@RequiredArgsConstructor
public class ImageLoaderAdapter {

    @Value("${aws.bucket.name}")
    private String bucketName;

    private final S3Client s3Client;

    private String uploadImg(MultipartFile multipartFile) {
        String filename = UUID.randomUUID() + "-" + multipartFile.getOriginalFilename();

        try {
            PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                    .bucket(bucketName)
                    .key(filename)
                    .contentType(multipartFile.getContentType())
                    .build();

            s3Client.putObject(
                    putObjectRequest,
                    RequestBody.fromBytes(multipartFile.getBytes())
            );

            GetUrlRequest urlRequest = GetUrlRequest.builder()
                    .bucket(bucketName)
                    .key(filename)
                    .build();

            return s3Client.utilities().getUrl(urlRequest).toString();
        } catch (Exception e) {
            log.error("erro ao subir arquivo: {}", e.getMessage());
            return "";
        }
    }

}
