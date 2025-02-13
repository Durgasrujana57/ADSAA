import java.util.ArrayList;
import java.util.Scanner;

public class MaxHeap {
    
    // Insert item into the heap
    public static void insert(ArrayList<Integer> a, int item) {
        a.add(item);  // Add the item at the end of the heap
        int i = a.size() - 1;
        
        // Restore the heap property by bubbling up
        while (i > 0 && a.get(i) > a.get(i / 2)) {
            int temp = a.get(i);
            a.set(i, a.get(i / 2));
            a.set(i / 2, temp);
            i = i / 2;
        }
    }

    // Delete the maximum element from the heap (root)
    public static int DeleteMax(ArrayList<Integer> a) {
        if (a.isEmpty()) {
            System.out.println("Heap is empty!");
            return -1;
        }
        
        int max = a.get(0);  // The root is the maximum
        a.set(0, a.get(a.size() - 1));  // Replace root with the last element
        a.remove(a.size() - 1);  // Remove the last element
        
        if (!a.isEmpty()) {
            Adjust(a, 0, a.size());  // Restore heap property
        }
        
        return max;
    }

    // Adjust the heap by moving the element at index i down to its correct position
    public static void Adjust(ArrayList<Integer> a, int i , int n) {
        int j = 2 * i + 1;  // Left child index
        int item = a.get(i);

        while (j < n) {
            // Find the largest child
            if (j + 1 < n && a.get(j) < a.get(j + 1)) {
                j = j + 1;
            }
            
            // If the current item is larger than or equal to the largest child, stop
            if (item >= a.get(j)) break;
            
            // Swap with the largest child
            a.set(i, a.get(j));
            i = j;
            j = 2 * i + 1;  // Move to the next level
        }
        
        // Place the item in its correct position
        a.set(i, item);
    }

    // Build the heap from the given array
    public static void Heapify(ArrayList<Integer> a) {
        int n = a.size();
        for (int i = n / 2 - 1; i >= 0; i--) {  // Start from the last non-leaf node
            Adjust(a, i , n);
        }
    }

    // Perform heap sort
    public static void HeapSort(ArrayList<Integer> a) {
        Heapify(a);  // Build the heap
        int n = a.size();
        for (int i = n - 1; i > 0; i--) {
            // Swap root with the last element
            int temp = a.get(i);
            a.set(i, a.get(0));
            a.set(0, temp);
            
            // Adjust the heap
            Adjust(a, 0 , i);
        }
    }

    // Get the maximum element (root of the heap)
    public static int SearchMax(ArrayList<Integer> a) {
        return a.size() > 0 ? a.get(0) : -1;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Integer> a = new ArrayList<>();

        // Input the number of elements and elements
        System.out.println("Enter the number of elements in the array: ");
        int n = sc.nextInt();
        System.out.println("Enter the elements of the array:");
        for (int i = 0; i < n; i++) {
            a.add(sc.nextInt());
        }

        // Display the given array
        System.out.println("Given array: " + a);
        
        // Heapify the array
        System.out.println("Heapifying the array");
        Heapify(a);
        System.out.println("Heap array: " + a);

        // Menu for user options
        while (true) {
            System.out.println("1.Insert \n2.DeleteMax \n3.SearchMax \n4.HeapSort \n5.Exit \n6.Heapify");
            System.out.println("Enter your choice: ");
            int choice = sc.nextInt();
            switch (choice) {
                case 1 -> {
                    System.out.print("Enter the number: ");
                    int item = sc.nextInt();
                    insert(a, item);
                    System.out.println("After Insertion: " + a);
                }

                case 2 -> {
                    int max = DeleteMax(a);
                    if (max != -1) {
                        System.out.println("After Deletion: " + a);
                    }
                }

                case 3 -> {
                    System.out.println("The largest element in the heap is: " + SearchMax(a));
                }

                case 4 -> {
                    System.out.println("Heap array before sorting: " + a);
                    HeapSort(a);
                    System.out.println("Heap array after sorting: " + a);
                }

                case 5 -> {
                    System.out.println("Exiting...");
                    sc.close();
                    return;
                }

                case 6 -> {
                    Heapify(a);
                    System.out.println("After Heapify: " + a);
                }

                default -> System.out.println("Invalid choice!!!");
            }
        }
    }
}

