package architeture.hexagonal.adapters.outbound.storage;

import org.springframework.web.multipart.MultipartFile;

public interface ImageUploaderPort {
    String uploadImage(MultipartFile file);
}
