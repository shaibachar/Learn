package com.fun.fun.controller

@RestController
class FunController {


  @GetMapping("/fun")
  public String fun() {
    return "super fun";
  }
}