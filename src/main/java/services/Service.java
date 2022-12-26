package services;
import Payment.*;
import Users.*;

public abstract class Service {
		
		protected String type;
		protected String description;
		protected Form form; 
		protected Discount discount;
		protected boolean COD; //to indicate of the service accepts cash
		
		
		public abstract boolean handle(GeneralUser user, PaymentMethod method);
		
		public void displayForm() throws Exception {
			form.takeInput();
		}

		public String getType() {
			return type;
		}

		public void setType(String type) {
			this.type = type;
		}

		public String getDescription() {
			return description;
		}

		public void setDescription(String description) {
			this.description = description;
		}


		public Discount getDiscount() {
			return discount;
		}

		public void setDiscount(Discount discount) {
			this.discount = discount;
		}

		public boolean isCOD() {
			return COD;
		}

		public void setCOD(boolean cOD) {
			COD = cOD;
		}
}
