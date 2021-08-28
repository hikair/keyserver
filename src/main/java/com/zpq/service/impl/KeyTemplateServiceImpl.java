package com.zpq.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zpq.dao.KeyTemplateDao;
import com.zpq.model.KeyTemplate;
import com.zpq.service.KeyTemplateService;
import org.springframework.stereotype.Service;

/**
 * @author 35147
 */
@Service("keyTemplateService")
public class KeyTemplateServiceImpl extends ServiceImpl<KeyTemplateDao, KeyTemplate> implements KeyTemplateService {
}
