package org.example.ssm.service;

import org.example.ssm.dao.ItemMapper;
import org.example.ssm.pojo.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ItemServiceImpl implements ItemService {
    @Autowired
    private ItemMapper itemMapper;
    @Override
    public Item findById(int id) {
        return itemMapper.findById(id);
    }
}
