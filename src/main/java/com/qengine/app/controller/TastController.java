package com.qengine.app.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author sergey
 */
@RestController
public class TastController {
    
    @GetMapping("/api/test")
    public String test(){
        return "API work!";
    }
}
