package io.learning.hs.mooc.dashboard;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Hyeonsoo
 * @version 1.0
 * @description Do nothing
 */
@Controller
public class DashboardController {

    @GetMapping("/dboard")
    public String goDboard() {

        return "dboard";
    }
}
