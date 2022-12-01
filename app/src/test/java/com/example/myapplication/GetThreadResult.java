package com.example.myapplication;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.util.Objects;
import java.util.concurrent.locks.LockSupport;
import java.util.function.Supplier;

public class GetThreadResult<T> implements Runnable{
   private Supplier<T> supplier;
    private State state;

    public GetThreadResult(Supplier<T> supplier){
        this.supplier = supplier;
        this.state = State.INIT;
    }
    private Thread getResultThread;

    private T res;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void run() {
        res = supplier.get();
        this.state = State.COMPLETED;
        if(Objects.nonNull(this.getResultThread)){
            LockSupport.unpark(this.getResultThread);
        }
    }

    public T get(){
        if(this.state == State.COMPLETED){
            return res;
        }
        this.getResultThread = Thread.currentThread();
        LockSupport.park();
        return res;
    }

    enum State{
        INIT,
        COMPLETED;
    }
}
