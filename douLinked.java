import java.util.*;

class Node {
    int data;
    Node next;
    Node prev;

    public Node(int data) {
        this.data = data;
        this.next = null;
        this.prev = null;
    }
}

class DoublyLinkedList {
    private Node head;

    public DoublyLinkedList() {
        this.head = null;
    }

    // Insertion at a specific position
    public void insertAtPosition(int data, int position) {
    Node newNode = new Node(data);
    
    if (position <= 0) {
        System.out.println("Invalid position. Please enter a position greater than 0.");
        return;
    }

    if (position == 1) {
        newNode.next = head;
        if (head != null) {
            head.prev = newNode;
        }
        head = newNode;
        System.out.println("Node with data " + data + " inserted at the beginning.");
        return;
    }

    Node current = head;
    int currentPosition = 1;

    while (current != null && currentPosition < position - 1) {
        current = current.next;
        currentPosition++;
    }

    if (currentPosition < position - 1 || current == null) {
        System.out.println("Invalid position. Position exceeds the length of the list.");
    } else {
        newNode.prev = current;
        newNode.next = current.next;
        if (current.next != null) {
            current.next.prev = newNode;
        }
        current.next = newNode;
        System.out.println("Node with data " + data + " inserted at position " + position);
    }
}


    // Deletion of a specific node
    public void delete(int data) {
        if (head == null) {
            System.out.println("List is empty. Deletion failed.");
            return;
        }

        Node current = head;

        while (current != null && current.data != data) {
            current = current.next;
        }

        if (current == null) {
            System.out.println("Node with data " + data + " not found.");
        } else {
            if (current.prev != null) {
                current.prev.next = current.next;
            } else {
                head = current.next;
            }

            if (current.next != null) {
                current.next.prev = current.prev;
            }

            System.out.println("Node with data " + data + " deleted.");
        }
    }

    // Search for a node
    public void search(int data) {
        Node current = head;
        int position = 1;

        while (current != null && current.data != data) {
            current = current.next;
            position++;
        }

        if (current == null) {
            System.out.println("Node with data " + data + " not found.");
        } else {
            System.out.println("Node with data " + data + " found at position " + position);
        }
    }

    // Update a node
    public void update(int oldData, int newData) {
        Node current = head;

        while (current != null && current.data != oldData) {
            current = current.next;
        }

        if (current == null) {
            System.out.println("Node with data " + oldData + " not found. Update failed.");
        } else {
            current.data = newData;
            System.out.println("Node with data " + oldData + " updated to " + newData + ".");
        }
    }

    // Display the doubly linked list
    public void display() {
        Node current = head;
        while (current != null) {
            System.out.print(current.data + " ");
            current = current.next;
        }
        System.out.println();
    }
}

public class douLinked {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        DoublyLinkedList doublyLinkedList = new DoublyLinkedList();

        while (true) {
            System.out.println("\nMenu:");
            System.out.println("1. Insert at specific position");
            System.out.println("2. Delete");
            System.out.println("3. Search");
            System.out.println("4. Update");
            System.out.println("5. Display");
            System.out.println("6. Exit");

            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter data to insert: ");
                    int insertDataPosition = scanner.nextInt();
                    System.out.print("Enter position to insert at: ");
                    int position = scanner.nextInt();
                    doublyLinkedList.insertAtPosition(insertDataPosition, position);
                    break;
                case 2:
                    System.out.print("Enter data to delete: ");
                    int deleteData = scanner.nextInt();
                    doublyLinkedList.delete(deleteData);
                    break;
                case 3:
                    System.out.print("Enter data to search: ");
                    int searchData = scanner.nextInt();
                    doublyLinkedList.search(searchData);
                    break;
                case 4:
                    System.out.print("Enter data to update: ");
                    int oldData = scanner.nextInt();
                    System.out.print("Enter new data: ");
                    int newData = scanner.nextInt();
                    doublyLinkedList.update(oldData, newData);
                    break;
                case 5:
                    System.out.println("Doubly Linked List:");
                    doublyLinkedList.display();
                    break;
                case 6:
                    System.out.println("Exiting program.");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
