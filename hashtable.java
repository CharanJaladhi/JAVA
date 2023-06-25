
/*import java.util.*;




public class hashtable {
    private String AName;
    private String password;
    public hashtable(String name,String pass){
        this.AName=name;
        this.password=pass;
    }

    public static void main(String args[]){
        Hashtable<String,hashtable> store=new Hashtable<>();
        hashtable table[]=new hashtable[2];
        table[0]=new hashtable("charan","N12");
        table[1]=new hashtable("cherry","N13");
        store.put("N12345",table[0]);
        store.put("N12346",table[1]);
        //for(String key:store.keySet())
        //System.out.println(key+store.getValue(key.AName));

    }
}*/
import java.util.*;




public class hashtable {
    static Hashtable<String,hashtable> store=new Hashtable<>();
    private String AName;
    private String password;
    public hashtable(String name,String pass){
        this.AName=name;
        this.password=pass;
        
    }

    public static void main(String args[]){
        
        hashtable table[]=new hashtable[2];
        table[0]=new hashtable("charan","N12");
        table[1]=new hashtable("cherry","N13");
        store.put("N12345",table[0]);
        store.put("N12346",table[1]);
        for(String key:store.keySet())
            System.out.println(key+ ": " + store.get(key).AName + " " + store.get(key).password);

    }
}
