package com.bss.framework.core.design.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Rocky on 15-06-2019.
 */
@RestController
@RequestMapping("/application/schema")
@CrossOrigin(origins = "*", maxAge = 3600)
public class ApplicationLayoutController {
}
