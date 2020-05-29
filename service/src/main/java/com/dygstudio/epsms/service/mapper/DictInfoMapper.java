package com.dygstudio.epsms.service.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dygstudio.epsms.service.entity.DictInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DictInfoMapper extends BaseMapper<DictInfo> {
    DictInfo findByValue(String value);
    DictInfo findObjectAndChildByValue(String value);
    List<DictInfo> findByParentId(String value);
    List<DictInfo> findByTopObject();
}
