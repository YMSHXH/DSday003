package com.example.king.dsday003.module;

import com.example.king.dsday003.contact.Contact;
import com.example.king.dsday003.utils.Api;
import com.example.king.dsday003.utils.NetUtile;

import java.util.Map;

public class Module implements Contact.ResPD {

    @Override
    public void setData(Map<String, String> params,String api,Contact.Imodule imodule) {
        NetUtile.getIntence().toPost(params,api,imodule);
    }

}
