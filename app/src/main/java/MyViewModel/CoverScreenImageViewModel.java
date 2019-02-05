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
    }

    public MutableLiveData<List<CoverImages>> getMutableLiveDataCoverImages(){
        return mutableLiveDataImages;
    }
}
