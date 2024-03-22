package com.example.filRouge.service.Result;

import com.example.filRouge.entities.Result;

import java.util.List;

public interface ResultService {
    public List<Result> findByConcourReference(String reference);

    public Result findByReference(String reference);

    public Result findById(Long id);

    public Result saveResult(Result result);

    public Result updateResult(Result result);

    public void DeleteResult(Result result);

    public Result DeleteResultById(Long id);

}
