package bg.softuni.spring_core;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class AddressService
{
    @Value("${user.address}")
    private String address;

    public String getAddress()
    {
        return "My address is " + address + ".";
    }
}
