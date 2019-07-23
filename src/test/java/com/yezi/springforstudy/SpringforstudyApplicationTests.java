package com.yezi.springforstudy;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringforstudyApplicationTests {

    @Test
    public void contextLoads() {
        String  path = "D:\\Program Files\\repository\\org\\springframework\\spring-beans\\5.0.10.RELEASE\\spring-beans-5.0.10.RELEASE.jar";
        JarFile jfile = null;
        try {
            jfile = new JarFile(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
        ;
        Enumeration files = jfile.entries();
        int classCount  = 0;
        while(files.hasMoreElements())
        {
            JarEntry entry = (JarEntry)files.nextElement();
            if(entry.getName().endsWith(".class")){
                System.out.println(entry.getName());
                classCount++;
            }
        }
        System.out.println(classCount);
    }

}
