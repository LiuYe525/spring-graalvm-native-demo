package com.lye.nativeimagedemo.controller;

import com.google.gson.Gson;
import com.lye.nativeimagedemo.mapper.CityMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lye
 * @date 2023/10/14
 */
@Slf4j
@RestController
@RequestMapping("/path")
public class PathController {

  private final Gson gson = new Gson();
  private final CityMapper cityMapper;
  private final StringRedisTemplate redisTemplate;

  public PathController(CityMapper cityMapper, StringRedisTemplate redisTemplate) {
    this.cityMapper = cityMapper;
    this.redisTemplate = redisTemplate;
    log.info(
        "Created {}(cityMapper={}, redisTemplate={}).", this, this.cityMapper, this.redisTemplate);
  }

  @GetMapping("/{id}")
  public String hello(@PathVariable String id) {
    log.info("Received request {}.", id);
    return "Hello " + id;
  }

  @GetMapping("/database/{id}")
  public String dbHello(
      @PathVariable String id,
      @RequestParam(required = false) String name,
      @RequestParam(required = false) String state,
      @RequestParam(required = false) String country) {
    if (StringUtils.hasText(name)) {
      cityMapper.upsert(id, name, state, country);
      return String.format("update database id: %s, name: %s", id, name);
    }
    var city = cityMapper.findById(id);
    return String.format(
        "select databse id: %s, name: %s, state: %s, country: %s",
        city.getId(), city.getName(), city.getState(), city.getCountry());
  }

  @GetMapping("/redis/{id}")
  public String redisHello(@PathVariable String id, @RequestParam(required = false) String name) {
    if (StringUtils.hasText(name)) {
      redisTemplate.opsForValue().set(id, name);
      return String.format("update redis id: %s, name: %s", id, name);
    }
    name = redisTemplate.opsForValue().get(id);
    return String.format("select databse id: %s, name: %s", id, name);
  }
}
