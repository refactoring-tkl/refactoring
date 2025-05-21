package com.tkl.refactoring.chapter3;

// 3.9 기능 편애
public class FeatureEnvy {
	public static class Order {
		private String customerName;
		private String phone;
		private String address;

		public Order(String customerName, String phone, String address) {
			this.customerName = customerName;
			this.phone = phone;
			this.address = address;
		}

		public String getCustomerName() { return customerName; }
		public String getPhone() { return phone; }
		public String getAddress() { return address; }
	}

	public static class OrderPrinter {
		public String printOrder(FeatureEnvy.Order order) {
			String customerName = order.getCustomerName();
			String customerPhone = order.getPhone();
			String address = order.getAddress();
			return "Order for: " + customerName + ", " + customerPhone + ", " + address;
		}
	}
}
