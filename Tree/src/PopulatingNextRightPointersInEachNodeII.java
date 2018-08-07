public class PopulatingNextRightPointersInEachNodeII {
    public void connect(TreeLinkNode root) {
        TreeLinkNode header = root; // header of most recently linked level
        while(header != null) {
            TreeLinkNode cur = header, prv = null; // use cur to iterate through the linked level, prv: prv node in next node
            header = null; // once header assigned to cur, null head, if didn't find new head node in this level, loop ends
            for ( ; cur != null; cur = cur.next) { // for each node cur, connect cur.left and cur.right if they are not null
                if (cur.left != null) {
                    if (prv == null) {
                        header = cur.left; // this is the first node of this level, because prv is null
                    } else {
                        prv.next = cur.left; // connect cur.left
                    }
                    prv = cur.left;
                }
                if (cur.right != null) {
                    if (prv == null) {
                        header = cur.right;
                    } else {
                        prv.next = cur.right;
                    }
                    prv = cur.right;
                }
            }
        }
    }
}
