package com.delaroystudios.userauthentication.model;

/**
 * Created by delaroy on 6/26/18.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserLogin {

    @SerializedName("error")
    @Expose
    private Boolean error;
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("username")
    @Expose
    private String username;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("apikey")
    @Expose
    private String apikey;

    /**
     * No args constructor for use in serialization
     *
     */
    public UserLogin() {
    }

    /**
     *
     * @param id
     * @param username
     * @param error
     * @param email
     * @param name
     * @param apikey
     */
    public UserLogin(Boolean error, Integer id, String name, String username, String email, String apikey) {
        super();
        this.error = error;
        this.id = id;
        this.name = name;
        this.username = username;
        this.email = email;
        this.apikey = apikey;
    }

    public Boolean getError() {
        return error;
    }

    public void setError(Boolean error) {
        this.error = error;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getApikey() {
        return apikey;
    }

    public void setApikey(String apikey) {
        this.apikey = apikey;
    }

}
