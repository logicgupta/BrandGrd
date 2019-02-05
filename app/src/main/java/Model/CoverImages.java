package Model;

import android.net.Uri;

public class CoverImages {

    String imageUrl;
    String imageDesc;
    String title;
    String imageKey;

    public CoverImages() {

    }

    public CoverImages(String imageUrl, String imageDesc, String title) {
        this.imageUrl = imageUrl;
        this.imageDesc = imageDesc;
        this.title = title;
    }

    public CoverImages(String imageUrl, String imageDesc, String title, String imageKey) {
        this.imageUrl = imageUrl;
        this.imageDesc = imageDesc;
        this.title = title;
        this.imageKey = imageKey;
    }

    public String getImgKey() {
        return imageKey;
    }

    public void setImgKey(String imageKey) {
        this.imageKey = imageKey;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getImageDesc() {
        return imageDesc;
    }

    public void setImageDesc(String imageDesc) {
        this.imageDesc = imageDesc;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
