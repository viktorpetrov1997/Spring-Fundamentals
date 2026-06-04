package bg.softuni.spring_core;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class GreetingService
{
    private final AddressService addressService;

    @Value("${user.username}")
    private String userName;

    @Autowired
    public GreetingService(AddressService addressService)
    {
        this.addressService = addressService;
    }

    public String getGreeting()
    {
        return "Hello " + userName + ", welcome to the Spring Core Lab! " + addressService.getAddress();
    }
}
