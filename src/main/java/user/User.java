package user;

import subscriber.Subscriber;

public class User implements Subscriber {

    private int id;
    private String name;
    private String surnames;
    private String email;
    private boolean subscribed;

    public User(String name, String surnames, String email, boolean subscribed) {

        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Invalid name");
        }

        if (surnames == null || surnames.trim().isEmpty()) {
            throw new IllegalArgumentException("Invalid surname(s)");
        }

        if (email == null || email.trim().isEmpty() || !email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9-]+\\.[A-Za-z]{2,}$")) {
            throw new IllegalArgumentException("Invalid email");
        }

        this.name = name;
        this.surnames = surnames;
        this.email = email;
        this.subscribed = subscribed;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Invalid name");
        }
        this.name = name;
    }

    public String getSurnames() {
        return surnames;
    }

    public void setSurnames(String surnames) {
        if (surnames == null || surnames.trim().isEmpty()) {
            throw new IllegalArgumentException("Invalid surname(s)");
        }
        this.surnames = surnames;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        if (email == null || email.trim().isEmpty() || !email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9-]+\\.[A-Za-z]{2,}$")) {
            throw new IllegalArgumentException("Invalid email");
        }
        this.email = email;
    }

    public boolean isSubscribed() {
        return subscribed;
    }

    public void setSubscribed(boolean subscribed) {
        this.subscribed = subscribed;
    }

    @Override
    public void receiveNotification(String notification) {
        System.out.println("To: " + email);
        System.out.println("Dear " + name + ",");
        System.out.println(notification);
    }

    @Override
    public String toString() {
        return "User { id: " + id + ", name: " + name + ", surnames: " + surnames + ", email: " + email + ", subscribed: " + (subscribed ? "yes" : "no") + " }";
    }
}
