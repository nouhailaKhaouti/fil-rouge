package com.example.filRouge.service.Result;

import com.example.filRouge.entities.Result;

import java.util.List;

public interface ResultService {
    public List<Result> findByConcourReference(String reference);

    public Result findByReference(String reference);

    public Result findById(Long id);

    public Result saveResult(Result result);

    public Result updateResult(Result result);

    Result updateResultOral(Result result);

    Result preselectionResult(Result result);

    Result writingResult(Result result);

    Result admisResult(Result result);

    Result addModuleNote(Result result);

    public void DeleteResult(Result result);


}
