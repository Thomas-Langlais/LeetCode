import java.util.concurrent.*; 

class FooBar {
    private int n;
    private Semaphore FooPermit;
    private Semaphore BarPermit;

    public FooBar(int n) {
        this.n = n;
        this.FooPermit = new Semaphore(1);
        this.BarPermit = new Semaphore(0);
    }

    public void foo(Runnable printFoo) throws InterruptedException {
        
        for (int i = 0; i < n; i++) {
            // obtain a foo permit to print foo. on the next iteration, the thread will
            // wait until a permit is available
            FooPermit.acquire();
        	// printFoo.run() outputs "foo". Do not change or remove this line.
        	printFoo.run();
            // signal that a bar permit is available to print bar
            BarPermit.release();
        }
    }

    public void bar(Runnable printBar) throws InterruptedException {
        
        for (int i = 0; i < n; i++) {
            // obtain a bar permit to print foo. on the next iteration, the thread will
            // wait until a permit is available
            BarPermit.acquire();
            // printBar.run() outputs "bar". Do not change or remove this line.
        	printBar.run();
            // release the bar permit to emit that we have printed bar
            FooPermit.release();
        }
    }
}