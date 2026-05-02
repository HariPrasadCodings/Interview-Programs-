package linkedList;

class ListNode {
	int value;
	ListNode next;

	ListNode() {

	}

	ListNode(int value) {
		this.value = value;
	}

	ListNode(int value, ListNode next) {
		this.value = value;
		this.next = next;
	}
}

public class ReverseLinkedList {
	public static void main(String[] args) {
		ListNode head = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5)))));

		System.out.println("Original List: ");
		printList(head);

		System.out.println("Reversed list: ");
		ReverseLinkedList linkedList = new ReverseLinkedList();
		ListNode reversedHead = linkedList.reverseList(head);

		printList(reversedHead);

	}

	public ListNode reverseList(ListNode head) {
		ListNode prev = null;

		while (head != null) {
			ListNode nextNode = head.next;
			head.next = prev;
			prev = head;
			head = nextNode;
		}
		return prev;
	}

	static void printList(ListNode head) {
		while (head != null) {
			System.out.print(head.value + (head.next != null ? "-->" : ""));
			head = head.next;
		}
		System.out.println();
	}

}
