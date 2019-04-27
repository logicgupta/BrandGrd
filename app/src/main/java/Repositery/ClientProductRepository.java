package Repositery;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;

public class ClientProductRepository {

    public ClientProductRepository() {
    }


    public void addProductDetails(String category,String imgUrl1,String imgUrl2,String imgUrl3
            ,String imgUrl4,String productname,String productDesc,String productOriginalPrice,
                                  String productDiscountPrice,String pincode){

        FirebaseFirestore firestore=FirebaseFirestore.getInstance();

        CollectionReference collectionReference=firestore
                .collection("BrandMore")
                .document("ProductList")
                .collection(category);


    }

}
