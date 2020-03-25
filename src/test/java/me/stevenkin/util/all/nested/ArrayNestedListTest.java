package me.stevenkin.util.all.nested;

import java.util.Iterator;

public class ArrayNestedListTest {
    public static void main(String[] args) {
        NestedList<String> list1 = ArrayNestedList.of(new String[]{"hello", "list"});
        NestedList<Integer> list2 = ArrayNestedList.of(new Integer[]{1, 2, 3});
        NestedList<Integer> list3 = ArrayNestedList.of(null);
        NestedList<Integer> list4 = ArrayNestedList.of(null);
        list3.addAll(list2)
                .addAll(new ArrayNestedList<>(4))
                .addAll(list4.addAll(new ArrayNestedList<>(5)).addAll(ArrayNestedList.of(new Integer[]{6})));
        Iterator<Integer> iterator = list3.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }
}
