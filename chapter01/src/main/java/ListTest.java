import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ListTest {

    public static void main(String[] args) {
        List<String> list = new ArrayList<>(2);
        List<String> list1 = new LinkedList<>();
        System.out.println(list.size());
        list.add("test1");
        list.add("test2");
        list.add("test3");
        //3
        System.out.println(list.size());
        list.add("test4");
        //4
        System.out.println(list.size());
        list.add("test5");
        //6
        System.out.println(list.size());
    }
}
