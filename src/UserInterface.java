public interface UserInterface<T> {
    public void exportData(String filepath); // For exporting data

    public void importData(String filepath); // For importing data

    public T createAccount();

    public T editAccount();

    public void deleteAccount();
}
