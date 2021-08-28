package com;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zpq.model.*;
import com.zpq.service.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = App.class)
public class KeyConfigServer01 {

    @Autowired
    KeyListenerService keyListenerService;

    @Autowired
    KeyUserService keyUserService;

    @Autowired
    DeptmentService deptmentService;

    @Autowired
    KeyTemplateService keyTemplateService;

    @Autowired
    MenuService menuService;

    @Test
    public void stringTest(){
        String str = "a,b,c,d";
        System.out.println(str.endsWith(",")?str.substring(0,str.length()-1):str);
    }

    @Test
    public void deptTest(){
        List<Deptment> allDeptWithTemplate = deptmentService.getAllDeptWithTemplate();
        for (Deptment deptment : allDeptWithTemplate) {
            System.out.println(deptment);
        }
    }

    @Test
    public void menuTest(){
        List<Menu> list = menuService.list();
        for (Menu menu : list) {
            System.out.println(menu);
        }
    }

    @Test
    public void test02(){
        QueryWrapper<KeyTemplate> sql = new QueryWrapper<>();
        sql.eq("dept_id",1);
        sql.eq("template_id",1);
        List<KeyTemplate> list = keyTemplateService.list(sql);
        System.out.println(list);
    }

    @Test
    public void test01(){
        Map map = new HashMap();
        map.put("hostAddress","192.168.2.6");
        map.put("keyNames","回车,退格,删除,alt+回车");
        map.put("createDate","2020-12-12 09:08:24");
        Map<String, int[]> stringMap = keyListenerService.countKey(map,1);
        System.out.println(stringMap);
    }

    @Test
    public void testGetByIp(){
        KeyUser keyUser = new KeyUser();
        keyUser.setHostAddress("127.0.0.1");
        keyUser.setListenKey("回车");
        keyUserService.updateByIp(keyUser);
    }

    @Test
    public void testAscii(){
        char a = '\n';
        System.out.println((int)a);
    }

    @Test
    public void testList(){
        List<KeyListener> list = keyListenerService.list();
        System.out.println(list);
    }

    @Test
    public void testUserList(){
        Page<KeyUser> page = new Page<>(1,5);
        keyUserService.page(page);
        System.out.println(page.getRecords());
    }

    @Test
    public void testGetIps(){
        QueryWrapper<KeyListener> sql = new QueryWrapper<>();
        sql.select("distinct host_address");
        List<KeyListener> list = keyListenerService.list(sql);
        System.out.println(list);
    }

    @Test
    public void testGetAllDates(){
        List<String> allDates = keyListenerService.getAllDates();
        System.out.println(allDates);
    }

    @Test
    public void testGetKeyNames(){
        Map map = new HashMap<>(2);
        map.put("ip","127.0.0.1");
        map.put("date","2020-11-16");
        List<String> list = keyListenerService.getKeyNames(map);
        System.out.println(list);
    }

    @Test
    public void testCount(){
        Map map = new HashMap(4);
        map.put("hostAddress","127.0.0.1");
        map.put("createDate","2020-11-16");
        map.put("keyNames","A,B,退格,回车,");
        Map<String, List<Integer>> stringListMap = keyListenerService.list2(map);
        System.out.println(stringListMap);
    }
}
