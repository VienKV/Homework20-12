package vn.hust.edu.homeworkw13;

public class ExampleItem {
    private String mImageUrl;
    private String mCreator;
    private String mEmail;

    public ExampleItem(String imageUrl, String creator, String email) {
        mImageUrl = imageUrl;
        mCreator = creator;
        mEmail = email;
    }

    public String getImageUrl() {
        return mImageUrl;
    }

    public String getCreator() {
        return mCreator;
    }

    public String getEmail() {
        return mEmail;
    }
}
