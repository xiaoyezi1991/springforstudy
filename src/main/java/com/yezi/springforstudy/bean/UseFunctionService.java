package com.yezi.springforstudy.bean;

public class UseFunctionService {
    private FunctionService functionService;

    public void setFunctionService(FunctionService functionService) {
        this.functionService = functionService;
    }

    public void getBean(){
        this.functionService.getBean();
    }
}
