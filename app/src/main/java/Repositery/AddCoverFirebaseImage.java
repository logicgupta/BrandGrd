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
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
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
import Model.ProductDetails;
import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

public class AddCoverFirebaseImage {

     private static AddCoverFirebaseImage instance;
        FirebaseFirestore firestore;
        FirebaseStorage storage;
        CollectionReference collectionReference;
        Context context;
        List<CoverImages> coverImagesList=new ArrayList<>();
         Uri newimgUrl1;
         Uri newimgUrl2;

         MutableLiveData<Boolean> addCoverImageStatus=new MutableLiveData<>();              // Status Of Cover Image
         MutableLiveData<List<CoverImages>> mutableLiveData=new MutableLiveData<>();     // Cover Images Of App
        private MutableLiveData<Boolean> addProductStatus =new MutableLiveData<>();         // Status of Product  Added !

        StorageReference storageReference ,storageReference2;

    public static AddCoverFirebaseImage getInstance() {
        if (instance==null){
            instance=new AddCoverFirebaseImage();
        }
        return instance;
    }

    public void intializeFirebaseFirestore(Context context2){
        /*String projectID = "brandmore-532eb";
        FirebaseOptions options=new FirebaseOptions.Builder()
                .setApplicationId("1:629215182951:android:d461937a0e182e4c")
                .setApiKey("AIzaSyBFwXn-RiyGoqO5w56V_KRd3ATtoIMc8cc")
                .setDatabaseUrl("https://brandmore-532eb.firebaseio.com")
                .setProjectId(projectID)
                .setStorageBucket("brandmore-532eb.appspot.com")
                .build();

        FirebaseApp.initializeApp(context2.getApplicationContext(),options);*/

            context=context2;
        firestore=FirebaseFirestore.getInstance();
        storage=FirebaseStorage.getInstance();

        }

        public void uploadCoverPhoto(Uri uri, final String title, final String imgDesc){


            storageReference=storage.getReference();

            storageReference2=storageReference.child("Images/Admin/CoverPhoto/"+System.currentTimeMillis()+"."+getImageExtension(uri));
            Log.e("Tag","Firestoagra kjbvvjkb  101");
            storageReference2
                    .putFile(uri)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            Log.e("Tag","Firestoagra kjbvvjkb Success 102");
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
                                        addCoverImageStatus.setValue(false);
                                        Log.e("Tag","Firestore Error",e);
                                    }
                                });
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    e.printStackTrace();
                    addCoverImageStatus.setValue(false);
                }
            });

        }



        public void storeToDatabase(Uri url,String title,String desc){
            collectionReference=firestore
                    .collection("BrandMore")
                    .document("Admin").collection("CoverImages");

            CoverImages coverImages=new CoverImages(url.toString(),desc,title);

            collectionReference
                    .add(coverImages).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                @Override
                public void onComplete(@NonNull Task<DocumentReference> task) {
                    if (task.isSuccessful()){
                        addCoverImageStatus.setValue(true);
                    }
                    else {
                        addCoverImageStatus.setValue(false);
                    }

                }
            });

        }

        public MutableLiveData<Boolean> getCoverImagesStatus(){

            return  addCoverImageStatus;
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
                             coverImagesList.clear();
                            coverImagesList.add(coverImages);
                            mutableLiveData.postValue(coverImagesList);
                        }
                    }

                }
            });

        }


        //     Live Data of COver Images
        public MutableLiveData<List<CoverImages>> getCoverImages(){
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


            /*
                            Product To Firebase Database *-*--*-------------------*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-
             */


    public void addProductDetails(final String category, String imgUrl1, final String imgUrl2
            , final String productname, final String productDesc, final String productOriginalPrice,
                                  final String productDiscountPrice, final String pincode, final String quantity, final String sellerName){


        firestore=FirebaseFirestore.getInstance();
        storage=FirebaseStorage.getInstance();
        Log.e("AdminAddItems Activty","Making Process ------");
        storageReference=storage.getReference();

        storageReference2=storageReference
                .child("Images/Admin/Products/"+System.currentTimeMillis()+"."+getImageExtension(Uri.parse(imgUrl1)));

        storageReference2
                .putFile(Uri.parse(imgUrl1)).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                Log.e("AdminAddItems Activty","Success1 ------*-**--");
                storageReference2.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {

                        newimgUrl1=uri;
                        storeImage2(category,imgUrl2,productname,productDesc,productOriginalPrice,productDiscountPrice,
                                pincode,quantity,sellerName);
                    }
                });
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                addProductStatus.setValue(false);
                Log.e("Error1 ","i s"+e);
            }
        });



    }

    public void storeImage2(final String category, String imgUrl2
            , final String productname, final String productDesc, final String productOriginalPrice,
                            final String productDiscountPrice, final String pincode, final String quantity, final String sellerName){
        storageReference2=storageReference
                .child("Images/Admin/Products/"+System.currentTimeMillis()+"."
                        +getImageExtension(Uri.parse(imgUrl2)));

        storageReference2.putFile(Uri.parse(imgUrl2)).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                Log.e("AdminAddItems Activty","Success2 ------*-**--");

                storageReference2.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        newimgUrl2=uri;
                         storeProductsDetailsToDataBase(category,productname,productDesc,
                                productOriginalPrice,productDiscountPrice,pincode,quantity,newimgUrl2.toString(),sellerName);

                    }
                });

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                addProductStatus.setValue(false);
                Log.e("Error 2 ","i s"+e);

            }
        });
    }
    public void storeProductsDetailsToDataBase(String category
            ,String productname,String productDesc
            ,String productOriginalPrice, String productDiscountPrice
            ,String pincode,String quantity,String urlimg,String sellerName

    ){

        CollectionReference collectionReference=firestore
                .collection("BrandMore")
                .document("ProductList")
                .collection("category");

        ProductDetails productDetails=new ProductDetails(productname,category,productDesc
                ,productOriginalPrice,productDiscountPrice,quantity
                ,pincode,sellerName,newimgUrl1.toString(),urlimg);

        collectionReference
                .add(productDetails)
                .addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentReference> task) {
                        if (task.isSuccessful()){
                            addProductStatus.setValue(true);
                            Log.e("AdminAddItems Activity","Success Database 0 ------*-**--");

                        }
                        else {
                            addProductStatus.setValue(false);
                            Log.e("AdminAddItems Activity","Failed Database 0 ------*-**--"+task.getResult().toString());

                        }
                    }
                });
    }

    public MutableLiveData<Boolean> statusOfProductAdded(){
            return addProductStatus;
    }






}
