package com.oracat.model;

public class Sp {


   public String getBegin_date() {
      return begin_date;
   }

   public void setBegin_date(String begin_date) {
      this.begin_date = begin_date;
   }

   public String getEnd_date() {
      return end_date;
   }

   public void setEnd_date(String end_date) {
      this.end_date = end_date;
   }

   private String             begin_date         ;
   private String             end_date         ;




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
