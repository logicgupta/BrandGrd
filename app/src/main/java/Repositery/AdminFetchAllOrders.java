package Repositery;

import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

import Model.OrderDetails;
import Model.OrderIds;
import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

public class AdminFetchAllOrders {

    private static AdminFetchAllOrders sInstance;
    String orderId;
    String authids;
    String date;
    String orderDelivered;
    List<OrderDetails > orderDetailsList=new ArrayList<>();
    List<OrderIds> orderIdsList=new ArrayList<>();
    List<OrderIds> orderIdsCancelledList=new ArrayList<>();
    MutableLiveData<List<OrderIds>> mutableLiveData=new MutableLiveData<>();  // Order Ids -----

    public static AdminFetchAllOrders getsInstance(){

        if (sInstance==null){
            sInstance=new AdminFetchAllOrders();
        }

        return sInstance;
    }

    public void fetchOrderDetails() {

        final FirebaseFirestore firestore = FirebaseFirestore.getInstance();

        final CollectionReference collectionReference = firestore
                .collection("BrandMore")
                .document("AllOrders")
                .collection("OrderId");


        collectionReference.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()){
                    for (QueryDocumentSnapshot snapshot: task.getResult()){
                        Log.e("OrderId s",""+snapshot);
                        OrderIds orderIds=new OrderIds();
                        orderId=snapshot.getString("orderId");
                        authids=snapshot.getString("Ids");
                        date=snapshot.getString("orderDate");
                        orderDelivered=snapshot.getString("orderDelivered");
                        orderIds.setTransId(snapshot.getString("transId"));
                        orderIds.setOrderId(orderId);
                        orderIds.setOrderDate(date);
                        orderIds.setIds(authids);
                        orderIds.setOrderDelivered(orderDelivered);
                        orderIdsList.clear();
                        orderIdsList.add(orderIds);
                        mutableLiveData.setValue(orderIdsList);
                    }
                }
                else {
                    Log.e("OrderId s","Failed !");
                }

            }
        });
    }


    public void fetchOrderIdData(String orderIds,String authids){
        final FirebaseFirestore firestore = FirebaseFirestore.getInstance();

        CollectionReference collectionReference1=firestore
                .collection("BrandMore")
                .document("UserBooking")
                .collection(authids)
                .document("Booked")
                .collection(orderId);
        collectionReference1.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {

                if (task.isSuccessful()){
                    for (QueryDocumentSnapshot documentSnapshot :task.getResult()){
                        OrderDetails orderDetails=new OrderDetails();
                        orderDetails.setOrderKey(documentSnapshot.getId());
                        orderDetails.setProductName(documentSnapshot.getString("productName"));
                        orderDetails.setProductDesc(documentSnapshot.getString("productDesc"));
                        orderDetails.setAddress(documentSnapshot.getString("address"));
                        orderDetails.setDelivery(documentSnapshot.getString("delivery"));
                        orderDetails.setOrderId(documentSnapshot.getString("orderId"));
                        orderDetails.setProductDesc(documentSnapshot.getString("productDesc"));
                        orderDetails.setProductDiscountPrice(documentSnapshot.getString("productDiscountPrice"));
                        orderDetails.setProductImageUrl1(documentSnapshot.getString("productImageUrl1"));
                        orderDetails.setProductSeller(documentSnapshot.getString("productSeller"));
                        orderDetails.setPurchasedDate(documentSnapshot.getString("purchasedDate"));
                        orderDetails.setStatus(documentSnapshot.getString("status"));
                        orderDetails.setTrackingId(documentSnapshot.getString("trackingId"));
                        orderDetails.setEmailId(documentSnapshot.getString("emailId"));
                        orderDetailsList.clear();
                        orderDetailsList.add(orderDetails);

                    }
                }
            }
        });
    }

    public MutableLiveData<List<OrderIds>> getOrder(){
        fetchOrderDetails();
        return mutableLiveData;
    }

    public MutableLiveData<List<OrderDetails>> getOrderDetails(){
        MutableLiveData<List<OrderDetails>> mutableLiveData=new MutableLiveData<>();
        mutableLiveData.postValue(orderDetailsList);
        return mutableLiveData;
    }

    public void fetchCancelOrderDetails(){


        final FirebaseFirestore firestore = FirebaseFirestore.getInstance();

        final CollectionReference collectionReference = firestore
                .collection("BrandMore")
                .document("AllOrders")
                .collection("OrderId");


        collectionReference.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {

                if (task.isSuccessful()){
                    for (QueryDocumentSnapshot snapshot: task.getResult()){
                        OrderIds orderIds=new OrderIds();
                        orderId=snapshot.getString("orderId");
                        authids=snapshot.getString("Ids");
                        date=snapshot.getString("orderDate");
                        orderDelivered=snapshot.getString("orderDelivered");
                        orderIds.setTransId(snapshot.getString("transId"));
                        orderIds.setOrderId(orderId);
                        orderIds.setOrderDate(date);
                        orderIds.setIds(authids);
                        orderIds.setOrderDelivered(orderDelivered);
                        if (orderDelivered.equalsIgnoreCase("orderCancelled"))
                        orderIdsCancelledList.add(orderIds);
                    }
                }
            }
        });

    }

    public MutableLiveData<List<OrderIds>> getCancelledData(){
        MutableLiveData<List<OrderIds>> mutableLiveData=new MutableLiveData<>();
        mutableLiveData.setValue(orderIdsCancelledList);
        return mutableLiveData;
    }
}
