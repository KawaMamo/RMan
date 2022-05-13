import javafx.scene.image.ImageView;

public class UploadedImages {
    private ImageView imageView;
    private String imageName;

    public UploadedImages(ImageView imageView, String imageName) {
        this.imageView = imageView;
        this.imageName = imageName;
    }


    public ImageView getImageView() {

        imageView.setPreserveRatio(true);
        imageView.setFitHeight(40);
        return imageView;
    }

    public void setImageView(ImageView imageView) {
        this.imageView = imageView;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

}
