import Auth.Authentication;

public class App {

    public static void main(String[] args) throws Exception {
        Authentication auth = new Authentication();
        String email = "";
        String password = "";
        auth.loginAkun(email, password);
    }

}
