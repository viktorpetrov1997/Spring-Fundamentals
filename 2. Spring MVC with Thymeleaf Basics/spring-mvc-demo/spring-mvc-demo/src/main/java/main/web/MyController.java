package main.web;

import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Map;

@RestController
@RequestMapping("/info") // Base path for all handler methods. A handler method is a method that handles HTTP requests.
public class MyController
{
    private Map<Integer, String> users = Map.of(
            1, "Ivan",
            2, "Joro",
            3, "Hristo"
    );

    @GetMapping("/time-now") // Another way: info/time-now. Instead of using the base path in @RequestMapping.
    public String getTimeNow()
    {
        return "Time now is: " + LocalDateTime.now();
    }

    @GetMapping("/today") // Another way: info/today. Instead of using the base path in @RequestMapping.
    public String getDayOfWeek()
    {
        return "Today is: " + LocalDateTime.now().getDayOfWeek().name();
    }

    @GetMapping("/users/{id}")
    public String getUsernameById(@PathVariable int id, @RequestParam("firstName") String firstName, @RequestParam("age") int age)
    {
        return users.get(id);
    }
}
