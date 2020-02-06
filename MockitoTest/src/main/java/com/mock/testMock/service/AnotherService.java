package com.mock.testMock.service;

public class AnotherService implements IAnotherService {

    private final IMyService myService;

    public AnotherService(IMyService myService) {
        this.myService = myService;
    }

    @Override
    public String getCalculationMessage(){
        int calculate = myService.calculate(6, 7);
        return String.format("calculationResults of 6,7:%d", calculate);
    }
}
