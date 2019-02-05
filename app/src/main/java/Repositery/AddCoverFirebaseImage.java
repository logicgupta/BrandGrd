package Repositery;

import android.content.ContentResolver;
import android.content.Context;
import android.net.Uri;
import android.util.Log;
import android.webkit.MimeTypeMap;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.ArrayList;
import java.util.List;

import Model.CoverImages;
import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

public class AddCoverFirebaseImage {

     private static AddCoverFirebaseImage instance;
    FirebaseFirestore firestore;
        FirebaseStorage storage;
        CollectionReference collectionReference;
        Context context;
        boolean status;
        List<CoverImages> coverImagesList=new ArrayList<>();

        StorageReference storageReference ,storageReference2;

    public static AddCoverFirebaseImage getInstance() {
        if (instance==null){
            instance=new AddCoverFirebaseImage();
        }
        return instance;
    }

    public void intializeFirebaseFirestore(Context context2){

            context=context2;
            firestore=FirebaseFirestore.getInstance();
            storage=FirebaseStorage.getInstance();
        }

        public void uploadCoverPhoto(Uri uri, final String title, final String imgDesc){

            storageReference=storage.getReference();

            storageReference2=storageReference.child("Images/Admin/CoverPhoto/"+System.currentTimeMillis()+"."+getImageExtension(uri));
            Log.e("Tag","Firestoagra kjbvvjkb Error 101");
            storageReference2
                    .putFile(uri)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            Log.e("Tag","Firestoagra kjbvvjkb Error 102");
                                storageReference2
                                        .getDownloadUrl()
                                        .addOnSuccessListener(new OnSuccessListener<Uri>() {
                                            @Override
                                            public void onSuccess(Uri uri) {
                                                storeToDatabase(uri,title,imgDesc);
                                            }
                                        }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Log.e("Tag","Firestoagra kjbvvjkb Error");
                                    }
                                });
                        }
                    });
        }



        public boolean storeToDatabase(Uri url,String title,String desc){
            collectionReference=firestore
                    .collection("BrandMore")
                    .document("Admin").collection("CoverImages");

            CoverImages coverImages=new CoverImages(url.toString(),desc,title);

            collectionReference
                    .add(coverImages).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                @Override
                public void onComplete(@NonNull Task<DocumentReference> task) {
                    if (task.isSuccessful()){
                        status=true;
                    }
                    else {
                        status=false;
                    }

                }
            });
            return status;
        }


        public void fetchCoverImages(){

            collectionReference=firestore
                    .collection("BrandMore")
                    .document("Admin").collection("CoverImages");

            collectionReference.get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(Task<QuerySnapshot> task) {
                    if (task.isSuccessful()){

                        for (QueryDocumentSnapshot snapshot :task.getResult()){

                            CoverImages coverImages=new CoverImages();
                            String imgKey=snapshot.get("imageKey").toString();
                            String imgUrl=snapshot.get("imageUrl").toString();
                            String imgTitle=snapshot.get("imageTitle").toString();
                            String imgDesc=snapshot.get("imageDec").toString();

                            coverImages.setImgKey(imgKey);
                            coverImages.setImageUrl(imgUrl);
                            coverImages.setTitle(imgTitle);
                            coverImages.setImageDesc(imgDesc);
                            coverImagesList.add(coverImages);
                        }
                    }

                }
            });

        }


        //     Live Data of COver Images
        public MutableLiveData<List<CoverImages>> getCoverImages(){
            MutableLiveData<List<CoverImages>> mutableLiveData=new MutableLiveData<>();
            mutableLiveData.postValue(coverImagesList);
            return mutableLiveData;
        }



        /*
                   Image Extension from the URL .....
         */

        public String getImageExtension(Uri uri){

                ContentResolver contentResolver=context.getApplicationContext().getContentResolver();
                MimeTypeMap mime=MimeTypeMap.getSingleton();
                return mime.getExtensionFromMimeType(contentResolver.getType(uri));
            }


}
