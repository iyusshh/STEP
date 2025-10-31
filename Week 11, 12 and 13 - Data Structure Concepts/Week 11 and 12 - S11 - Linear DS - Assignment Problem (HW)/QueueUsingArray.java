import java.util.*;

public class QueueUsingArray {
    int size, front, rear;
    int[] queue;

    QueueUsingArray(int capacity) {
        size = capacity;
        queue = new int[size];
        front = rear = -1;
    }

    void enqueue(int data) {
        if (rear == size - 1) {
            System.out.println("Queue is full!");
            return;
        }
        if (front == -1) front = 0;
        queue[++rear] = data;
        System.out.println(data + " enqueued.");
    }

    void dequeue() {
        if (front == -1 || front > rear) {
            System.out.println("Queue is empty!");
            return;
        }
        System.out.println(queue[front++] + " dequeued.");
    }

    void display() {
        if (front == -1 || front > rear) {
            System.out.println("Queue is empty!");
            return;
        }
        System.out.print("Queue: ");
        for (int i = front; i <= rear; i++)
            System.out.print(queue[i] + " ");
        System.out.println();
    }

    public static void main(String[] args) {
        QueueUsingArray q = new QueueUsingArray(5);
        q.enqueue(10);
        q.enqueue(20);
        q.enqueue(30);
        q.display();
        q.dequeue();
        q.display();
    }
}
