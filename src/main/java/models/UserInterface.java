package models;

public interface UserInterface<T> {
    public Boolean userExists(String email, String password);

    public T createAccount(String email, String password);

    public T getAccount(String email, String password);

    public void editAccount(String email, String password);

    public void deleteAccount();
}
