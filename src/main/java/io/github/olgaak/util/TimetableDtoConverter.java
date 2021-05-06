package io.github.olgaak.util;

import io.github.olgaak.dto.TimetableItemDto;
import io.github.olgaak.entity.TimetableItem;


public class TimetableDtoConverter {

    public static TimetableItemDto convertTimetableItemEntityToDto(TimetableItem timetableItem){
        TimetableItemDto timetableItemDto = new TimetableItemDto();
        return timetableItemDto;
    }
}
