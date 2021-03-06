package com.wcs.showcase.test;

import javax.ejb.Local;
import javax.inject.Named;

/**
* <p>Project: btcbase</p> 
* <p>Title: GreetingManagerBean.java</p> 
* <p>Description: </p> 
* <p>Copyright: Copyright .All rights reserved.</p> 
* <p>Company: wcs.com</p> 
* @author <a href="mailto:yujingu@wcs-gloabl.com">Yu JinGu</a>
 */
@Local(GreetingManager.class)
@Named(value = "greetingManagerBean")
public class GreetingManagerBean implements GreetingManager {  
    public String greet(String userName) {
        return "Hello " + userName;
    }
}
