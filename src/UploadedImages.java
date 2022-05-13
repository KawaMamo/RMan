import javafx.scene.image.ImageView;

public class UploadedImages {
    private ImageView imageView;
    private String imageName;
    private String pah;

    public UploadedImages(ImageView imageView, String imageName, String pah) {
        this.imageView = imageView;
        this.imageName = imageName;
        this.pah = pah;
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

    public String getPah() {
        return pah;
    }

    public void setPah(String pah) {
        this.pah = pah;
    }
}
