package com.cangmaomao.network.request.bean;

/**
 * Created by 17246 on 2017/11/5.
 * Login解析登陆
 */

public class Login {
    private boolean success;
    private String code;
    private String message;
    private LoginData data;
    public class LoginData{
        private String token;
        private String username;
        private String cur_user;

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getCur_user() {
            return cur_user;
        }

        public void setCur_user(String cur_user) {
            this.cur_user = cur_user;
        }

        @Override
        public String toString() {
            return "LoginData{" +
                    "token='" + token + '\'' +
                    ", username='" + username + '\'' +
                    ", cur_user='" + cur_user + '\'' +
                    '}';
        }
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LoginData getData() {
        return data;
    }

    public void setData(LoginData data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Login{" +
                "success='" + success + '\'' +
                ", code='" + code + '\'' +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }
}
