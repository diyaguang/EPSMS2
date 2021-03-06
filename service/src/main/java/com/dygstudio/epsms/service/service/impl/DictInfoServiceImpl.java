package com.dygstudio.epsms.service.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dygstudio.epsms.service.entity.DictInfo;
import com.dygstudio.epsms.service.mapper.DictInfoMapper;
import com.dygstudio.epsms.service.service.DictInfoService;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 〈功能概述〉
 *
 * @className: DictInfoServiceImpl
 * @package: com.dygstudio.epsms.service.service.impl
 * @author: diyaguang
 * @date: 2020/5/3 17:20
 */
@Service
public class DictInfoServiceImpl extends ServiceImpl<DictInfoMapper, DictInfo> implements DictInfoService {

    @Resource
    DictInfoMapper dictInfoMapper;

    public DictInfo findObjectAndChildByValue(String value){
        return dictInfoMapper.findObjectAndChildByValue(value);
    }
    public DictInfo findObjectAndChildById(String id){
        return dictInfoMapper.findObjectAndChildById(id);
    }
    public List<DictInfo> findByParentId(String value){
        return dictInfoMapper.findByParentId(value);
    }

    @Cacheable(value = "dictInfo")
    public List<DictInfo> findByTopObject(){
        return dictInfoMapper.findByTopObject();
    }
}
