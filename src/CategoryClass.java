public class CategoryClass {

    private int catId;
    private String catName;
    private SubCat[] subCats;

    public CategoryClass(String catName, int catId) {
        this.catName = catName;
        this.catId = catId;
    }

    public int getCatId() {
        return catId;
    }

    public void setCatId(int catId) {
        this.catId = catId;
    }

    public String getCatName() {
        return catName;
    }

    public void setCatName(String catName) {
        this.catName = catName;
    }

    public SubCat[] getSubCats() {
        return subCats;
    }

    public void setSubCats(SubCat[] subCats) {
        this.subCats = subCats;
    }

    @Override
    public String toString(){
        return catName;
    }
}
