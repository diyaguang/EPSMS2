package com.dygstudio.epsms.service.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dygstudio.epsms.service.entity.Role;
import com.dygstudio.epsms.service.entity.User;
import javafx.scene.control.Pagination;
import org.apache.ibatis.annotations.Mapper;

import java.util.HashMap;
import java.util.List;
import java.util.Set;

@Mapper
public interface UserMapper extends BaseMapper<User> {
    List<User> findAllUserForShow(Page<User> page,User queryItem);
}
