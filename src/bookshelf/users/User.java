package bookshelf.users;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class User {
    private String login;
    private String password;
    private String email;
    private Boolean role;

    public Boolean getRole() {
        return role;
    }

    public void setRole(Boolean role) {
        this.role = role;
    }

    public User() {
    }

    public User(String login, String password) {

        try {
            FileReader fr = new FileReader("src/bookshelf/users/AllUsers.txt");
            BufferedReader reader = new BufferedReader(fr);
            String line;

            while ((line = reader.readLine()) != null) {
                String[] data = line.split(":");
                if ((data[0].equals(login)) && (data[1].equals(password))) {
                    this.login = login;
                    this.password = password;
                    this.email = data[2];
                    this.role = Boolean.valueOf(data[3]);
                    break;
                }
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
