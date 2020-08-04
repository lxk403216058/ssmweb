package org.example.ssm.dao;

import org.example.ssm.pojo.Item;

public interface ItemMapper {
    Item findById(int id);
}
