public class UserDataManager {
    private UserDataProvider userDataProvider;

    public UserDataManager(UserDataProvider userDataProvider) {
        this.userDataProvider = userDataProvider;
    }
    public String getUserInfo() {
        return userDataProvider.getUserDetails();
    }
}
