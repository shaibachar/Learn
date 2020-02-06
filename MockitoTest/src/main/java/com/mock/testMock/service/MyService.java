package com.mock.testMock.service;

public class MyService implements IMyService{

    @Override
    public String sayHello(){
        return "hello";
    }

    @Override
    public int calculate(int x, int y){
        int res = add(x,y);
        return res;
    }

    @Override
    public Integer add(Integer x, Integer y) {
        return x+y;
    }
}
