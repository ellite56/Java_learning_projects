package school21.schoolbe2.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.UUID;

@Schema(description = "Данные изображения товара")
public class ImageDto {
    @Schema(description = "UUID изображения", example = "e9dbca33-2a9c-4a73-b1d7-f211c5363d06")
    private UUID imageId;
    @Schema(description = "Изображение в виде массива байт")
    private byte[] images;

    public ImageDto(){}

    public ImageDto(UUID imageId,byte[] images){
        this.imageId=imageId;
        this.images=images;
    }

    public byte[] getImages() {
        return images;
    }

    public UUID getImage_id() {
        return imageId;
    }

    public void setImageId(UUID imageId) {
        this.imageId = imageId;
    }

    public void setImages(byte[] images) {
        this.images = images;
    }
}
