package com.example.king.dsday003.presenter;

import com.example.king.dsday003.contact.Contact;
import com.example.king.dsday003.module.Module;

public class Presenter {

    private Module module;
    private Contact.Iview iview;

    public Presenter(Contact.Iview iview) {
        this.iview = iview;
        this.module = new Module();
    }

    public void login() {
        if (module != null){
            module.setData(new Contact.Imodule() {
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
