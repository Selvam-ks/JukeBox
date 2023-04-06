package com.dao;

import com.model.SongModel;
import com.model.UserInfo;
import com.model.UserLogs;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.sql.*;
import java.util.Arrays;

import static com.dao.Dao.getConnection;
import static com.model.UserInfo.setIsLOGIN;
import static com.model.UserInfo.setUSER;

public class UserDAO implements UserLogs {
    static final String ALG = "MD5";
    ResultSet rs=null;
    Statement st=null;
    Connection con = null;
    PreparedStatement pst;
    @Override
    public int signUp(UserInfo userInfo) {
        con = getConnection();
        String user_id="";
        byte[] salt= createdSalt();
        int rowAdded = 0;
        try {
            String hash = passwordEncoder(userInfo.getUser_pass(),salt);
            st = con.createStatement();
            pst = con.prepareStatement("INSERT INTO jukebox.userlog (user_name,user_pass,salt) value (?,?,?)");
            pst.setString(1,userInfo.getUser_name());
            pst.setString(2,hash);
            pst.setBytes(3,salt);
            rowAdded = pst.executeUpdate();
        }catch (SQLDataException a){
            throw new RuntimeException(a.getMessage());
        }
        catch (Exception e)
        {
            System.out.println("username already exists"+e.getMessage());
        }
        return rowAdded;
    }

    @Override
    public boolean login(UserInfo userInfo) {
        boolean flag = false;
        con = getConnection();
        UserInfo getUser = new UserInfo();
        try{
            st = con.createStatement();
            rs = st.executeQuery("select * from jukebox.userlog where user_name ='"+userInfo.getUser_name()+"'");
            if (!rs.next()){
                System.out.print("User Not Found");
            }else {
                do{
                    getUser = new UserInfo(rs.getInt("user_id"),rs.getString("user_name"),rs.getString("user_pass"),rs.getBytes("salt"));
                } while (rs.next());
            }
            String hash = passwordEncoder(userInfo.getUser_pass(), getUser.getSalt());
            if(hash.equals(getUser.getUser_pass())){
                flag = true;
                System.out.println("Success USER_ID : "+getUser.getUser_id());
                setUSER(getUser.getUser_id());
                setIsLOGIN(true);
            }else
                System.out.println("Failed");
        }catch (Exception e) {
            System.out.println(" try again");
        }
        return flag;
    }
    protected static byte[] createdSalt(){
        byte[] bytes = new byte[10];
        SecureRandom random = new SecureRandom();
        random.nextBytes(bytes);
        return bytes;
    }
    protected String passwordEncoder(String passR,byte[] salt) throws NoSuchAlgorithmException {
        MessageDigest messageDigest = MessageDigest.getInstance(ALG);
        messageDigest.reset();
        messageDigest.update(salt);
        byte[] md = messageDigest.digest(passR.getBytes());
        BigInteger bigInteger = new BigInteger(1,md);
        return bigInteger.toString(16);
    }
}
