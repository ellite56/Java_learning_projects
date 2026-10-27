package school21.schoolbe2.persistence.entity;

import jakarta.persistence.*;

import java.util.UUID;
@Entity
@Table(name = "images")
public class ImageEntity {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID imageId;
    private byte[] images;

    public ImageEntity(){

    }

    public ImageEntity(byte[] images){
        this.images=images;
    }

    public byte[] getImages() {
        return images;
    }

    public UUID getImageId() {
        return imageId;
    }

    public void setImages(byte[] images) {
        this.images = images;
    }

    public void setImageId(UUID image_id) {
        this.imageId = image_id;
    }
}
