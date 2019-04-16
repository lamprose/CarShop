package com.carshop.server.service;

import java.util.Map;

public interface EvaluationService {
    Map<String,Object> addEvaluation(Map<String,String> params);

    Map<String,Object> getEvaluation(Map<String,String> params);
}
