import java.util.*;
class Product{
    private String productName;
    private double productPrice;

    public void setproductName(String pName,double pPrice){
        this.productName=pName;
        this.productPrice=pPrice;
    }
    public void getproductName(ArrayList<Product> cartItems){
        for(int i=0;i<4;i++)
        System.out.println("Product Name:"+cartItems.get(i).productName+"\nPrice:"+cartItems.get(i).productPrice);
    }
}
class updateProduct extends Product{

}
public class Cart{
    public static void main(String args[]){
        ArrayList<Product> cartItems=new ArrayList<>();
        Product obj=new Product();
        obj.setproductName("Realme",26999);
        cartItems.add(obj);
        Product obj1=new Product();
        obj1.setproductName("Samsung",21999);
       // obj1.setproductName("OnepLus",21999);
        cartItems.add(obj1);
        Product obj2=new Product();
        obj2.setproductName("OnePlus",29999);
        cartItems.add(obj2);
        Product obj3=new Product();
        obj3.setproductName("IPhone",136999);
        cartItems.add(obj3);
        obj.getproductName(cartItems);
    }
}