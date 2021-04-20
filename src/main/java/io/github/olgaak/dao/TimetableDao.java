package io.github.olgaak.dao;

import io.github.olgaak.entity.TimetableItem;
import io.github.olgaak.entity.Train;

import java.util.List;

public interface TimetableDao {

    void createNewTimetableItem(TimetableItem timetableItem);

    List<TimetableItem> getAllTimetableItems();

    void deleteTimetableItem(long id);

    void editTimetableItem(TimetableItem timetableItem);
}
