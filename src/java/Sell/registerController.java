/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Sell;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

/**
 *
 * @author c0691289
 */
@Named
@ApplicationScoped
public class registerController {

    /**
     * created the list of the registration
     */
    private List<registerSetter> regList = new ArrayList<>();
    private registerSetter registerObj = new registerSetter(); //created the object of the register pojo class

    int take=15;

    /**
     * created the list of the registration
     */
    public registerController() {
        registerObj = new registerSetter();
        fetchData1();
    }

    /**
     * created the list of the registration
     */
    public void fetchData1() {
        try {
            regList = new ArrayList<>();
            Connection conn = connection.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM easysell");
            while (rs.next()) {
                registerSetter p = new registerSetter();
                p.setId(rs.getInt("id"));
                p.setFirstName(rs.getString("fName"));
                p.setLastName(rs.getString("lName"));
                p.setEmail(rs.getString("email"));
                p.setUserName(rs.getString("username"));
                p.setPassword(rs.getString("password"));
                p.setConfirmPassword(rs.getString("cpassword"));
                regList.add(p);
            }
        } catch (SQLException ex) {
            Logger.getLogger(registerController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * add method is used to add the data to the database in easysell table
     *
     * @return
     */
    public String add() {
        try {
            Connection conn = connection.getConnection();
            String sql = "INSERT INTO easysell VALUES (?,?,?,?,?,?,?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, take);
            pstmt.setString(2, registerObj.getFirstName());
            pstmt.setString(3, registerObj.getLastName());
            pstmt.setString(4, registerObj.getEmail());
            pstmt.setString(5, registerObj.getUserName());
            pstmt.setString(6, registerObj.getPassword());
            pstmt.setString(7, registerObj.getConfirmPassword());

            pstmt.executeUpdate();
            regList.add(registerObj);
            registerObj = new registerSetter();
            fetchData1();
        } catch (SQLException ex) {
            Logger.getLogger(registerController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "index";
    }

    /**
     * checkuser method is used to retrieve the data from the database
     *
     * @param user
     * @param pass
     * @return
     * @throws SQLException
     */
    public String checkUser(String user, String pass) throws SQLException {
        Connection conn = connection.getConnection();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("Select * FROM easysell");
        while (rs.next()) {
            if (user.equals(rs.getString("userName")) && pass.equals(rs.getString("password"))) {
                registerObj.setUserName(rs.getString("userName"));
                registerObj.setId(rs.getInt("id"));
                registerObj.setButton(false);
                registerObj.setWelcome("block");
                registerObj.setFirstName(rs.getString("fName"));
                registerObj.setEmail(rs.getString("email"));
                registerObj.setProperty("none");
                return "index";
            }
        }
        return "";
    }

    /**
     * get method
     *
     * @return
     */
    

    /**
     * get register object
     *
     * @return
     */
    public registerSetter getRegisterObj() {
        return registerObj;
    }

    /**
     * set register object
     *
     * @param registerObj
     */
    public void setRegisterObj(registerSetter registerObj) {
        this.registerObj = registerObj;
    }

    /**
     * list create
     *
     * @return
     */
    public List<registerSetter> getRegList() {
        return regList;
    }

}
