package cn.tedu.mall.ams.service;

import cn.tedu.mall.pojo.admin.model.Admin;

import java.io.IOException;

public interface ISSOService {
    String doLogin(Admin admin) throws IOException;
}
