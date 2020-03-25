package me.stevenkin.util.all.mergelist;


public class MergeList {

    public ListNode mergeLists(ListNode[] lists) {
        if (lists == null || lists.length == 0)
            return null;
        ListNode result = null, p = null;
        ListNode[] nodes = new ListNode[lists.length];
        for (int i = 0; i < lists.length; i++) {
            nodes[i] = lists[i];
        }
        ListNode node;
        while ((node = min(nodes)) != null) {
            if (p == null) {
                result = p = node;
            } else {
                p.next = node;
                p = p.next;
            }
        }
        return result;
    }

    private ListNode min(ListNode[] nodes) {
        int min = -1;
        for (int i = 0; i < nodes.length; i++) {
            if (nodes[i] != null) {
                if (min == -1 || nodes[i].val < nodes[min].val) {
                    min = i;
                }
            }
        }
        if (min == -1)
            return null;
        ListNode result = nodes[min];
        nodes[min] = nodes[min].next;
        return result;
    }

    private void printList(ListNode list) {
        while (list != null) {
            System.out.print(list.val + "->");
            list = list.next;
        }
        System.out.print("null");
    }

    private ListNode[] build(int[][] vals) {
        if (vals == null || vals.length == 0) {
            return null;
        }
        ListNode[] lists = new ListNode[vals.length];
        for (int i = 0; i < vals.length; i++) {
            if (vals[i] != null) {
                ListNode node = null, p = null;
                for (int j = 0; j < vals[i].length; j++) {
                    if (p == null) {
                        node = p = new ListNode(vals[i][j]);
                    } else {
                        p.next = new ListNode(vals[i][j]);
                        p = p.next;
                    }
                }
                lists[i] = node;
                continue;
            }
            lists[i] = null;
        }
        return lists;
    }

    private static class ListNode {
        int val;
        ListNode next = null;

        public ListNode(int val) {
            this.val = val;
        }
    }

}
