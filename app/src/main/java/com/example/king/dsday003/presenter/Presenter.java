package com.example.king.dsday003.presenter;

import com.example.king.dsday003.contact.Contact;
import com.example.king.dsday003.module.Module;

import java.util.Map;

public class Presenter {

    private Module module;
    private Contact.Iview iview;

    public Presenter(Contact.Iview iview) {
        this.iview = iview;
        this.module = new Module();
    }

    public void login(Map<String, String> params, String api) {



        if (module != null){
            module.setData(params,api,new Contact.Imodule() {
                @Override
                public void success(Object o) {
                    if (iview != null){
                        iview.success(o);
                    }
                }

                @Override
                public void error(String meg) {
                    if (iview != null){
                        iview.pwdError(meg);
                    }
                }
            });
        }
    }
}
