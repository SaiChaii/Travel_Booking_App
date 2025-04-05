package ticket.booking.services;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import ticket.booking.entities.User;
import ticket.booking.util.UserServiceUtil;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class UserBookingService
{
    private User user;
    private List<User> userList;
    private static final String USERS_PATH="../localDb/users.json";
    private ObjectMapper objectMapper = new ObjectMapper();
//    private Object UserServiceUtil;

    public UserBookingService(User user1) throws IOException
    {
        this.user=user1;
        File users=new File(USERS_PATH);
        userList = objectMapper.readValue(users,new TypeReference<List<User>>() {});
    }

    public UserBookingService() throws IOException
    {
        File users=new File(USERS_PATH);
        userList = objectMapper.readValue(users,new TypeReference<List<User>>() {});
    }

    public Boolean loginUser(){
        Optional<User> foundUser = userList.stream().filter(user -> {
            return user.getName().equals(user.getName()) && UserServiceUtil.checkPassword(user.getPassword(),user.getHashedPassword());
        }).findFirst();
        return foundUser.isPresent();
    }

    public Boolean signUpUser(User user1){
        try {
            userList.add(user);
            saveUserListToFile();
            return Boolean.TRUE;
        } catch (IOException e) {
            return Boolean.FALSE;
        }
    }

    public void saveUserListToFile() throws IOException{
        File usersFile=new File(USERS_PATH);
        objectMapper.writeValue(usersFile,userList);
    }

    public void fetchBoookings(){
        user.printTickets();
    }

    public Boolean cancelBooking(String TicketId){
        Boolean a=user.getTicketsBooked().stream().anyMatch(e-> e.getTicketId() == TicketId);
        return a;
    }

}
