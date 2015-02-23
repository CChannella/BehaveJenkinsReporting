package net.python.behave.json;

import com.googlecode.totallylazy.Function1;

public class Tag {

     private String name;

     public Tag() {

     }

     public String getName() {
         return name;
     }

     public static class functions {

         public static Function1<Tag, String> getName() {
             return new Function1<Tag, String>() {
                 @Override
                 public String call(Tag tag) throws Exception {
                     return tag.getName();
                 }
             };
         }
     }}
  /*  private String[] name;

    public Tag() {

    }

    public String[] getName() {
        return name;
    }


}*/
