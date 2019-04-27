package Model;

public class OffersList {

    String imageUrl;
    String title;
    String desc;
    String offerKey;

    public OffersList() {
    }

    public OffersList(String imageUrl, String title, String desc) {
        this.imageUrl = imageUrl;
        this.title = title;
        this.desc = desc;
    }
    public OffersList(String imageUrl, String title, String desc, String offerKey) {
        this.imageUrl = imageUrl;
        this.title = title;
        this.desc = desc;
        this.offerKey = offerKey;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getOfferKey() {
        return offerKey;
    }

    public void setOfferKey(String offerKey) {
        this.offerKey = offerKey;
    }


}
