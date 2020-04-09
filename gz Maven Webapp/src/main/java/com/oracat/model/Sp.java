package com.oracat.model;

public class Sp {
   public String getSpmch() {
      return spmch;
   }

   public void setSpmch(String spmch) {
      this.spmch = spmch;
   }

   public Double getOrder_pay_price() {
      return order_pay_price;
   }

   public void setOrder_pay_price(Double order_pay_price) {
      this.order_pay_price = order_pay_price;
   }

   private String spmch ;
   private Double order_pay_price;


   public String getMs() {
      return ms;
   }

   public void setMs(String ms) {
      this.ms = ms;
   }

   private  String ms ;

}
