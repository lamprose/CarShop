package com.carshop.server.service;

import java.util.List;
import java.util.Map;

public interface EvaluationService {
    Map<String,Object> addEvaluation(Map<String,String> params);

    Map<String,Object> editEvaluation(Map<String,String> params);

    Map<String,Object> getEvaluationListPage(Map<String,String> params);

    Map<String,Object> removeEvaluation(List<Map<String, String>> params);
}
