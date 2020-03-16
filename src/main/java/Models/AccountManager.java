package Models;

import java.util.HashMap;

public class AccountManager extends DBManager {
    private String userName;
    private String password;
    private static AccountManager ac;

    private AccountManager(){
        super("Account");
    }

    public static AccountManager getAccount(){
        if (ac==null) {
            ac=new AccountManager();
            return ac;
        }
        else {
           return ac;
        }
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public String insertAccount(){
        HashMap<String,String> AccDet=new HashMap();
        AccDet.put("userName",ac.getUserName());
        AccDet.put("password",ac.getPassword());
        insert(AccDet);
        return "Successful";
    }

}
