public interface UserInterface<T> {
    public void exportData(String filepath); // For exporting data

    public void importData(String filepath); // For importing data

    public void editAccount(String email, String password);

    public void deleteAccount();
}
