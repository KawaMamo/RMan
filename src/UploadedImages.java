import javafx.scene.image.ImageView;

public class UploadedImages {
    private ImageView imageView;
    private String imageName;
    private String newName;

    public String getNewName() {
        return newName;
    }

    public void setNewName(String newName) {
        this.newName = newName;
    }

    public UploadedImages(ImageView imageView, String imageName, String newName) {
        this.imageView = imageView;
        this.imageName = imageName;
        this.newName = newName;
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
