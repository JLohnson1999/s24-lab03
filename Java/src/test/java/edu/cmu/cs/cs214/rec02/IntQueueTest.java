package edu.cmu.cs.cs214.rec02;

import org.junit.Before;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static org.junit.Assert.*;


/**
 * TODO: 
 * 1. The {@link LinkedIntQueue} has no bugs. We've provided you with some example test cases.
 * Write your own unit tests to test against IntQueue interface with specification testing method 
 * using mQueue = new LinkedIntQueue();
 * 
 * 2. 
 * Comment `mQueue = new LinkedIntQueue();` and uncomment `mQueue = new ArrayIntQueue();`
 * Use your test cases from part 1 to test ArrayIntQueue and find bugs in the {@link ArrayIntQueue} class
 * Write more unit tests to test the implementation of ArrayIntQueue, with structural testing method
 * Aim to achieve 100% line coverage for ArrayIntQueue
 *
 * @author Alex Lockwood, George Guo, Terry Li
 */
public class IntQueueTest {

    private IntQueue mQueue;
    private List<Integer> testList;

    /**
     * Called before each test.
     */
    @Before
    public void setUp() {
        // comment/uncomment these lines to test each class
        //mQueue = new LinkedIntQueue();
        mQueue = new ArrayIntQueue();

        testList = new ArrayList<>(List.of(1, 2, 3));
    }

    @Test
    public void testIsEmpty() {
        // This is an example unit test
        assertTrue(mQueue.isEmpty());
    }

    @Test
    public void testNotEmpty() {
        // use enqueue() to add elements to the queue
        // test if the queue is not empty
        Integer exp = 5;
        mQueue.enqueue(exp);
        assertFalse(mQueue.isEmpty());
    }

    @Test
    public void testPeekEmptyQueue() {
        // peek() should return null if the queue is empty
        assertNull(mQueue.peek());
    }

    @Test
    public void testPeekNoEmptyQueue() {
        // TODO: write your own unit test
        // use enqueue() to add elements to the queue
        // use dequeue() to remove elements from the queue
        // use peek() to get the element at the head of the queue
        
        Integer exp = 5;
        mQueue.enqueue(exp);
        assertEquals(exp,mQueue.peek());
    }

    @Test
    public void testEnqueue() {
        // This is an example unit test
        for (int i = 0; i < testList.size(); i++) {
            mQueue.enqueue(testList.get(i));
            assertEquals(testList.get(0), mQueue.peek());
            assertEquals(i + 1, mQueue.size());
        }
    }

    @Test
    public void testDequeue() {
        // assert test list into queue
        // use dequeue() to remove elements from the queue
        // compare the removed element with the element in the test list
        if (mQueue.size() >0) {
            for (int i = 0; i < testList.size(); i++) {
                mQueue.dequeue();
                assertEquals(testList.get(i), mQueue.peek());
                assertEquals(testList.size() - i - 1, mQueue.size());
            }
        }
    }

    @Test
    public void testContent() throws IOException {
        // This is an example unit test
        InputStream in = new FileInputStream("src/test/resources/data.txt");
        try (Scanner scanner = new Scanner(in)) {
            scanner.useDelimiter("\\s*fish\\s*");

            List<Integer> correctResult = new ArrayList<>();
            while (scanner.hasNextInt()) {
                int input = scanner.nextInt();
                correctResult.add(input);
                System.out.println("enqueue: " + input);
                mQueue.enqueue(input);
            }

            for (Integer result : correctResult) {
                assertEquals(mQueue.dequeue(), result);
            }
        }
    }

    @Test
    public void testSize() {
        mQueue.enqueue(0);
        mQueue.enqueue(1);
        mQueue.enqueue(2);
        assertEquals(3, mQueue.size());
    }

    @Test
    public void testClear() {
        mQueue.enqueue(0);
        mQueue.enqueue(1);
        mQueue.enqueue(2);
        mQueue.clear();
        assertEquals(0, mQueue.size());
    }


    @Test
    public void testDequeueEmpty() {
        assertNull(mQueue.dequeue());
    }

    @Test
    public void testPeekEmpty() {
        assertNull(mQueue.peek());
    }

    @Test
    public void testSizeEmpty() {
        assertEquals(0, mQueue.size());
    }

    @Test
    public void testClearEmpty() {
        mQueue.clear();
        assertEquals(0, mQueue.size());
    }

    @Test
    public void testEnsureCapacity() {
        for (int i = 0; i < 100; i++) {
            mQueue.enqueue(i);
        }
        assertEquals(100, mQueue.size());
    }
}
