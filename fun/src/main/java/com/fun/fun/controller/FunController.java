<<<<<<< HEAD
package com.fun.fun.controller;

import com.fun.fun.services.FunService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FunController {

    @Autowired
    private FunService funService;

    @GetMapping("/fun")
    public String getFun() {
        return "super fun";
    }

    @GetMapping("/twoSum")
    public int[] calcTwoSum() {
        int[] nums = {5, 2, 4, 6, 10};
        int[] ints = funService.twoSum(nums, 10);
        return ints;
    }
}
=======
package com.fun.fun.controller

@RestController
class FunController {


  @GetMapping("/fun")
  public String fun() {
    return "super fun";
  }
}
>>>>>>> 98afdbecff07d124d0063bc06465ab08d9872b57
