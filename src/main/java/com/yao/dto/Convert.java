package com.yao.dto;

/**
 * Created by Jack Yao on 2021/6/14 3:19 下午
 */
public interface Convert<S,T> {
    T convert(S s,T t);
    T convert(S s);
}
