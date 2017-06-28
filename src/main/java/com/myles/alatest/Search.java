package com.myles.alatest;

import java.util.Map;

public interface Search<K,V>{

    V search(K token);

    void index(Map<K,V> map);

}
