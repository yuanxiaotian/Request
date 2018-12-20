package com.cangmaomao.network.myapplication;


import androidx.work.Worker;

/**
 * Author:帅气的potato
 */

public class DemoWork extends Worker{

    @Override
    public WorkerResult doWork() {

        return WorkerResult.SUCCESS;
    }


}
