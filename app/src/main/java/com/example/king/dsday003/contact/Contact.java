package com.example.king.dsday003.contact;

public interface Contact {

    interface Imodule{
        void success(Object o);
        void error(String meg);
    }

    interface ResPD {
        void setData(Imodule data);
    }

    interface Iview{
        void mobileError(String msg);
        void pwdError(String msg);
        void failure(String msg);
        void success(Object o);
        void successMsg(String msg);
    }
}
