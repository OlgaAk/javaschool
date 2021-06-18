package io.github.olgaak.dao.api;

import io.github.olgaak.entity.TimetableItem;

import java.util.List;

public interface TimetableDao {

    void createNewTimetableItem(TimetableItem timetableItem);

    List<TimetableItem> getRoutePlanTimetableItems(long routePlanId);

    List<TimetableItem> getAllTimetableItems();

    void deleteTimetableItem(long id);

    void editTimetableItem(TimetableItem timetableItem);
}
