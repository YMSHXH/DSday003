package com.example.king.dsday003.module;

import com.example.king.dsday003.contact.Contact;
import com.example.king.dsday003.utils.Api;
import com.example.king.dsday003.utils.NetUtile;

public class Module implements Contact.ResPD {

    @Override
    public void setData(Contact.Imodule imodule) {
        NetUtile.getIntence().toGet(Api.API,imodule);
    }

}
