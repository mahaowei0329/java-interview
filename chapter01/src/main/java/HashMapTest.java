import java.util.HashMap;
import java.util.Hashtable;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class HashMapTest {

    public static void main(String[] args) {
        Map<String, String> map = new HashMap<>();
        String str = "abc";
        System.out.println(str.hashCode());

        Map<String, String> map1 = new LinkedHashMap<>();
        ConcurrentHashMap<Object, Object> hashMap = new ConcurrentHashMap<>();

        Map<String, String> hashtable = new Hashtable<>();

    }
}
