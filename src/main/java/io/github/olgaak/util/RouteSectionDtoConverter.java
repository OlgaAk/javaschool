package io.github.olgaak.util;

import io.github.olgaak.dto.RouteSectionDto;
import io.github.olgaak.entity.RouteSection;

public class RouteSectionDtoConverter {

    public static RouteSectionDto convertRouteSectionEntityToDto(RouteSection routeSection){
        RouteSectionDto routeSectionDto = new RouteSectionDto();
        routeSectionDto.setId(routeSection.getId());
        routeSectionDto.setStartStation(StationDtoConverter.convertStationEntityToDto(routeSection.getStartStation()));
        routeSectionDto.setEndStation(StationDtoConverter.convertStationEntityToDto(routeSection.getEndStation()));
        return routeSectionDto;
    }

    public static RouteSection convertRouteSectionDtoToEntity(RouteSectionDto routeSectionDto){
        RouteSection routeSection = new RouteSection();
        routeSection.setId(routeSectionDto.getId());
        routeSection.setStartStation(StationDtoConverter.convertStationDtoToEntity(routeSectionDto.getStartStation()));
        routeSection.setEndStation(StationDtoConverter.convertStationDtoToEntity(routeSectionDto.getEndStation()));
        return routeSection;
    }

}
