import java.util.*;

public class CircularQueue {
    int front, rear, size;
    int[] queue;

    CircularQueue(int capacity) {
        size = capacity;
        queue = new int[size];
        front = rear = -1;
    }

    void enqueue(int data) {
        if ((rear + 1) % size == front) {
            System.out.println("Queue is full!");
            return;
        }
        if (front == -1) front = 0;
        rear = (rear + 1) % size;
        queue[rear] = data;
        System.out.println(data + " inserted.");
    }

    void dequeue() {
        if (front == -1) {
            System.out.println("Queue is empty!");
            return;
        }
        System.out.println(queue[front] + " deleted.");
        if (front == rear)
            front = rear = -1;
        else
            front = (front + 1) % size;
    }

    void display() {
        if (front == -1) {
            System.out.println("Queue is empty!");
            return;
        }
        System.out.print("Elements: ");
        int i = front;
        while (true) {
            System.out.print(queue[i] + " ");
            if (i == rear)
                break;
            i = (i + 1) % size;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        CircularQueue cq = new CircularQueue(5);
        cq.enqueue(10);
        cq.enqueue(20);
        cq.enqueue(30);
        cq.display();
        cq.dequeue();
        cq.display();
    }
}

