package hu.me.firstSpringApp;

import org.springframework.stereotype.Service;

@Service
public class MessageImpl implements Message{
    @Override
    public String getMessage(){
        return "Hello World!";
    }
}
