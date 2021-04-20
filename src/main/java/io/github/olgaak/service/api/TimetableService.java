package io.github.olgaak.service.api;

import java.util.List;

import io.github.olgaak.entity.TimetableItem;

    public interface TimetableService {

    TimetableItem createNewTimetableItem(TimetableItem timetableItem);

    public List<TimetableItem> getAllTimetableItems();

    void deleteTimetableItem(long id);

    void editTimetableItem(TimetableItem timetableItem);
}
