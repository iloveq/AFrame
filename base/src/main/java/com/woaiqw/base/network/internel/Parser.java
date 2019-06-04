package com.woaiqw.base.network.internel;

public interface Parser<T> {
    T parse(String jsonStr) throws Exception;
}
