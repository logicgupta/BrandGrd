package com.logistic.splashactivity.View.Admin;

import Model.OrderIds;
import MyViewModel.AdminOrderListViewModel;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.logistic.splashactivity.R;
import com.logistic.splashactivity.View.AdminAdapters.OrderIdAdapter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class OrderListActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    OrderIdAdapter adapter;
    List<OrderIds> orderIdsList=new ArrayList<>();
    AdminOrderListViewModel viewModel;
    ProgressBar progressBar;
    String orderId;
    String authids;
    String date;
    String orderDelivered;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_list);
        initViews();
        fetchOrderDetails();
    }

    public void initViews(){
        progressBar=findViewById(R.id.progressBarOrderIds);
        recyclerView=findViewById(R.id.recyclerViewOrders);
        recyclerView.setLayoutManager(new LinearLayoutManager(OrderListActivity.this));
        recyclerView.setHasFixedSize(false);

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
                        String payType=snapshot.getString("paymentType");
                        String seller=snapshot.getString("seller");
                        orderDelivered=snapshot.getString("orderDelivered");
                        orderIds.setTransId(snapshot.getString("transId"));
                        orderIds.setSeller(seller);
                        orderIds.setPaymentType(payType);
                        orderIds.setOrderId(orderId);
                        orderIds.setOrderDate(date);
                        orderIds.setIds(authids);
                        orderIds.setEmailId(snapshot.getString("emailId"));
                        orderIds.setAddress(snapshot.getString("address"));
                        orderIds.setOrderDelivered(orderDelivered);
                        orderIdsList.add(orderIds);

                    }
                    adapter=new OrderIdAdapter(OrderListActivity.this,recyclerView,orderIdsList);
                    progressBar.setVisibility(View.GONE);
                    recyclerView.setAdapter(adapter);
                }
                else {
                    Log.e("OrderId s","Failed !");
                    Toast.makeText(OrderListActivity.this, "No Result Found !", Toast.LENGTH_SHORT).show();
                    progressBar.setVisibility(View.GONE);
                }

            }
        });
    }
}
