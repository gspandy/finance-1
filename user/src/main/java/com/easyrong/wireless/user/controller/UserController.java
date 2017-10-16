package com.easyrong.wireless.user.controller;

import com.easyrong.wireless.user.base.BaseErrorInfo;
import com.easyrong.wireless.user.base.BaseException;
import com.easyrong.wireless.user.base.BaseRspEntity;
import com.easyrong.wireless.user.constants.UserErrorInfo;
import com.easyrong.wireless.user.entity.UserEntity;
import com.easyrong.wireless.user.service.UserService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;

@RestController
@RequestMapping("/api")
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ApiOperation(value = "用户注册", notes = "注册数据提交")
    public Object userRegister(@RequestBody(required = false) UserEntity userEntity) throws BaseException {
        UserEntity user = userService.findByName(userEntity.getName());
        if (null == user) {
            userService.save(user);
            return new BaseRspEntity(BaseErrorInfo.SUCCESS);
        } else {
            return new BaseRspEntity(BaseErrorInfo.SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ApiOperation(value = "用户登录", notes = "登录数据提交")
    public Object userLogin(@RequestBody(required = false) UserEntity userEntity) throws BaseException {
        UserEntity user = userService.findByNameAndPassword(userEntity.getName(), userEntity.getPassword());

        if (null == user) {
            return new BaseRspEntity(UserErrorInfo.USRENAME_PASSWORD_ERROR);
        } else {
            return new BaseRspEntity(BaseErrorInfo.SUCCESS);
        }
    }

    @ApiOperation(value = "获取用户信息", notes = "根据用户id获取单个用户信息")
    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
    public Object findOne(@PathVariable("id") int id) throws BaseException {
        return new BaseRspEntity(userService.findById(id));
    }

    @ApiOperation(value = "获取用户列表", notes = "分页获取用户列表")
    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public Object findList(@RequestParam(value = "page", required = false, defaultValue = "0") int page,
                           @RequestParam(value = "size", required = false, defaultValue = "10") int size,
                           @RequestParam(value = "updateTime", required = false, defaultValue = "") String regTime) throws BaseException {
        Sort.Order[] orders = new Sort.Order[]{
                new Sort.Order(Sort.Direction.DESC, "regTime")
        };

        if (regTime.isEmpty()) {
            return new BaseRspEntity(userService.findListByPage(new PageRequest(page, size, new Sort(orders))));
        } else {
            return new BaseRspEntity(userService.findListByTime(new Timestamp(Long.parseLong(regTime)), new PageRequest(page, size, new Sort(orders))));
        }
    }

    @ApiOperation(value = "创建用户", notes = "根据实体信息创建用户")
    @ApiImplicitParam(name = "user", value = "用户详细实体user", required = true, dataType = "UserEntity")
    @RequestMapping(value = "/user", method = RequestMethod.POST)
    public Object create(@RequestBody UserEntity userEntity) throws BaseException {
        BaseRspEntity baseRspEntity = new BaseRspEntity(BaseErrorInfo.CREAT_SUCC);
        baseRspEntity.setResult(userService.save(userEntity));
        return baseRspEntity;
    }

    @ApiOperation(value = "更新用户信息", notes = "根据实体信息更新公司信息")
    @ApiImplicitParam(name = "user", value = "公司详细实体user", required = true, dataType = "UserEntity")
    @RequestMapping(value = "/user", method = RequestMethod.PUT)
    public Object update(@RequestBody UserEntity companyEntity) throws BaseException {
        BaseRspEntity baseRspEntity = new BaseRspEntity(BaseErrorInfo.UPDATE_SUCC);
        baseRspEntity.setResult(userService.update(companyEntity));
        return baseRspEntity;
    }

    @ApiOperation(value = "删除公司", notes = "根据请求id删除公司")
    @RequestMapping(value = "/user/{id}", method = RequestMethod.DELETE)
    public Object delete(@PathVariable("id") int id) throws BaseException {
        userService.delete(id);
        BaseRspEntity baseRspEntity = new BaseRspEntity(BaseErrorInfo.DELETE_SUCC);
        return baseRspEntity;
    }
}
