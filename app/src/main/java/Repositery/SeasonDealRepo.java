package Repositery;

import android.content.ContentResolver;
import android.content.Context;
import android.net.Uri;
import android.util.Log;
import android.webkit.MimeTypeMap;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import Model.ProductDetails;

public class SeasonDealRepo {

        private static SeasonDealRepo  productRepo;
        FirebaseFirestore firestore;
        CollectionReference collectionReference;
        StorageReference storageReference,storageReference2;
        Context context;
        FirebaseStorage storage;

        MutableLiveData<ProductDetails> mutableLiveData=new MutableLiveData<>();
        MutableLiveData<Boolean> status=new MutableLiveData<>();

        public static SeasonDealRepo  getProductRepo(){
            if (productRepo==null){
                productRepo=new SeasonDealRepo ();
            }
            return productRepo;
        }

        public void initialize(Context context){
            this.context=context;

        }

        public void sendImageUrl1(final String  imageUrl1, final String imageUrl2, final String imageUrl3,
                                  final String imageUrl4, final String productName, final String productDesc,
                                  final String productOrgPrice, final String productDisPrice,
                                  final String productQty, final String pinCode, final String seller, final String qty){

            firestore=FirebaseFirestore.getInstance();
            storage=FirebaseStorage.getInstance();
            Log.e("AdminAddItems Activty","Making Process ------");
            storageReference=storage.getReference();

            storageReference2=storageReference
                    .child("Images/"+seller+"/Products/"+System.currentTimeMillis()+"."+getImageExtension(Uri.parse(imageUrl1)));

            storageReference2
                    .putFile(Uri.parse(imageUrl1)).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                    storageReference2.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {

                            sendImageUrl2(uri.toString(),imageUrl2,imageUrl3,imageUrl4,
                                    productName,productDesc,productOrgPrice,productDisPrice,
                                    productQty,pinCode,seller,qty);

                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            status.setValue(false);
                        }
                    });
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {

                    status.setValue(false);
                }
            });


        }
        public void sendImageUrl2(final String  imageUrl1, final String imageUrl2, final String imageUrl3,
                                  final String imageUrl4, final String productName, final String productDesc,
                                  final String productOrgPrice, final String productDisPrice,
                                  final String productQty, final String pinCode, final String seller, final String qty){

            firestore=FirebaseFirestore.getInstance();
            storage=FirebaseStorage.getInstance();
            Log.e("AdminAddItems Activty","Making Process ------");
            storageReference=storage.getReference();

            storageReference2=storageReference
                    .child("Images/"+seller+"/Products/"+System.currentTimeMillis()+"."+getImageExtension(Uri.parse(imageUrl2)));

            storageReference2
                    .putFile(Uri.parse(imageUrl2)).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                    storageReference2.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                            sendImageUrl3(imageUrl1,uri.toString(),imageUrl3,imageUrl4,
                                    productName,productDesc,productOrgPrice,productDisPrice,
                                    productQty,pinCode,seller,qty);

                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            status.setValue(false);
                        }
                    });
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {

                    status.setValue(false);
                }
            });


        }
        public void sendImageUrl3(final String  imageUrl1, final String imageUrl2, final String imageUrl3,
                                  final String imageUrl4, final String productName, final String productDesc,
                                  final String productOrgPrice, final String productDisPrice,
                                  final String productQty, final String pinCode, final String seller, final String qty){

            firestore=FirebaseFirestore.getInstance();
            storage=FirebaseStorage.getInstance();
            Log.e("AdminAddItems Activty","Making Process ------");
            storageReference=storage.getReference();

            storageReference2=storageReference
                    .child("Images/"+seller+"/Products/"+System.currentTimeMillis()+"."+getImageExtension(Uri.parse(imageUrl3)));

            storageReference2
                    .putFile(Uri.parse(imageUrl3)).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                    storageReference2.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                            sendImageUrl4(imageUrl1,imageUrl2,uri.toString(),imageUrl4,
                                    productName,productDesc,productOrgPrice,productDisPrice,
                                    productQty,pinCode,seller,qty);

                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            status.setValue(false);
                        }
                    });
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {

                    status.setValue(false);
                }
            });


        }
        public void sendImageUrl4(final String  imageUrl1, final String imageUrl2, final String imageUrl3,
                                  final String imageUrl4, final String productName, final String productDesc,
                                  final String productOrgPrice, final String productDisPrice,
                                  final String productQty, final String pinCode, final String seller, final String qty){

            firestore=FirebaseFirestore.getInstance();
            storage=FirebaseStorage.getInstance();
            Log.e("AdminAddItems Activty","Making Process ------");
            storageReference=storage.getReference();

            storageReference2=storageReference
                    .child("Images/"+seller+"/Products/"+System.currentTimeMillis()+"."+getImageExtension(Uri.parse(imageUrl4)));

            storageReference2
                    .putFile(Uri.parse(imageUrl4)).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                    storageReference2.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                            storeData(imageUrl1,imageUrl2,imageUrl3,uri.toString(),
                                    productName,productDesc,productOrgPrice,productDisPrice,
                                    productQty,pinCode,seller,qty);

                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            status.setValue(false);
                        }
                    });
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {

                    status.setValue(false);
                }
            });


        }


        public void storeData(String  imageUrl1,String imageUrl2,String imageUrl3,
                              String imageUrl4,String productName,String productDesc,
                              String productOrgPrice,String productDisPrice,
                              String productQty,String pinCode,String seller,String qty){
            String category="SeasonSale";
            String deal="Summer Sale";

            CollectionReference collectionReference=firestore
                    .collection("BrandMore")
                    .document("ProductList")
                    .collection("category");
            ProductDetails productDetails=new
                    ProductDetails(productName,productDesc,
                    productOrgPrice,productDisPrice,productQty,pinCode,seller
                    ,imageUrl1,imageUrl2,imageUrl3,imageUrl4,qty,category,deal);

            collectionReference.add(productDetails).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                @Override
                public void onComplete(@NonNull Task<DocumentReference> task) {

                    if (task.isSuccessful()){
                        status.setValue(true);
                    }

                    else {
                        status.setValue(false);
                    }
                }
            });


        }


        public MutableLiveData<Boolean> getStatus(String  imageUrl1,String imageUrl2,String imageUrl3,
                                                  String imageUrl4,String productName,String productDesc,
                                                  String productOrgPrice,String productDisPrice,
                                                  String productQty,String pinCode,String seller,String qty){
            // Method 1
            sendImageUrl1(imageUrl1,imageUrl2,imageUrl3,imageUrl4,productName,productDesc,
                    productOrgPrice,productDisPrice,productQty,pinCode,seller,qty);
            return  status;
        }

        public String getImageExtension(Uri uri){

            ContentResolver contentResolver=context.getApplicationContext().getContentResolver();
            MimeTypeMap mime=MimeTypeMap.getSingleton();
            return mime.getExtensionFromMimeType(contentResolver.getType(uri));
        }
    }

