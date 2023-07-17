package com.alttalttal.mini_project.delete.test;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MongoUserService {
    private final MongoUserRepository mongoUserRepository;

    public MongoUser createUser(){
        MongoList mongoList1 = new MongoList();
        mongoList1.setMongo("kang");
        mongoList1.setAmount(1L);
        MongoList mongoList2 = new MongoList();
        mongoList2.setMongo("kim");
        mongoList2.setAmount(2L);
        List<MongoList> mongoList = new ArrayList<>();
        mongoList.add(mongoList1);
        mongoList.add(mongoList2);
        MongoUser mongoUser = new MongoUser();
        mongoUser.setName("강");
        mongoUser.setMongoLists(mongoList);
        mongoUserRepository.save(mongoUser);
        return mongoUser;
    }
    public MongoUser getUser(BigInteger id){
        return mongoUserRepository.findById(id).orElseThrow(()->new IllegalArgumentException("실패"));
    }
}
