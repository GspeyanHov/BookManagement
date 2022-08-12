package books.storage;

import books.exception.UserNotFoundException;
import books.model.User;
import books.model.UserType;


public class UserStorage {

    private static User[] array = new User[10];
    private static int size = 0;


    public User getUserByUserEmail(String email) {
        for (int i = 0; i < size; i++) {
            if (array[i].getEmail().equals(email)) {
                return array[i];
            }
        }
        return null;
    }

    private void extend() {
        User[] user = new User[array.length + 10];
        for (int i = 0; i < array.length; i++) {
            user[i] = array[i];
        }
        array = user;
    }

    public void add(User user) throws UserNotFoundException {
        if (array.length == size) {
            extend();
            throw new UserNotFoundException("User with that " + UserType.USER + "doesn't exist ");
        }
        array[size++] = user;
    }

    public int getSize() {
        return size;
    }
}

