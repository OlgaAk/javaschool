package io.github.olgaak.service.impl;

import io.github.olgaak.dao.TimetableDao;
import io.github.olgaak.dao.TrainDao;
import io.github.olgaak.entity.TimetableItem;
import io.github.olgaak.service.api.TimetableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TimetableServiceImpl implements TimetableService {

    private TimetableDao timetableDao;

    @Autowired
    public TimetableServiceImpl(TimetableDao timetableDao){
        this.timetableDao = timetableDao;
    }

    public TimetableItem createNewTimetableItem(TimetableItem timetableItem) {
        timetableDao.createNewTimetableItem(timetableItem);
        return null;
    }

    public List<TimetableItem> getAllTimetableItems() {
        return timetableDao.getAllTimetableItems();
    }

    @Override
    public void deleteTimetableItem(long id) {
        timetableDao.deleteTimetableItem(id);
    }

    @Override
    public void editTimetableItem(TimetableItem timetableItem) {
        timetableDao.editTimetableItem(timetableItem);
    }

}
