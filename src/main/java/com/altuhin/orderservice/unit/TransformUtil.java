package com.altuhin.orderservice.unit;

import org.hibernate.LazyInitializationException;
import org.springframework.beans.BeanUtils;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class TransformUtil {
    
    public static <X, Y> Y copyProp(X x, Class<Y> clazz) {
        if (Objects.isNull(x)) {
            return null;
        } else {
            try {
                Y t = clazz.newInstance();
                BeanUtils.copyProperties(x, t);
                return t;
            } catch (InstantiationException var3) {
                var3.printStackTrace();
                throw new RuntimeException("InstantiationException");
            } catch (IllegalAccessException var4) {
                var4.printStackTrace();
                throw new RuntimeException("IllegalAccessException");
            }
        }
    }
    
    public static <X, Y> List<Y> copyList(List<X> list, Class<Y> clazz) {
        return (List) list.parallelStream().map((e) -> {
            return copyProp(e, clazz);
        }).collect(Collectors.toList());
    }
    
    public static <X> Boolean isProxyList(List<X> list) {
        try {
            CollectionUtils.isEmpty(list);
            return false;
        } catch (LazyInitializationException var2) {
            return true;
        }
    }
    
    public static <X> Boolean isNotProxyList(List<X> list) {
        try {
            CollectionUtils.isEmpty(list);
            return true;
        } catch (LazyInitializationException var2) {
            return false;
        }
    }
}
