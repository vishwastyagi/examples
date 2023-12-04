import java.util.HashMap;

public class Test {
    public static void main(String[] args) {
        HashMap<String,String> map = new HashMap<>();
        String one="test";
        String two="test";
        String three=new String("test");
        StringBuilder four=new StringBuilder("test");
        map.put(one,one);
        map.put(two,two);
        map.put(three,three);
        map.put(four.toString(),four.toString());
        System.out.println(map.size());
    }
}
class A{
    A(){

    }

}
class B extends A{
    B(){

    }

}