package com.example.king.dsday003.contact;

import java.util.Map;

public interface Contact {

    interface Imodule{
        void success(Object o);
        void error(String meg);
    }

    interface ResPD {
        void setData(Map<String, String> params,String api, Imodule data);
    }

    interface Iview{
        void mobileError(String msg);
        void pwdError(String msg);
        void failure(String msg);
        void success(Object o);
        void successMsg(String msg);
    }
}
