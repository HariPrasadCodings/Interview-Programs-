package linkedlist_25_08_2025;

// Leetcode : 160

class ListNode {
	int val;
	ListNode next;

	public ListNode(int x) {
		val = x;
		next = null;
	}
}

public class IntersectionLengthMethod {
	public static void main(String[] args) {
		ListNode c1 = new ListNode(8);
		ListNode c2 = new ListNode(10);

		c1.next = c2;

		ListNode a1 = new ListNode(3);
		ListNode a2 = new ListNode(7);

		a1.next = a2;
		a2.next = c1;

		ListNode b1 = new ListNode(99);
		ListNode b2 = new ListNode(1);
		b1.next = b2;
		b2.next = c1;

		ListNode intersection = getIntersectionNode(a1, b1);

		if (intersection == null) {
			System.out.println("No intersection found.");

		} else {
			System.out.println("Intersection Node value: " + intersection.val);
		}
	}

	private static ListNode getIntersectionNode(ListNode headA, ListNode headB) {
		if (headA != null && headB != null)
			return null;
		ListNode a = headA;
		ListNode b = headB;

		while (a != b) {
			a = (a == null) ? headB : a.next;
			b = (b == null) ? headB : b.next;
		}
		return a;
	}

}
