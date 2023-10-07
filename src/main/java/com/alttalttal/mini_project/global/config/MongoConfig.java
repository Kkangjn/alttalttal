package com.alttalttal.mini_project.global.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.core.convert.DbRefResolver;
import org.springframework.data.mongodb.core.convert.DefaultDbRefResolver;
import org.springframework.data.mongodb.core.convert.DefaultMongoTypeMapper;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;
import org.springframework.data.mongodb.core.mapping.MongoMappingContext;

@Configuration
public class MongoConfig {
    // _class 삭제하기 위함
    //  MongoDB 컬렉션에는 다양한 유형의 인스턴스를 문서로 저장할 수 있다. 그리고 저장된 문서를 탐색하여 찾은 결과를 온전한 객체로 인스턴스화하기 위해서는 해당 필드의 값들을 올바르게 읽는 것이 중요하다.
    //  따라서 MongoDB에 저장할 때 함께 타입 정보를 저장함
    @Bean
    public MappingMongoConverter mappingMongoConverter(MongoDatabaseFactory mongoDatabaseFactory,
                                       MongoMappingContext context){
        DbRefResolver dbRefResolver = new DefaultDbRefResolver(mongoDatabaseFactory);
        MappingMongoConverter converter = new MappingMongoConverter(dbRefResolver, context);
        converter.setTypeMapper(new DefaultMongoTypeMapper(null));
        return converter;
    }
}
