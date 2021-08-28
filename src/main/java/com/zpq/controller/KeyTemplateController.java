package com.zpq.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zpq.model.KeyTemplate;
import com.zpq.model.Result;
import com.zpq.service.KeyTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author 35147
 */
@RestController
@RequestMapping("/keyTemplate")
public class KeyTemplateController {
    @Autowired
    KeyTemplateService keyTemplateService;

    /**
     * 根据部门id查询模板名和模板id
     * @param deptId 部门id
     * @return
     */
    @RequestMapping("/getByDeptId")
    public Result<List<KeyTemplate>> getByDeptId(Integer deptId){
        QueryWrapper<KeyTemplate> sql = new QueryWrapper<>();
        sql.eq("dept_id",deptId);
        sql.select("template_id,template_name");
        List<KeyTemplate> list = keyTemplateService.list(sql);
        return new Result<>(true,200,"success",list);
    }

    /**
     * 根据部门id和模板id查询具体某个模板的所有信息
     * @param deptId 部门id
     * @param templateId 模板id
     * @return
     */
    @RequestMapping("/getTemplateDetail")
    public Result<KeyTemplate> getTemplateDetail(Integer deptId,Integer templateId){
        QueryWrapper<KeyTemplate> sql = new QueryWrapper<>();
        sql.eq("dept_id",deptId);
        sql.eq("template_id",templateId);
        return new Result<>(true,200,"success",keyTemplateService.getOne(sql));
    }

    /**
     * 为模板添加按键
     * @param keyTemplate 主要传的是templateId和添加新按键后的keyNames
     * @return
     */
    @RequestMapping("/addTemplateKey")
    public Result<Boolean> addTemplateKey(KeyTemplate keyTemplate){
        // 判断添加的按键是否有重复
        String keyNames = keyTemplate.getKeyNames();
        String[] keyNameArray = keyNames.split(",");
        Set<String> keyNameSet = new HashSet<>();
        for (String keyName : keyNameArray) {
            keyNameSet.add(keyName);
        }
        if(keyNameArray.length == keyNameSet.size()){
            keyTemplateService.updateById(keyTemplate);
            return new Result<>(true,200,"success",true);
        }
        return new Result<>(true,200,"新增失败",false);
    }

    @RequestMapping("/deleteTemplateKey")
    public Result<Boolean> deleteTemplateKey(KeyTemplate keyTemplate){
        KeyTemplate oldKeyTemplate = keyTemplateService.getById(keyTemplate.getTemplateId());
        String oldKeyNames = oldKeyTemplate.getKeyNames();
        String[] oldKeyNameArray = oldKeyNames.split(",");
        StringBuffer newKeyNames = new StringBuffer();
        String deleteKeyName = keyTemplate.getKeyNames();
        for (String oldKeyName : oldKeyNameArray) {
            if(!oldKeyName.equals(deleteKeyName)) {
                newKeyNames.append(oldKeyName+",");
            }
        }

        String newKeyNameStr = newKeyNames.toString();
        // 删除多余的逗号
        if("".equals(newKeyNameStr)){
            newKeyNames = null;
        }else {
            newKeyNameStr = newKeyNameStr.substring(0,newKeyNames.length()-1);
        }
        keyTemplate.setKeyNames(newKeyNameStr);
        keyTemplateService.updateById(keyTemplate);
        return new Result<>(true,200,"success",true);

    }
}
