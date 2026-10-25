package com.smartmetro.service;

import com.smartmetro.entity.Route;
import com.smartmetro.exception.RouteNotFoundException;
import com.smartmetro.model.RouteTO;
import com.smartmetro.repository.RouteRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class RouteServiceImplTest {

    @Mock
    private RouteRepository routeRepository;

    @InjectMocks
    private RouteServiceImpl routeServiceImpl;

    @Test
    public void testGetAllRoutes_whenRoutesExist_thenReturnData() throws RouteNotFoundException {
        List<Route> routeList = new ArrayList<>();

        Route route1 = new Route();
        route1.setRouteId(1L);
        route1.setRouteName("Purple Line");
        routeList.add(route1);

        Route route2 = new Route();
        route2.setRouteId(2L);
        route2.setRouteName("Green Line");
        routeList.add(route2);

        when(routeRepository.findAll()).thenReturn(routeList);

        List<RouteTO> routeTOS = routeServiceImpl.findAllRoutes();
        assertEquals(2, routeTOS.size());
    }

    @Test
    public void testGetAllRoutes_whenRoutesNotExist_thenThrowException() {
        List<Route> routeList = null;
        when(routeRepository.findAll()).thenReturn(routeList);

        assertThrows(RouteNotFoundException.class, () -> routeServiceImpl.findAllRoutes());
    }
}