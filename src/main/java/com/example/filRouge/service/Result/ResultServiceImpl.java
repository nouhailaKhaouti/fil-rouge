package com.example.filRouge.service.Result;


import com.example.filRouge.Repository.ResultRepository;
import com.example.filRouge.entities.Result;
import com.example.filRouge.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ResultServiceImpl implements ResultService {

    final private ResultRepository resultRepository;
    @Override
    public List<Result> findByConcourReference(String reference) {
        return null;
    }

    @Override
    public Result findByReference(String reference) {
/*        Optional<Result> result=resultRepository.findByReference(reference);
        if(result.isEmpty()){
            throw new NotFoundException();
        }*/
        return null;
    }

    @Override
    public Result findById(Long id) {
        Optional<Result> result=resultRepository.findById(id);
        if(result.isEmpty()){
            throw new NotFoundException();
        }
        return result.get();
    }

    @Override
    public Result saveResult(Result result) {
/*        Optional<Result> OptionalResult=resultRepository.findByReference(result.getReference());
        if(OptionalResult.isPresent()){
            throw new AlreadyExistException();
        }*/
        return resultRepository.save(result);
    }

    @Override
    public Result updateResult(Result result) {
/*        Result result1=findByReference(result.getReference());
        result1.setCoefResult(result.getCoefResult());*/
        return null;
    }

    @Override
    public void DeleteResult(Result result) {
        resultRepository.delete(null);
    }

    @Override
    public Result DeleteResultById(Long id) {
        return null;
    }

}
