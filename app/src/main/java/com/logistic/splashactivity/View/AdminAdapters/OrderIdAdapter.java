package com.logistic.splashactivity.View.AdminAdapters;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.logistic.splashactivity.View.Admin.ClientCancel.ClientOrderDetailsActivity;
import com.logistic.splashactivity.View.Admin.OrderDetailsActivity;

import java.text.BreakIterator;
import java.util.ArrayList;
import java.util.List;

import Model.OrderIds;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class OrderIdAdapter extends RecyclerView.Adapter<OrderIdAdapter.ViewHolder> {

    Context context;
    RecyclerView recyclerView;
    List<OrderIds> orderDetailsList=new ArrayList<>();

    public OrderIdAdapter(Context context, RecyclerView recyclerView
            , List<OrderIds> orderDetailsList) {
        this.context = context;
        this.recyclerView = recyclerView;
        this.orderDetailsList = orderDetailsList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater
                .from(context)
                .inflate(com.logistic.splashactivity.R.layout.custom_order_list_layout
                        ,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        OrderIds orderDetails=orderDetailsList.get(position);
        holder.textViewOrderId.setText("OrderId :"+orderDetails.getTransId());
        holder.textViewPurchasedDate.setText("Booked Date:"+orderDetails.getOrderDate());
        if (orderDetails.getSeller()!=null){
            holder.textViewDelivery.setText("Order Approved to  "+orderDetails.getSeller());

        }
        else {

            holder.textViewDelivery.setText(" Order Approved :"+orderDetails.getOrderDelivered());
        }
        if (orderDetails.getPaymentType().equalsIgnoreCase("Cash")){
            holder.textViewpaymentType.setText("Amount UnPaid : "+"Cash on Delivery");
        }
        else {
            holder.textViewpaymentType.setText("Amount Paid via"+orderDetails.getPaymentType());

        }
        holder.textViewEmail.setText("Email Id :"+orderDetails.getEmailId());


    }

    @Override
    public int getItemCount() {
        if (orderDetailsList==null){
            return 0;
        }
        return orderDetailsList.size();
    }

    public void setData(List<OrderIds> orderList){
        Log.e("Order Ids ","List Set  ");
        orderDetailsList.addAll(orderList);
        notifyDataSetChanged();


    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView textViewDelivery;
        TextView textViewOrderId,textViewPurchasedDate;
        TextView textViewpaymentType,textViewEmail;
    public ViewHolder(@NonNull final View itemView) {
        super(itemView);

        textViewOrderId=itemView.findViewById(com.logistic.splashactivity.R.id.orderIdTextView);
        textViewPurchasedDate=itemView.findViewById(com.logistic.splashactivity.R.id.purchasedDateTextView);
        textViewDelivery=itemView.findViewById(com.logistic.splashactivity.R.id.deliveryInitiatedTextView);
        textViewpaymentType=itemView.findViewById(com.logistic.splashactivity.R.id.paymentTextView);
        textViewEmail=itemView.findViewById(com.logistic.splashactivity.R.id.deliveryEmailTextView);



        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position=recyclerView.getChildAdapterPosition(itemView);
                OrderIds orderDetails=orderDetailsList.get(position);

                String orderId=orderDetails.getTransId();

                String authid=orderDetails.getIds();
                String transId=orderDetails.getTransId();
                String address=orderDetails.getAddress();
                Intent intent=new Intent(context, OrderDetailsActivity.class);
                intent.putExtra("orderId",orderId);
                intent.putExtra("authId",authid);
                intent.putExtra("transId",transId);
                intent.putExtra("address",address);


                context.startActivity(intent);

            }
        });
    }
}

}
