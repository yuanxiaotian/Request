package com.cangmaomao.network.myapplication;

import java.io.Serializable;
import java.util.List;

/**
 * Created by 17246 on 2017/9/21.
 * 用来解析Login
 */

public class Login {
    private LoginData data;
    private boolean success;
    private String code;
    private String message;

    public static class LoginData implements Serializable{
        private String loginId;
        private String loginPassWord;
        private String isonline;//0店内/1到店就餐/2取餐
        private String ordertime;//时间搓
        private String client_count;//人数
        private String aa;//订单制度
        private String cur_user;
        private String token;
        private String firstname;
        private String lastname;
        private String headpic;
        private String score;//积分
        private String email;
        private String buscode;//如果存在未支付的订单则不为null
        private String busname;//商户名称
        private String tablenum;//桌号
        private String on;//如果存在未支付的订单则不为null
        private String addon_on;//如果存在未支付的订单则不为null
        private String host;//发起人uid
        private Object payfor;//代付信息
        private String order_confirm;//是否下单:0否/1是
        private String tips;//小费
        private String tip_rate;//小费默认费率
        private String remark;//备注
        private String pa_user;//请客方标记
        private String bus_stime;//营业起始时间
        private String bus_etime;//营业结束时间
        private String ismyfav;//收藏字段
        private String invite_tip_count;//未处理的邀请信息数
        private String friend_tip_count;//未处理的好友请求信息数
        private String notice_tip_count;//未处理的付款通知信息数

        public String getTips() {
            return tips;
        }

        public void setTips(String tips) {
            this.tips = tips;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public String getLoginId() {
            return loginId;
        }

        public String getLoginPassWord() {
            return loginPassWord;
        }

        public void setLoginId(String loginId) {
            this.loginId = loginId;
        }

        public void setLoginPassWord(String loginPassWord) {
            this.loginPassWord = loginPassWord;
        }

        public String getIsonline() {
            return isonline;
        }

        public void setIsonline(String isonline) {
            this.isonline = isonline;
        }

        public String getBusname() {
            return busname;
        }

        public void setBusname(String busname) {
            this.busname = busname;
        }

        public String getTablenum() {
            return tablenum;
        }

        public void setTablenum(String tablenum) {
            this.tablenum = tablenum;
        }

        public String getOrdertime() {
            return ordertime;
        }

        public void setOrdertime(String ordertime) {
            this.ordertime = ordertime;
        }

        public String getClient_count() {
            return client_count;
        }

        public void setClient_count(String client_count) {
            this.client_count = client_count;
        }

        public String getAa() {
            return aa;
        }

        public void setAa(String aa) {
            this.aa = aa;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public String getCur_user() {
            return cur_user;
        }

        public void setCur_user(String cur_user) {
            this.cur_user = cur_user;
        }

        public String getFirstname() {
            return firstname;
        }

        public void setFirstname(String firstname) {
            this.firstname = firstname;
        }

        public String getLastname() {
            return lastname;
        }

        public void setLastname(String lastname) {
            this.lastname = lastname;
        }

        public String getHeadpic() {
            return headpic;
        }

        public void setHeadpic(String headpic) {
            this.headpic = headpic;
        }

        public String getScore() {
            return score;
        }

        public void setScore(String score) {
            this.score = score;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getBuscode() {
            return buscode;
        }

        public void setBuscode(String buscode) {
            this.buscode = buscode;
        }

        public String getOn() {
            return on;
        }

        public void setOn(String on) {
            this.on = on;
        }

        public String getAddon_on() {
            return addon_on;
        }

        public void setAddon_on(String addon_on) {
            this.addon_on = addon_on;
        }

        public String getHost() {
            return host;
        }

        public void setHost(String host) {
            this.host = host;
        }

        public Object getPayfor() {
            return payfor;
        }

        public void setPayfor(Object payfor) {
            this.payfor = payfor;
        }

        public String getOrder_confirm() {
            return order_confirm;
        }

        public void setOrder_confirm(String order_confirm) {
            this.order_confirm = order_confirm;
        }

        public String getPa_user() {
            return pa_user;
        }

        public void setPa_user(String pa_user) {
            this.pa_user = pa_user;
        }

        public String getBus_stime() {
            return bus_stime;
        }

        public void setBus_stime(String bus_stime) {
            this.bus_stime = bus_stime;
        }

        public String getBus_etime() {
            return bus_etime;
        }

        public void setBus_etime(String bus_etime) {
            this.bus_etime = bus_etime;
        }

        public String getIsmyfav() {
            return ismyfav;
        }

        public void setIsmyfav(String ismyfav) {
            this.ismyfav = ismyfav;
        }

        public String getInvite_tip_count() {
            return invite_tip_count;
        }

        public void setInvite_tip_count(String invite_tip_count) {
            this.invite_tip_count = invite_tip_count;
        }

        public String getFriend_tip_count() {
            return friend_tip_count;
        }

        public void setFriend_tip_count(String friend_tip_count) {
            this.friend_tip_count = friend_tip_count;
        }

        public String getNotice_tip_count() {
            return notice_tip_count;
        }

        public void setNotice_tip_count(String notice_tip_count) {
            this.notice_tip_count = notice_tip_count;
        }

        public String getTip_rate() {
            return tip_rate;
        }

        public void setTip_rate(String tip_rate) {
            this.tip_rate = tip_rate;
        }

        @Override
        public String toString() {
            return "LoginData{" +
                    "loginId='" + loginId + '\'' +
                    ", loginPassWord='" + loginPassWord + '\'' +
                    ", isonline='" + isonline + '\'' +
                    ", ordertime='" + ordertime + '\'' +
                    ", client_count='" + client_count + '\'' +
                    ", aa='" + aa + '\'' +
                    ", cur_user='" + cur_user + '\'' +
                    ", token='" + token + '\'' +
                    ", firstname='" + firstname + '\'' +
                    ", lastname='" + lastname + '\'' +
                    ", headpic='" + headpic + '\'' +
                    ", score='" + score + '\'' +
                    ", email='" + email + '\'' +
                    ", buscode='" + buscode + '\'' +
                    ", busname='" + busname + '\'' +
                    ", tablenum='" + tablenum + '\'' +
                    ", on='" + on + '\'' +
                    ", addon_on='" + addon_on + '\'' +
                    ", host='" + host + '\'' +
                    ", payfor=" + payfor +
                    ", order_confirm='" + order_confirm + '\'' +
                    ", tips='" + tips + '\'' +
                    ", tip_rate='" + tip_rate + '\'' +
                    ", remark='" + remark + '\'' +
                    ", pa_user='" + pa_user + '\'' +
                    ", bus_stime='" + bus_stime + '\'' +
                    ", bus_etime='" + bus_etime + '\'' +
                    ", ismyfav='" + ismyfav + '\'' +
                    ", invite_tip_count='" + invite_tip_count + '\'' +
                    ", friend_tip_count='" + friend_tip_count + '\'' +
                    ", notice_tip_count='" + notice_tip_count + '\'' +
                    '}';
        }
    }

    public LoginData getData() {
        return data;
    }

    public void setData(LoginData data) {
        this.data = data;
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

    @Override
    public String toString() {
        return "Login{" +
                "data=" + data +
                ", success='" + success + '\'' +
                ", code='" + code + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
