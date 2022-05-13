public class SubCat {

    private int subCatId;
    private String subCatName;
    private CategoryClass category;

    public SubCat(int subCatId, String subCatName, CategoryClass category) {
        this.subCatId = subCatId;
        this.subCatName = subCatName;
        this.category = category;
    }

    public int getSubCatId() {
        return subCatId;
    }

    public void setSubCatId(int subCatId) {
        this.subCatId = subCatId;
    }

    public String getSubCatName() {
        return subCatName;
    }

    public void setSubCatName(String subCatName) {
        this.subCatName = subCatName;
    }

    public CategoryClass getCategory() {
        return category;
    }

    public void setCategory(CategoryClass category) {
        this.category = category;
    }

    @Override
    public String toString(){
        return category.getCatName() +" :: "+subCatName;
    }
}
