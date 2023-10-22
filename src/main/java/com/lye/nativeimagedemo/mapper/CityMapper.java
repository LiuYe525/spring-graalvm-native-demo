package com.lye.nativeimagedemo.mapper;

import com.lye.nativeimagedemo.entity.City;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface CityMapper {

  void upsert(String id, String name, String state, String country);

  @Select("SELECT id, name, state, country FROM city WHERE id = #{id}")
  City findById(String id);

}