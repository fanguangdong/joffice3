package com.ocsoft.core.util;

import com.fasterxml.jackson.databind.ObjectMapper;


  
public class HibernateAwareObjectMapper extends ObjectMapper {  
  
    public HibernateAwareObjectMapper() {  
       // HibernateModule hm = new HibernateModule();  
        //registerModule(hm);  
       // configure(Feature.FAIL_ON_EMPTY_BEANS, false);  
    }  
  
    public void setPrettyPrint(boolean prettyPrint) {  
        //configure(Feature.INDENT_OUTPUT, prettyPrint);  
    }  
}  
