package cn.es.service.impl;

import cn.es.domain.City;
import cn.es.repository.CityRepository;
import cn.es.service.CityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CityESServiceImpl implements CityService {
    private static final Logger LOGGER = LoggerFactory.getLogger(CityESServiceImpl.class);

    // 分页参数 -> TODO 代码可迁移到具体项目的公共 common 模块
    private static final Integer pageNumber = 0;
    private static final Integer pageSize = 10;
    Pageable pageable = new PageRequest(pageNumber, pageSize);

    // ES 操作类
    @Autowired
    private CityRepository cityRepository;

    @Override
    public Long saveCity(City city) {
        City cityResult = cityRepository.save(city);
        return cityResult.getId();
    }

    @Override
    public List<City> findByDescriptionAndScore(String description, Integer score) {
        return cityRepository.findByDescriptionAndScore(description, score);
    }

    @Override
    public List<City> findByDescriptionOrScore(String description, Integer score) {
        return cityRepository.findByDescriptionOrScore(description, score);
    }
    @Override
    public List<City> findByDescription(String description) {
        return cityRepository.findByDescription(description, pageable).getContent();
    }
    @Override
    public List<City> findByDescriptionNot(String description) {
        return cityRepository.findByDescriptionNot(description, pageable).getContent();
    }
    @Override
    public List<City> findByDescriptionLike(String description) {
        return cityRepository.findByDescriptionLike(description, pageable).getContent();
    }

}
