package com.rfgvieira.Car_Fipe.service;

import java.util.List;

public interface ICastData {
    <T> T castData(String json, Class<T> type);

    <T> List<T> getList (String json, Class<T> type);
}
