package MyViewModel;

import android.app.Application;

import android.content.Context;
import android.net.Uri;


import java.util.List;

import Model.CoverImages;
import Repositery.AddCoverFirebaseImage;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

public class CoverScreenImageViewModel extends AndroidViewModel {


    MutableLiveData<List<CoverImages>> mutableLiveDataImages=new MutableLiveData<>();
    MutableLiveData<Boolean>  status=new MutableLiveData<>();
    MutableLiveData<Boolean>  statusProductAdded=new MutableLiveData<>();
    AddCoverFirebaseImage addCoverImage;



    public CoverScreenImageViewModel( Application application) {
        super(application);
    }

    public void addCoverImages(String url, String title, String desc, Context context)
    {

        addCoverImage=AddCoverFirebaseImage.getInstance();

        addCoverImage.intializeFirebaseFirestore(context);

     addCoverImage.uploadCoverPhoto(Uri.parse(url),title,desc);       // This will also store images url and desc. to firestore

        mutableLiveDataImages=addCoverImage.getCoverImages();

        status=addCoverImage.getCoverImagesStatus();
    }

    public void addProductDetails(String imgUrl1,String imgUrl2,String productName,
                                  String productDesc,String productOriginalPrice,
                                  String productDiscountPrice,String productQuantity,
                                  String category,String pincode,Context context,String sellerName){

        addCoverImage=AddCoverFirebaseImage.getInstance();
        addCoverImage.intializeFirebaseFirestore(context);

        addCoverImage.addProductDetails(category,imgUrl1,imgUrl2,productName
                ,productDesc
                ,productOriginalPrice,productDiscountPrice,pincode,productQuantity,sellerName);

        statusProductAdded=addCoverImage.statusOfProductAdded();

    }

    public MutableLiveData<List<CoverImages>> getMutableLiveDataCoverImages(){
        return mutableLiveDataImages;
    }
    public MutableLiveData<Boolean> getStatus(){
        return status;
    }
    public MutableLiveData<Boolean> getProductAddStatus(){
        return statusProductAdded;
    }
}
