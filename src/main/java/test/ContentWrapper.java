package test;

import com.google.common.collect.Maps;
import filterchain.OutputInitial;

import java.util.Map;

public class ContentWrapper implements OutputInitial {
    public Map<String, String> map = Maps.newHashMap();
}