package app.controller;

import app.service.MainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private MainService mainService;

    @GetMapping(value = "/get")
    public String testGet() {
        mainService.test();
        return "OK";
    }

    @PostMapping(value = "/post")
    public String testPost(@RequestBody String str) {
        return "OK";
    }
}
