package collection.hashmap;

public class Test {
    public static void main(String[] args) {
        ZsHashMap<Integer,String> map = new ZsHashMap<>();
        map.put(1,"我爱");
        map.put(2,"你");
        map.put(3,"世界");
        map.put(4,"java");
        map.put(5,"java");
        map.put(6,"java");

        System.out.println(map.get(1));
        System.out.println(map.get(2));
        System.out.println(map.get(3));

    }
}
