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
public class adsController {

    /**
     * list create
     */
    private List<adsGetter> adsList;
    private adsGetter adsObj;
    int count;

    /**
     * constructor class
     */
    public adsController() {
        /**
         * calling the fetchData method to fetch the data from the
         */
        adsObj = new adsGetter();
        fetchData();
    }

    /**
     * fetch data from database
     */
    public void fetchData() {
        try {

            adsList = new ArrayList<>();
            Connection conn = connection.getConnection();
            Statement stmt = conn.createStatement();

            ResultSet rs = stmt.executeQuery("SELECT * FROM ads");
            while (rs.next()) {

                adsGetter v = new adsGetter();
                //setting the value in the getter and setter
                v.setAdId(rs.getInt("id1"));
                v.setId(rs.getInt("id"));
                v.setTitle(rs.getString("title"));
                v.setCategory(rs.getString("category"));
                v.setDescription(rs.getString("description"));
                v.setYear(rs.getInt("year"));
                v.setBrand(rs.getString("brand"));
                v.setColor(rs.getString("color"));
                v.setPrice(rs.getInt("price"));
                v.setModel(rs.getString("model"));
                v.setEmail(rs.getString("email"));

                /**
                 * adding the value in the list
                 */
                adsList.add(v);
            }
        } catch (SQLException ex) {
            Logger.getLogger(adsController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * used to insert the data into the database for the table ads
     *
     */
    public String add(int id) {
        try {

            Connection conn = connection.getConnection();
            String sql = "INSERT INTO ads(id,title,category,description,year,brand,color,price,model,email) VALUES (?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setInt(1, id);
            pstmt.setString(2, adsObj.getTitle());
            pstmt.setString(3, adsObj.getCategory());
            pstmt.setString(4, adsObj.getDescription());
            pstmt.setInt(5, adsObj.getYear());
            pstmt.setString(6, adsObj.getBrand());
            pstmt.setString(7, adsObj.getColor());
            pstmt.setInt(8, adsObj.getPrice());
            pstmt.setString(9, adsObj.getModel());
            pstmt.setString(10, adsObj.getEmail());

            pstmt.executeUpdate();
            adsList.add(adsObj);
            adsObj = new adsGetter();
            fetchData();
        } catch (SQLException ex) {
            Logger.getLogger(adsController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "index";
    }

    /**
     * view add method
     *
     */
    public String viewAds(adsGetter a) {
        adsObj = a;
        return "viewads.xhtml";
    }

    /**
     * list create
     *
     * @return
     */
    public List<adsGetter> getAdsList() {
        return adsList;
    }

    /**
     * getters for object
     *
     * @return
     */
    public adsGetter getAdsObj() {
        return adsObj;
    }

    /**
     * setter for object
     *
     * @param adsObj
     */
    public void setAdsObj(adsGetter adsObj) {
        this.adsObj = adsObj;
    }

}
