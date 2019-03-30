package com.neo.model;

import java.io.Serializable;


public class User implements Serializable {
        private static final long serialVersionUID = -3258839839160856613L;
        private String  id;
        private String userName;
        private String passWord;

        public User(String userName, String passWord) {
                this.userName = userName;
                this.passWord = passWord;
        }

        public String getId() {
                return id;
        }

        public void setId(String id) {
                this.id = id;
        }

        public String getUserName() {
                return userName;
        }

        public void setUserName(String userName) {
                this.userName = userName;
        }

        public String getPassWord() {
                return passWord;
        }

        public void setPassWord(String passWord) {
                this.passWord = passWord;
        }

        @Override
        public String toString() {
                return "User{" +
                        "id='" + id + '\'' +
                        ", userName='" + userName + '\'' +
                        ", passWord='" + passWord + '\'' +
                        '}';
        }
}
